
import no.forsvaret.intrastats.*
import grails.util.GrailsUtil

class BootStrap {



    def init = { servletContext ->



        switch (GrailsUtil.environment) {
            case "development":
            def pv = new PageVisitController()

            def sections = new Section[3]
            for (i in 0..sections.length - 1) {
                sections[i] = pv.getSection("Section " + i)
            }

            def pages = new Page[10]
            for (i in 0..pages.length - 1) {
                pages[i] =  pv.getPage("http://www.testpage" + i + ".com", "Testpage " + i)
            }

            def clients = new Client[5]
            for (i in 0..clients.length - 1) {
                clients[i] = pv.getClient("Address " + i, "Host " + i, "User-agent " + i)
            }

            def p = 0
            def total = 100
            pv.timeOut = 0
            for(i in 0..total) {
                def section = sections[(int)(Math.random() * (sections.length - 1))]
                def page = pages[(int)(Math.random() * (pages.length - 1))]
                def client = clients[(int)(Math.random() * (clients.length - 1))]
                
                if (section != null && page != null && client != null) {
                    pv.registerVisit(section, page, client, null, i, i)
                } else {
                    println "FRAK!"
                }
                def p2 = (int)((i / total) * 100)
                if (p2 != p) {
                    p = p2
                    println p + "%"
                }
            }

            Visit.list().each()  {
                it.setDateCreated(new Date(System.currentTimeMillis() - (long)(Math.random() * (1000L * 60L * 60L * 24L * 32L))))
                it.save()
            }
            println "Number of visits: " + Visit.count()
            break
        }
    }
    
    def destroy = {
    }
}