package no.forsvaret.intrastats

import grails.test.*

class PageVisitControllerTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testUrl() {
        def pv = new PageVisitController()
        pv.params.site = "site"
        pv.params.section = "section"
        pv.params.url = "http://www.testpage1.com"
        pv.request.addHeader("user-agent", "test")
        pv.index()
        println pv.response.contentAsString
        assertEquals(1, Page.count())
        assertEquals(1, Visit.count())
        assertEquals(1, SiteVisitRel.count())
        assertEquals(1, SectionVisitRel.count())
        assertEquals(1, PageVisitRel.count())
        assertEquals("http://www.testpage1.com", Page.list().get(0).getUrl())
    }

    void testBrowserSize() {
        def width = 1234
        def height = 55353
        def pv = new PageVisitController()
        pv.request.addHeader("user-agent", "test")
        pv.params.site = "site"
        pv.params.section = "section"
        pv.params.url = "http://www.testpage2.com"
        pv.params.browserWidth = "" + width
        pv.params.browserHeight = height
        pv.index()
        println pv.response.contentAsString
        assertEquals(1, SiteVisitRel.count())
        assertEquals(1, SectionVisitRel.count())
        assertEquals(1, PageVisitRel.count())
        assertEquals(1, Page.count())
        assertEquals("http://www.testpage2.com", Page.list().get(0).getUrl())
        assertEquals(1, Visit.count())
        assertEquals(1, Site.count())
        assertEquals(1, Section.count())
        assertEquals(width, Visit.list().get(0).getBrowserWidth())
        assertEquals(height, Visit.list().get(0).getBrowserHeight())
    }

    void testTimeout() {
        def pv = new PageVisitController()
        pv.params.site = "site"
        pv.params.section = "section"
        pv.params.url = "http://www.testpage1.com"
        pv.request.addHeader("user-agent", "test")
        pv.index()
        println pv.response.contentAsString
        assertEquals(1, Site.count())
        assertEquals(1, Section.count())
        assertEquals(1, Page.count())
        assertEquals(1, Visit.count())
        assertEquals(1, SiteVisitRel.count())
        assertEquals(1, SectionVisitRel.count())
        assertEquals(1, PageVisitRel.count())
        assertEquals("http://www.testpage1.com", Page.list().last().getUrl())

        sleep(1000)

        pv = new PageVisitController()
        pv.params.site = "site"
        pv.params.section = "section"
        pv.params.url = "http://www.testpage2.com"
        pv.request.addHeader("user-agent", "test")
        pv.timeOut = 3000 // 3 seconds for timeout
        pv.index()
        println pv.response.contentAsString
        assertEquals(1, Site.count())
        assertEquals(1, Section.count())
        assertEquals(2, Page.count())
        assertEquals(2, Visit.count())
        assertEquals(1, SiteVisitRel.count())
        assertEquals(1, SectionVisitRel.count())
        assertEquals(2, PageVisitRel.count())
        assertEquals("http://www.testpage2.com", Page.list().last().getUrl())
     
        sleep(1000)

        pv = new PageVisitController()
        pv.params.site = "site"
        pv.params.section = "section2"
        pv.params.url = "http://www.testpage1.com"
        pv.request.addHeader("user-agent", "test")
        pv.timeOut = 0 // 0 seconds for timeout
        pv.index()
        println pv.response.contentAsString
        assertEquals(1, Site.count())
        assertEquals(2, Section.count())
        assertEquals(2, Page.count())
        assertEquals(3, Visit.count())
        assertEquals(2, SiteVisitRel.count())
        assertEquals(2, SectionVisitRel.count())
        assertEquals(3, PageVisitRel.count())
        assertEquals("http://www.testpage1.com", Page.list().get(0).getUrl())
        assertEquals("http://www.testpage2.com", Page.list().get(1).getUrl())
    }

    void testPreviewUrl() {
        def pv = new PageVisitController()
        pv.params.url = "http://publisering.miL.no/ConTent/aRticleeditor/editor.jhtmlJADDAPADDAEF"
        pv.request.addHeader("user-agent", "test")
        pv.timeOut = 0 // 0 seconds
        pv.index()
        println pv.response.contentAsString
        assertEquals(0, Page.count())
        assertEquals(0, Visit.count())
        assertEquals(0, PageVisitRel.count())
        assertEquals(0, SiteVisitRel.count())
        assertEquals(0, SectionVisitRel.count())
    }

    void testWithSiteAndSection() {
        def pv = new PageVisitController()
        pv.params.site = "site"
        pv.params.section = "section"
        pv.params.url = "http://testpage1.com"
        pv.request.addHeader("user-agent", "test")
        pv.timeOut = 0 // 0 seconds
        pv.index()
        println pv.response.contentAsString
        assertEquals(1, Site.count())
        assertEquals(1, Section.count())
        assertEquals(1, Page.count())
        assertEquals(1, Visit.count())
        assertEquals(1, SiteVisitRel.count())
        assertEquals(1, SectionVisitRel.count())
        assertEquals(1, PageVisitRel.count())

        pv.params.site = "site"
        pv.params.section = "section2"
        pv.params.url = "http://testpage2.com"
        pv.request.addHeader("user-agent", "test")
        pv.index()
        println pv.response.contentAsString
        assertEquals(1, Site.count())
        assertEquals(2, Section.count())
        assertEquals(2, Page.count())
        assertEquals(2, Visit.count())
        assertEquals(2, SiteVisitRel.count())
        assertEquals(2, SectionVisitRel.count())
        assertEquals(2, PageVisitRel.count())
    }
}
