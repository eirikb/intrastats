package no.forsvaret.intrastats

class PageVisitController {

    def index = {
        if (params.url != null) {
            def page = getPage(params.url, params.title)
            if (page != null) {
                registerVisit(page, params.referral, params.browserWidth, params.browserHeight)
            } else {
                response.outputStream << "Page was null, dunnolol"
            }
        } else {
            response.outputStream << "Must have valid url (?url=http://www.google.com)"
            response.outputStream << "\nValid params: url, title, referral, browserWidth, browserHeight"
        }
    }

    def getPage(url, title) {
        def page = Page.findByUrl(url)
        if (page == null) {
            page = new Page(url:params.url, title:params.title)
            page.validate()
            if(page.hasErrors()) {
                def errors = "Errors:"
                page.errors.each {
                    errors += '\n' + it
                }
                page = null
                response.outputStream << errors
            } else {
                page.save()
            }
        }
        return page
    }

    def registerVisit(page, referral, browserWidth, browserHeight) {
        if (session["visits"] == null) {
            session["visits"] = []
        }
        if (!session["visits"].contains(page.id)) {
            if (page.addToVisits(new Visit(referral:referral, remoteAddress:request.getRemoteAddr(),
                        remoteHost:request.getRemoteHost(),
                        browserWidth:browserWidth, browserHeight:browserHeight)).save()) {
                response.outputStream << "OK"
                session["visits"] << page.id
            } else {
                response.outputStream << "Unable to save!"
            }
        } else {
            response.outputStream << "Page already registered"
        }
    }
}
