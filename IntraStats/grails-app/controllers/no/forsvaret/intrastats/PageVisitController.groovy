package no.forsvaret.intrastats

class PageVisitController {

    def output
    def timeOut = 1000 * 60 * 30

    def index = {
        output = ""
        if (params.url != null) {
            def page = getPage(params.url?.decodeHTML(), params.title)
            if (page != null) {
                def referral = null
                if (params.referral != null) {
                    referral = getPage(params.referral, null)
                }
                registerVisit(page, referral, params.browserWidth, params.browserHeight)
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
            page = new Page(url:params.url, title:params.title)
            if (validate(page)) {
                page.save()
            } else {
                return null
            }
        }
        return page
    }

    def registerVisit(page, referral, browserWidth, browserHeight) {
        def client = getClient(request.getRemoteAddr(), request.getRemoteHost(), request.getHeader("user-agent"))
         if (Visit.countByClientAndDateCreatedGreaterThan(client, new Date(new Date().getTime() - timeOut)) == 0) {
            if (client != null) {
                def visit = new Visit(referral:referral, browserWidth:browserWidth, browserHeight:browserHeight, page:page, client:client)
                if (validate(visit)) {
                    visit.save()
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