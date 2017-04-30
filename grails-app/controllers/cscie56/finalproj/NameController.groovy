package cscie56.finalproj

import com.testapp.Role
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured([Role.ROLE_ADMIN])
@Transactional(readOnly = true)
class NameController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured([Role.ROLE_ANONYMOUS])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Name.list(params), model:[nameCount: Name.count()]
    }

    @Secured([Role.ROLE_ANONYMOUS])
    def show(Name name) {
        respond name
    }

    def create() {
        respond new Name(params)
    }

    @Transactional
    def save(Name name) {
        if (name == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (name.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond name.errors, view:'create'
            return
        }

        name.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'name.label', default: 'Name'), name.id])
                redirect name
            }
            '*' { respond name, [status: CREATED] }
        }
    }

    def edit(Name name) {
        respond name
    }

    @Transactional
    def update(Name name) {
        if (name == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (name.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond name.errors, view:'edit'
            return
        }

        name.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'name.label', default: 'Name'), name.id])
                redirect name
            }
            '*'{ respond name, [status: OK] }
        }
    }

    @Transactional
    def delete(Name name) {

        if (name == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        name.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'name.label', default: 'Name'), name.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    @Secured([Role.ROLE_ANONYMOUS])
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'name.label', default: 'Name'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
