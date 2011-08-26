package no.forsvaret.intrastats

class ClientController {

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [clientInstanceList: Client.list(params), clientInstanceTotal: Client.count()]
    }
}
