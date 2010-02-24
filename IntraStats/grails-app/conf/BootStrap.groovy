
import no.forsvaret.intrastats.*
import grails.util.GrailsUtil

class BootStrap {



    def init = { servletContext ->



        switch (GrailsUtil.environment) {
            case "development":
            def pv = new PageVisitController()
            for(i in 0..100) {

                int id = (Math.random() * 3)
                def section = pv.getSection("Section " + id)

                id = (Math.random() * 10)
                def page =  pv.getPage("http://www.testpage" + id + ".com", "Testpage " + id)

                id = (Math.random() * 5)
                def client = pv.getClient("Address " + id, "Host " + id, "User-agent " + id)

                if (section != null && page != null && client != null) {
                    pv.registerVisit(section, page, client, null, id, id)
                } else {
                    println "FRAK!"
                }
            }
            break
        }
    }
    
    def destroy = {
    }
} 