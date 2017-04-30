package cscie56.finalproj

import com.testapp.Role
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


@Secured([Role.ROLE_ADMIN])
@Transactional(readOnly = true)
class VersionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured([Role.ROLE_ANONYMOUS])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Version.list(params), model:[versionCount: Version.count()]
    }

    @Secured([Role.ROLE_ANONYMOUS])
    def show(Version version) {
        respond version
    }

    def create() {
        respond new Version(params)
    }

    @Transactional
    def save(Version version) {
        if (version == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (version.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond version.errors, view:'create'
            return
        }

        version.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'version.label', default: 'Version'), version.id])
                redirect version
            }
            '*' { respond version, [status: CREATED] }
        }
    }

    def edit(Version version) {
        respond version
    }

    @Transactional
    def update(Version version) {
        if (version == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (version.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond version.errors, view:'edit'
            return
        }

        version.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'version.label', default: 'Version'), version.id])
                redirect version
            }
            '*'{ respond version, [status: OK] }
        }
    }

    @Transactional
    def delete(Version version) {

        if (version == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        version.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'version.label', default: 'Version'), version.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    @Secured([Role.ROLE_ANONYMOUS])
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'version.label', default: 'Version'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
