package no.forsvaret.intrastats

class HomeController {

    def index = {
        [sections : Section.list()]
    }
}
