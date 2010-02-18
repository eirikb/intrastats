package no.forsvaret.intrastats

class Client {
    static hasMany = [visits : Visit]

    String remoteAddress
    String remoteHost
    String userAgent
    
    static constraints = {
    }
}
