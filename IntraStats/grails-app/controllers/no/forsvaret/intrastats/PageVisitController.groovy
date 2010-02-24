package no.forsvaret.intrastats

import org.codehaus.groovy.grails.commons.*


class PageVisitController {

    def output
    def config = ConfigurationHolder.config
    def timeOut = config.pagevisit.session.timeout

    def index = {
        output = ""
        if (params.url != null) {
            def page = getPage(params.url?.decodeHTML(), params.title)
            if (page != null) {
                def referral = null
                if (params.referral != null) {
                    referral = getPage(params.referral, null)
                }
                def client = getClient(request.getRemoteAddr(), request.getRemoteHost(), request.getHeader("user-agent"))
                registerVisit(getSection(params.section), page, client, referral, params.browserWidth, params.browserHeight)
            } else {
                output += "Page was null, dunnolol"
            }
        } else {
            output += "Must have valid url (?url=http://www.google.com)"
            output += "\nValid params: url, title, referral, browserWidth, browserHeight"
        }
        output = params.jsoncallback + "({\"output\" : \"" + output + "\"})"
        response.outputStream << output
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

    def registerVisit(section, page, client, referral, browserWidth, browserHeight) {
        def visits = Visit.executeQuery("select count(v) from Visit v where v.client = ? and dateCreated > ? and v.page = ?",
            [client, new Date(new Date().getTime() - timeOut), page])[0]
        if (visits == 0) {
            if (client != null) {
                def visit = new Visit(referral:referral, browserWidth:browserWidth, browserHeight:browserHeight, page:page, client:client)
                if (validate(visit)) {
                    visit.save()
                    if (section != null && page != null) {
                        section.addToPages(page).save()
                    }
                    if (page.addToVisits(visit).save() &&
                        client.addToVisits(visit).save()) {
                        output += "OK"
                    } else  {
                        output += "Linking between Client, Visit and Page failed!"
                    }
                } else {
                    output += "Visit did not validate..."
                }
            } else {
                output += "Could not initalize client..."
            }
        } else {
            output += "Page already registered"
        }
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
                output += errors + '\n'
                return false
            }
            return true
        } else {
            return false
        }
    }
}
