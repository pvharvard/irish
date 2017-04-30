package cscie56.finalproj

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TuneController {

    def searchByNameService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tune.list(params), model:[tuneCount: Tune.count()]
    }

    def show(Tune tune) {
        respond tune
    }

    def test(Tune tune) {
        respond tune
    }

    def create() {
        respond new Tune(params)
    }

    @Transactional
    def save(Tune tune) {
        if (tune == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tune.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tune.errors, view:'create'
            return
        }

        tune.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tune.label', default: 'Tune'), tune.id])
                redirect tune
            }
            '*' { respond tune, [status: CREATED] }
        }
    }

    def edit(Tune tune) {
        respond tune
    }

    @Transactional
    def update(Tune tune) {
        if (tune == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tune.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tune.errors, view:'edit'
            return
        }

        tune.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tune.label', default: 'Tune'), tune.id])
                redirect tune
            }
            '*'{ respond tune, [status: OK] }
        }
    }

    @Transactional
    def delete(Tune tune) {

        if (tune == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tune.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tune.label', default: 'Tune'), tune.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tune.label', default: 'Tune'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def search() {
        println("Reached search")

    }

    def searchByName(params) {
        println("Reached search by name")
        for(p in params) {
            println 'param[' + p + '] -> ' + params[p]
        }
        def tunes = []
        Name.findAll().each() {
            if (it.name ==~ /(?i).*${params['name']}.*/) {
                tunes.add(it)
            }
        }
        tunes.each {
            println 'tune ' + it
        }

        respond tunes, view:'search',  model:[tuneNames: tunes, tuneCount: Tune.count()]
    }

    def searchByNameGenre(params) {
        for(p in params.keySet()) {
            println 'param[' + p + '] -> ' + params[p]
        }
        def nameSearch  = params['name'] ?: ""
        def genreSearch = params['genre'] ?: ""
        def abcOption   = params['abcOption']
        def results = searchByNameService.findTunes(nameSearch, genreSearch)
        results.each {
            println('Result ' + it)
        }
        respond results, view:'search', model:[results: results, nameSearch:nameSearch, genreSearch:genreSearch, abcOption:abcOption]
    }

    def versionAbcSet(params) {
        for(p in params.keySet()) {
            println 'param[' + p + '] -> ' + params[p]
        }
        def tuneId  = params['tuneId']
        def tune  = params['tune']
        def abcOption   = params['abcOption']
        println("versionAbcSet: TuneID" + tuneId + "\ttune " + tune + "\tabcOption " + abcOption)
        respond tuneId, view:'show', model:[abcOption: abcOption, tuneId:tuneId]
    }

}



