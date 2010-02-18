package no.forsvaret.intrastats

class VisitController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [visitInstanceList: Visit.list(params), visitInstanceTotal: Visit.count()]
    }

    def create = {
        def visitInstance = new Visit()
        visitInstance.properties = params
        return [visitInstance: visitInstance]
    }

    def save = {
        def visitInstance = new Visit(params)
        if (visitInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'visit.label', default: 'Visit'), visitInstance.id])}"
            redirect(action: "show", id: visitInstance.id)
        }
        else {
            render(view: "create", model: [visitInstance: visitInstance])
        }
    }

    def show = {
        def visitInstance = Visit.get(params.id)
        if (!visitInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'visit.label', default: 'Visit'), params.id])}"
            redirect(action: "list")
        }
        else {
            [visitInstance: visitInstance]
        }
    }

    def edit = {
        def visitInstance = Visit.get(params.id)
        if (!visitInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'visit.label', default: 'Visit'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [visitInstance: visitInstance]
        }
    }

    def update = {
        def visitInstance = Visit.get(params.id)
        if (visitInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (visitInstance.version > version) {
                    
                    visitInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'visit.label', default: 'Visit')] as Object[], "Another user has updated this Visit while you were editing")
                    render(view: "edit", model: [visitInstance: visitInstance])
                    return
                }
            }
            visitInstance.properties = params
            if (!visitInstance.hasErrors() && visitInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'visit.label', default: 'Visit'), visitInstance.id])}"
                redirect(action: "show", id: visitInstance.id)
            }
            else {
                render(view: "edit", model: [visitInstance: visitInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'visit.label', default: 'Visit'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def visitInstance = Visit.get(params.id)
        if (visitInstance) {
            try {
                visitInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'visit.label', default: 'Visit'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'visit.label', default: 'Visit'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'visit.label', default: 'Visit'), params.id])}"
            redirect(action: "list")
        }
    }
}
