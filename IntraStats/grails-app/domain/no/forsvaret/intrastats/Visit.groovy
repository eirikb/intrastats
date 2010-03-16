package no.forsvaret.intrastats

class Visit {

    Date dateCreated
    Client client
    Integer browserWidth
    Integer browserHeight

    static constraints = {
        browserWidth(nullable: true)
        browserHeight(nullable: true)
    }
}
