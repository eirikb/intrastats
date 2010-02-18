package no.forsvaret.intrastats

class Visit {

    static belongsTo = [page : Page]

    String referral
    Date dateCreated
    Date lastUpdated
    String remoteAddress
    String remoteHost
    Page page
    int browserWidth
    int browserHeight

    static constraints = {
        referral(nullable: true)
        remoteAddress(nullable: true)
        remoteHost(nullable: true)
        browserWidth(nullable: true)
        browserHeight(nullable: true)
    }
}
