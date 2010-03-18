package no.forsvaret.intrastats

import grails.converters.JSON

class SectionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def show = {
        def sectionInstance = Section.get(params.id)
        if (!sectionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'section.label', default: 'Section'), params.id])}"
            redirect(action: "list")
        }
        else {
            def visitCount = null
            def data = getVisits();
            [sectionInstance: sectionInstance, visitCount: visitCount]
        }
    }

    def getVisitsAjax = {
        render getVisits()
    }

    def getVisits = {
        def sectionInstance = Section.get(params.id)
        if (sectionInstance != null) {
            def fromDate = new Date() - 7;
            def toDate = new Date()
            if (params.fromDate != null && params.toDate != null) {
                try {
                    fromDate = new Date().parse("yyyy-MM-dd", params.fromDate)
                    toDate = new Date().parse("yyyy-MM-dd", params.toDate)
                } catch (e) {
                    fromDate = new Date() - 7
                    toDate = new Date()
                }
            }
            def  visitlist = Visit.executeQuery("select v, c from SectionVisitRel svr left join svr.section s left join svr.visit v left join v.client c where s = ? \
                                                and svr.dateCreated >= ? and svr.dateCreated <= ?", [sectionInstance, fromDate, toDate])
            def browsers = ["MSIE 5.0":["Internet Explorer 5.0", 0],
                            "MSIE 5.5":["Internet Explorer 5.5", 0],
                            "MSIE 6.0":["Internet Explorer 6.0", 0],
                            "MSIE 7.0":["Internet Explorer 7.0", 0],
                            "MSIE 8.0":["Internet Explorer 8.0", 0],
                            "firefox":["Firefox", 0],
                            "safari":["Safari", 0],
                            "opera":["Opera", 0],
                            "chrome":["Chrome", 0],
                            "lynx":["Lynx", 0],
                            "__OTHER":["Other", 0]]
            def visits = [:]
            visitlist?.each() {
                def visit = it[0]
                def client = it[1]
                def userAgent = client.userAgent
                def found = false
                browsers.each() { key, value ->
                    if (client.userAgent.contains(key)) {
                        value[1]++
                        found = true
                    }
                }
                if (!found) {
                    browsers.get("__OTHER")[1]++
                }
                def day = visit.dateCreated.format("MM-dd")
                if (visits[day] == null) {
                    visits[day] = 0
                }
                visits[day]++
            }

            def colors = ["AFD8F8", "F6BD0F", "8BBA00", "FF8E46", "008E8E", "D64646", "8E468E", "588526", "B3AA00", "008ED6", "9D080D", "A186BE"]
            def visitData = "<graph bgAlpha='0' caption='Visits for each day (" + prettytime.display(date:fromDate) +
            " - " + prettytime.display(date:toDate)  + ")' xAxisName='Day' yAxisName='Visits' showNames='1' decimalPrecision='0' formatNumberScale='0'>"
            visits.sort{it.key}.each() {
                def day = it.key
                def color = colors[(int)(Math.random() * colors.size())]
                visitData += "<set name='$day' value='$it.value' color='$color'/>"

            }
            visitData += "</graph>"


            def browserData = "<graph bgAlpha='0' caption='Browser usage' decimalPrecision='0' formatNumberScale='0'>"
            def i = 0
            browsers.each() { key, value ->
                if (value[1] > 0) {
                    def color = colors[i++]
                    if (i >= colors.size()) {
                        i = 0
                    }
                    browserData += "<set name='" + value[0] + "' value='" + value[1] + "' color='" + color + "' />"
                }
            }
            browserData += "</graph>"

            return [visitData: visitData, browserData: browserData, visitCount: visitlist.size()] as JSON
        }
    }
}
