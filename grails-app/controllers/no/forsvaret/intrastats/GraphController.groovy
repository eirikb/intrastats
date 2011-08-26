package no.forsvaret.intrastats

import grails.converters.JSON

class GraphController {

    def graph = {
        def instance = null
        switch (params.type) {
            case "site":
            instance = Site.get(params.id)
            break
            case "section":
            instance = Section.get(params.id)
            break
            case "page":
            instance = Page.get(params.id)
            break
        }
        if (instance != null) {
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

            def visitlist = null
            switch (params.type) {
                case "site":
                visitlist = SiteVisitRel.findAllBySiteAndDateCreatedBetween(instance, fromDate, toDate + 1)
                break
                case "section":
                visitlist = SectionVisitRel.findAllBySectionAndDateCreatedBetween(instance, fromDate, toDate + 1)
                break
                case "page":
                visitlist = PageVisitRel.findAllByPageAndDateCreatedBetween(instance, fromDate, toDate + 1)
                break
            }

            def visits = [:]
            visitlist?.each() {
                def visit = it
                def day = visit.dateCreated.format("MM-dd")
                visits[day] = visits[day] == null ? 1 : visits[day] + 1
            }

            def colors = ["AFD8F8", "F6BD0F", "8BBA00", "FF8E46", "008E8E", "D64646", "8E468E", "588526", "B3AA00", "008ED6", "9D080D", "A186BE"]
            def visitData = "<graph bgAlpha='0' caption='Visits for each day (" + prettytime.display(date:fromDate) +
            " - " + prettytime.display(date:toDate)  + ")' xAxisName='Day' yAxisName='Visits' showNames='1' decimalPrecision='0' formatNumberScale='0'>"
            def i = 0
            visits.sort{it.key}.each() {
                def day = it.key
                def color = colors[i]
                i = i >= colors.size() ? 0 : i + 1
                visitData += "<set name='$day' value='$it.value' color='$color'/>"

            }
            visitData += "</graph>"

            render ([visitData: visitData, visitCount: visitlist.size()] as JSON)
        }
    }
}
