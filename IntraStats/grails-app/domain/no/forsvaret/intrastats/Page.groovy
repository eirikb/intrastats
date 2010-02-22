package no.forsvaret.intrastats

class Page {

    static hasMany = [visits : Visit]
    static belongsTo = [section : Section]

    String title
    String url
    Date dateCreated
    Section section
    
    static constraints = {
        title(nullable: true)
        url(nullable: false, unique: true)
        section(nullable: true)
    }
}
