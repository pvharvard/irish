package cscie56.finalproj

import com.testapp.Role

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugin.springsecurity.annotation.Secured

@Secured([Role.ROLE_ANONYMOUS])
@Transactional(readOnly = true)
class TuneController {

    def searchByNameService
    def analysisService

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

    @Secured([Role.ROLE_ADMIN])
    def create() {
        respond new Tune(params)
    }

    @Secured([Role.ROLE_ADMIN])
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

    @Secured([Role.ROLE_ADMIN])
    def edit(Tune tune) {
        respond tune
    }

    @Secured([Role.ROLE_ADMIN])
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

    @Secured([Role.ROLE_ADMIN])
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
            println 'searchByNameGenre param[' + p + '] -> ' + params[p]
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


    def analyzeByGenre(params) {
        for(p in params.keySet()) {
            println 'analyzeByGenre param[' + p + '] -> ' + params[p]
        }
        def genreSearch = params['genre'] ?: ""
        def keyResults   = analysisService.findKeys(genreSearch)
        def genreResults = analysisService.findGenres()
        def meterResults = analysisService.findMeters(genreSearch)
        def genreKeyMeterResults = analysisService.findGenreKeyMeters(genreSearch)

        respond keyResults, view:'analysis', model:[genreSearch:genreSearch, keyResults:keyResults, genreResults:genreResults, meterResults:meterResults, genreKeyMeterResults:genreKeyMeterResults]
    }

    def versionAbcSet(params) {
        for(p in params.keySet()) {
            println 'versionAbcSet param[' + p + '] -> ' + params[p]
        }
        def tuneId  = params['tuneId']
        def tune  = params['tune']
        def abcOption   = params['abcOption']
        println("versionAbcSet: TuneID" + tuneId + "\ttune " + tune + "\tabcOption " + abcOption)
        respond tune, view:'show', model:[abcOption: abcOption, tuneId:tuneId]
    }

    def analysis() {
        respond Tune.list(params), view:'analysis'
    }

}



