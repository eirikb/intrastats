package no.forsvaret.intrastats

class Visit {

    static belongsTo = [page : Page]

    String referral
    Date dateCreated
    Date lastUpdated
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
