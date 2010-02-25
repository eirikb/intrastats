package no.forsvaret.intrastats

class SectionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sectionInstanceList: Section.list(params), sectionInstanceTotal: Section.count()]
    }

    def create = {
        def sectionInstance = new Section()
        sectionInstance.properties = params
        return [sectionInstance: sectionInstance]
    }

    def save = {
        def sectionInstance = new Section(params)
        if (sectionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'section.label', default: 'Section'), sectionInstance.id])}"
            redirect(action: "show", id: sectionInstance.id)
        }
        else {
            render(view: "create", model: [sectionInstance: sectionInstance])
        }
    }

    def show = {
        def sectionInstance = Section.get(params.id)
        if (!sectionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'section.label', default: 'Section'), params.id])}"
            redirect(action: "list")
        }
        else {
            def visitCount = null
            if (params.submit != null) {
                //TODO: hent ut info.
                //TODO: Vis info i tabellform + kanskje fusion chart.
                //TODO: Bruk jQuery datepicker
            }

            //TODO: List ut alle pages
            //TODO: Browsertype(user-agent)-statistikk!
            //TODO: browsersize

            def data = getVisits();

            [sectionInstance: sectionInstance, visitCount: visitCount, data: data]
        }
    }

    def getVisitsAjax = {
        println params.fromDate
        render getVisits()
    }

    def getVisits = {
        def sectionInstance = Section.get(params.id)
        if (sectionInstance != null) {
            def visitlist = null

            if (params.fromDate == null || params.toDate == null) {
                visitlist = Visit.executeQuery("select v from Visit v left join v.page p left join p.section s where s = ?", sectionInstance)
            } else {
                Date
            }
            def visits = [:]
            visitlist?.each() {
                def day = it.dateCreated.format("dd")
                if (visits[day] == null) {
                    visits[day] = 0
                }
                visits[day]++
            }


            def colors = ["AFD8F8", "F6BD0F", "8BBA00", "FF8E46", "008E8E", "D64646", "8E468E", "588526", "B3AA00", "008ED6", "9D080D", "A186BE"]
            def data = "<graph caption='Visits for each day' xAxisName='Day' yAxisName='Visits' showNames='1' decimalPrecision='0' formatNumberScale='0'>"
            visits.sort{it.key}.each() {
                def day = it.key
                def color = colors[(int)(Math.random() * colors.size())]
                data += "<set name='$day' value='$it.value' color='$color'/>"

            }
            data += "</graph>"
            return data
        }
    }

    def edit = {
        def sectionInstance = Section.get(params.id)
        if (!sectionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'section.label', default: 'Section'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [sectionInstance: sectionInstance]
        }
    }

    def update = {
        def sectionInstance = Section.get(params.id)
        if (sectionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sectionInstance.version > version) {
                    
                    sectionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'section.label', default: 'Section')] as Object[], "Another user has updated this Section while you were editing")
                    render(view: "edit", model: [sectionInstance: sectionInstance])
                    return
                }
            }
            sectionInstance.properties = params
            if (!sectionInstance.hasErrors() && sectionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'section.label', default: 'Section'), sectionInstance.id])}"
                redirect(action: "show", id: sectionInstance.id)
            }
            else {
                render(view: "edit", model: [sectionInstance: sectionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'section.label', default: 'Section'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def sectionInstance = Section.get(params.id)
        if (sectionInstance) {
            try {
                sectionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'section.label', default: 'Section'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'section.label', default: 'Section'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'section.label', default: 'Section'), params.id])}"
            redirect(action: "list")
        }
    }
}
