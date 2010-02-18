package no.forsvaret.intrastats

class Page {

    static hasMany = [visits : Visit]

    String title
    String url
    Date dateCreated
    Date lastUpdated

    static constraints = {
        title(nullable: false)
        url(nullable: false, url: true, unique: true)
    }
}