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
        pv.params.url = "http://www.testpage1.com"
        pv.request.addHeader("user-agent", "test")
        pv.index()
        println pv.response.contentAsString
        assertEquals(1, Page.count())
        assertEquals(1, Visit.count())
        assertEquals("http://www.testpage1.com", Page.list().get(0).getUrl())
    }

    void testBrowserSize() {
        def width = 1234
        def height = 55353
        def pv = new PageVisitController()
        pv.request.addHeader("user-agent", "test")
        pv.params.url = "http://www.testpage2.com"
        pv.params.browserWidth = "" + width
        pv.params.browserHeight = height
        pv.index()
        println pv.response.contentAsString
        assertEquals(1, Page.count())
        assertEquals("http://www.testpage2.com", Page.list().get(0).getUrl())
        assertEquals(1, Visit.count())
        assertEquals(width, Visit.list().get(0).getBrowserWidth())
        assertEquals(height, Visit.list().get(0).getBrowserHeight())
        Visit.list().each() { println it.dump() }
    }
}
