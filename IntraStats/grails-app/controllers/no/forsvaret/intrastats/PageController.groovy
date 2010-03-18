package no.forsvaret.intrastats

class PageController {
    
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        params.offset = params.offset != null ? params.offset : 0
        def sorting = " order by " + (params.sort != null ? params.sort : " count(pvr)")
        sorting += params.order != null ? " " + params.order : " desc"
        def section = params.id != null ? " where p.section.id = " + params.id : ""
        def pages = Page.executeQuery("select p.id, p.url, p.title, p.dateCreated, count(pvr)  \
            from PageVisitRel pvr right join pvr.page p" + section + " group by p.id, p.url, p.title, p.dateCreated" + sorting, [max:params.max, offset:params.offset])
        def pageCount = params.id != null ? Page.countBySection(Section.get(params.id)) : Page.count()
        [pageInstanceList: pages, pageInstanceTotal: pageCount]
    }
}
