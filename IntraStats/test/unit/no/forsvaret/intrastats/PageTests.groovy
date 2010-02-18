package no.forsvaret.intrastats

import grails.test.*

class PageTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
        new Page(url:"http://www.google.com", title:"test").save()
    }
}
