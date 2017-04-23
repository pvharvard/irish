package cscie56.finalproj

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TuneController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tune.list(params), model:[tuneCount: Tune.count()]
    }

    def show(Tune tune) {
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
}