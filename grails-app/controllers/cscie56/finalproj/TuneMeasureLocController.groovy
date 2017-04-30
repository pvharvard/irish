package cscie56.finalproj

import com.testapp.Role
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured([Role.ROLE_ANONYMOUS])  // not implemented so keep anonymous for now
@Transactional(readOnly = true)
class TuneMeasureLocController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TuneMeasureLoc.list(params), model:[tuneMeasureLocCount: TuneMeasureLoc.count()]
    }

    def show(TuneMeasureLoc tuneMeasureLoc) {
        respond tuneMeasureLoc
    }

    def create() {
        respond new TuneMeasureLoc(params)
    }

    @Transactional
    def save(TuneMeasureLoc tuneMeasureLoc) {
        if (tuneMeasureLoc == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tuneMeasureLoc.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tuneMeasureLoc.errors, view:'create'
            return
        }

        tuneMeasureLoc.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tuneMeasureLoc.label', default: 'TuneMeasureLoc'), tuneMeasureLoc.id])
                redirect tuneMeasureLoc
            }
            '*' { respond tuneMeasureLoc, [status: CREATED] }
        }
    }

    def edit(TuneMeasureLoc tuneMeasureLoc) {
        respond tuneMeasureLoc
    }

    @Transactional
    def update(TuneMeasureLoc tuneMeasureLoc) {
        if (tuneMeasureLoc == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tuneMeasureLoc.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tuneMeasureLoc.errors, view:'edit'
            return
        }

        tuneMeasureLoc.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tuneMeasureLoc.label', default: 'TuneMeasureLoc'), tuneMeasureLoc.id])
                redirect tuneMeasureLoc
            }
            '*'{ respond tuneMeasureLoc, [status: OK] }
        }
    }

    @Transactional
    def delete(TuneMeasureLoc tuneMeasureLoc) {

        if (tuneMeasureLoc == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tuneMeasureLoc.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tuneMeasureLoc.label', default: 'TuneMeasureLoc'), tuneMeasureLoc.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tuneMeasureLoc.label', default: 'TuneMeasureLoc'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
