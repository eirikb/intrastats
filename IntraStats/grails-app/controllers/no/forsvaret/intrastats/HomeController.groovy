package no.forsvaret.intrastats

class HomeController {

    def index = {
        def calendar = new GregorianCalendar()
        calendar.setTime(new Date())
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        def today = calendar.getTime()
        def siteVisits = SiteVisitRel.countByDateCreatedGreaterThan(today)
        def sectionVisits =  SectionVisitRel.countByDateCreatedGreaterThan(today)
        def pageVisits = PageVisitRel.countByDateCreatedGreaterThan(today)
        [siteVisits: siteVisits, sectionVisits: sectionVisits, pageVisits: pageVisits, sections : Section.list(),
        siteTotal: SiteVisitRel.count(), sectionTotal: SectionVisitRel.count(), pageTotal: PageVisitRel.count()]
    }
}
