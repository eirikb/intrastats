package no.forsvaret.intrastats

import org.codehaus.groovy.grails.commons.*


class PageVisitController {

    def output
    def config = ConfigurationHolder.config
    def timeOut = config.pagevisit.session.timeout

    def maxCount = 1000

    def index = {
        output = ""
        def time = System.currentTimeMillis()
        if (params.url != null && params.url.toLowerCase().indexOf("http://publisering.mil.no/content/articleeditor/editor.jhtml") < 0) {
            def page = getPage(params.url?.decodeHTML(), params.title)
            if (page != null) {
                def client = getClient(request.getRemoteAddr(), request.getRemoteHost(), request.getHeader("user-agent"))
                registerVisit(getSite(params.site), getSection(params.section), page, client, params.browserWidth, params.browserHeight)
            } else {
                output += "Page was null, dunnolol"
                log.error("Page was null, unknown reason")
            }
        } else {
            log.warn("Got a request without valid parameters. Client IP: " + request.getRemoteAddr() + ". URL: " + params.url)
            output += "Must have valid url (?url=http://www.google.com)"
            output += "\nValid params: site, section, url, title, browserWidth, browserHeight"
        }
        time = System.currentTimeMillis() - time
        output += " TIME: " + time + "ms"
        log.info(time + "ms + " - output)
        output = params.jsoncallback + "({\"" + output + "\"})"
        response.outputStream << output
    }


    def registerVisit(site, section, page, client, browserWidth, browserHeight) {
        if (client != null) {
            def timeOutDate = new Date(new Date().getTime() - timeOut)

            def siteVisitRel = site != null ? SiteVisitRel.findBySiteAndDateCreatedGreaterThan(site, timeOutDate) : null
            def sectionVisitRel = section != null ? SectionVisitRel.findBySectionAndDateCreatedGreaterThan(section, timeOutDate) : null
            def pageVisitRel = page != null ? PageVisitRel.findByPageAndDateCreatedGreaterThan(page, timeOutDate) : null

            if (siteVisitRel == null || sectionVisitRel == null || pageVisitRel == null) {
                def visit = new Visit(browserWidth:browserWidth, browserHeight:browserHeight, page:page, client:client)
                if (validate(visit)) {
                    visit.save()
                    if (site != null && section != null) {
                        site.addToSections(section).save()
                    }
                    if (section != null && page != null) {
                        section.addToPages(page).save()
                    }

                     if (site != null && siteVisitRel == null) {
                        if (new SiteVisitRel(site: site, visit: visit).save()) {
                            output += " Site Visit registered."
                        } else {
                            log.error("Could not register Site Visit")
                            output += " [Error] Could not register Site Visit."
                        }
                    }

                     if (section != null && sectionVisitRel == null) {
                        if (new SectionVisitRel(section: section, visit: visit).save()) {
                            output += " Section Visit registered."
                        } else {
                            log.error("Could not register Section Visit")
                            output += " [Error] Could not register Section Visit."
                        }
                    }

                    if (pageVisitRel == null) {
                        if (new PageVisitRel(page: page, visit: visit).save()) {
                            output += " Page Visit registered."
                        } else {
                            log.error("Could not register Page Visit")
                            output += " [Error] Could not register Page Visit."
                        }
                    }
                } else {
                    log.error("Visit did not validate")
                    output += "Visit did not validate..."
                }
            } else {
                output += "Site, Section and Page already registered."
            }
        } else {
            log.error("Could not initalize client")
            output += "Could not initalize client..."
        }
    }

    def getPage(url, title) {
        def page = Page.findByUrl(url)
        if (page == null) {
            page = new Page(url:url, title:title)
            if (validate(page)) {
                page.save()
            } else {
                return null
            }
        }
        return page
    }

    def getSection(name) {
        if (name == null) {
            return null
        }
        def section = Section.findByName(name)
        if (section == null) {
            section = new Section(name: name)
            if (validate(section)) {
                section.save()
                return section
            } else {
                return null
            }
        }
        return section
    }

    def getSite(name) {
        if (name == null) {
            name = "default"
        }
        def site = Site.findByName(name)
        if (site == null) {
            site = new Site(name: name)
            if (validate(site)) {
                site.save()
                return site
            } else {
                return null
            }
        }
        return site
    }

    def getClient(remoteAddress, remoteHost, userAgent) {
        def client = Client.findWhere(remoteAddress:remoteAddress, remoteHost:remoteHost, userAgent:userAgent)
        if (client == null) {
            client = new Client(remoteAddress:remoteAddress, remoteHost:remoteHost, userAgent:userAgent)
            if (validate(client)) {
                client.save()
                return client
            } else {
                output += "Client did not validate..."
                return null
            }
        } else {
            return client
        }
    }

    def validate(object) {
        if (object != null) {
            object.validate()
            if(object.hasErrors()) {
                def errors = "Errors while creating " + object + ":"
                object.errors.each {
                    errors += '\n' + it
                }
                object = null
                log.error(errors)
                output += errors + '\n'
                return false
            }
            return true
        } else {
            return false
        }
    }
}
