package no.forsvaret.intrastats

class PageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset = params.offset != null ? params.offset : 0
        def sorting = params.sort != null ? " order by " + params.sort : ""
        sorting += params.order != null ? " " + params.order : ""
        def section = params.id != null ? " where section = " +params.id : ""
        def pages = Page.executeQuery("select id, url, title, dateCreated, \
            visits.size from Page" + section + sorting, [max:params.max, offset:params.offset])
        [pageInstanceList: pages, pageInstanceTotal: Page.count(),
            visits:pages]
    }

    def create = {
        def pageInstance = new Page()
        pageInstance.properties = params
        return [pageInstance: pageInstance]
    }

    def save = {
        def pageInstance = new Page(params)
        if (pageInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'page.label', default: 'Page'), pageInstance.id])}"
            redirect(action: "show", id: pageInstance.id)
        }
        else {
            render(view: "create", model: [pageInstance: pageInstance])
        }
    }

    def show = {
        def pageInstance = Page.get(params.id)
        if (!pageInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'page.label', default: 'Page'), params.id])}"
            redirect(action: "list")
        }
        else {
            [pageInstance: pageInstance]
        }
    }

    def edit = {
        def pageInstance = Page.get(params.id)
        if (!pageInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'page.label', default: 'Page'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [pageInstance: pageInstance]
        }
    }

    def update = {
        def pageInstance = Page.get(params.id)
        if (pageInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (pageInstance.version > version) {
                    
                    pageInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'page.label', default: 'Page')] as Object[], "Another user has updated this Page while you were editing")
                    render(view: "edit", model: [pageInstance: pageInstance])
                    return
                }
            }
            pageInstance.properties = params
            if (!pageInstance.hasErrors() && pageInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'page.label', default: 'Page'), pageInstance.id])}"
                redirect(action: "show", id: pageInstance.id)
            }
            else {
                render(view: "edit", model: [pageInstance: pageInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'page.label', default: 'Page'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def pageInstance = Page.get(params.id)
        if (pageInstance) {
            try {
                pageInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'page.label', default: 'Page'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'page.label', default: 'Page'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'page.label', default: 'Page'), params.id])}"
            redirect(action: "list")
        }
    }
}
