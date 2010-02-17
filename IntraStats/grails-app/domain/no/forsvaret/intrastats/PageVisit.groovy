package no.forsvaret.intrastats

class PageVisit {
    String title
    int visits = 1
    String URL
    String referral
    Date dateCreated
    Date lastUpdated
    String ip
    int browserWidth
    int browserHeight

    static constraints = {
        title(nullable: false)
        URL(nullable: false, url: true, unique: true)
    }
}
