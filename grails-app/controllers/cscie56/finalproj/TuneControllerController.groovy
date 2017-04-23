package cscie56.finalproj

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TuneControllerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TuneController.list(params), model:[tuneControllerCount: TuneController.count()]
    }

    def show(TuneController tuneController) {
        respond tuneController
    }

    def create() {
        respond new TuneController(params)
    }

    @Transactional
    def save(TuneController tuneController) {
        if (tuneController == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tuneController.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tuneController.errors, view:'create'
            return
        }

        tuneController.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tuneController.label', default: 'TuneController'), tuneController.id])
                redirect tuneController
            }
            '*' { respond tuneController, [status: CREATED] }
        }
    }

    def edit(TuneController tuneController) {
        respond tuneController
    }

    @Transactional
    def update(TuneController tuneController) {
        if (tuneController == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tuneController.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tuneController.errors, view:'edit'
            return
        }

        tuneController.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tuneController.label', default: 'TuneController'), tuneController.id])
                redirect tuneController
            }
            '*'{ respond tuneController, [status: OK] }
        }
    }

    @Transactional
    def delete(TuneController tuneController) {

        if (tuneController == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tuneController.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tuneController.label', default: 'TuneController'), tuneController.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tuneController.label', default: 'TuneController'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
