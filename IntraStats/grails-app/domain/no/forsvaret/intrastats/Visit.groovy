package no.forsvaret.intrastats

class Visit {

    static belongsTo = [page : Page]

    Page referral
    Date dateCreated
    Page page
    Client client
    Integer browserWidth
    Integer browserHeight

    static constraints = {
        referral(nullable: true)
        browserWidth(nullable: true)
        browserHeight(nullable: true)
    }
}
