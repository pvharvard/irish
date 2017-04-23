package cscie56.finalproj

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MeasureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Measure.list(params), model:[measureCount: Measure.count()]
    }

    def show(Measure measure) {
        respond measure
    }

    def create() {
        respond new Measure(params)
    }

    @Transactional
    def save(Measure measure) {
        if (measure == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (measure.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond measure.errors, view:'create'
            return
        }

        measure.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'measure.label', default: 'Measure'), measure.id])
                redirect measure
            }
            '*' { respond measure, [status: CREATED] }
        }
    }

    def edit(Measure measure) {
        respond measure
    }

    @Transactional
    def update(Measure measure) {
        if (measure == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (measure.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond measure.errors, view:'edit'
            return
        }

        measure.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'measure.label', default: 'Measure'), measure.id])
                redirect measure
            }
            '*'{ respond measure, [status: OK] }
        }
    }

    @Transactional
    def delete(Measure measure) {

        if (measure == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        measure.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'measure.label', default: 'Measure'), measure.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'measure.label', default: 'Measure'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
