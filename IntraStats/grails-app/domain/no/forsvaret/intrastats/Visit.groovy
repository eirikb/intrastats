package no.forsvaret.intrastats

class Visit {

    static belongsTo = [page : Page]

    String referral
    Date dateCreated
    Date lastUpdated
    String ip
    int browserWidth
    int browserHeight

    static constraints = {
    }
}
