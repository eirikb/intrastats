package no.forsvaret.intrastats

class Section {

    static hasMany = [pages : Page]
    static belongsTo = [site : Site]

    String name
    Site site
    Date dateCreated
    
    static constraints = {
        name(unique: true)
    }
}
