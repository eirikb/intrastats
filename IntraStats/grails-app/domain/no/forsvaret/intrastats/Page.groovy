package no.forsvaret.intrastats

class Page {

    static hasMany = [visits : Visit]

    String title
    String url
    Date dateCreated

    static constraints = {
        title(nullable: true)
        url(nullable: false, url: true, unique: true)
    }
}
