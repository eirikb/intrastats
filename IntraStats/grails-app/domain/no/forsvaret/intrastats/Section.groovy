package no.forsvaret.intrastats

class Section {
    static hasMany = [pages : Page]

    String name
    Date dateCreated
    
    static constraints = {
    }
}
