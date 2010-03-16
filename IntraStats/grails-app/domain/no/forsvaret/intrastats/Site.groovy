package no.forsvaret.intrastats

class Site {

    static hasMany = [sections : Section]

    String name

    static constraints = {
        name(unique: true)
    }
}
