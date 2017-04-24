package cscie56.finalproj

import grails.test.mixin.*
import spock.lang.*

@TestFor(VersionController)
@Mock(Version)
class VersionControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params['setting'] = 26404
        params['key'] = "Gmaj"
        params['meter'] = '6/8'
        params['unitLength'] = '1/8'
        params['index'] = 10
        params['abc'] = '|:BGG dGG | ege dBA | BGG dGG | ~A3 ABc |\n' +
                        'BGG dGG | ege dBd | gbg aga | bgg g2d :|\n' +
                        '|:G2F GAB | A2G ABd | edd gdd | edB dBA |\n' +
                        'G2F GAB | A2G ABd | edd gdB |1 AGF GBA :|2 AGF G2A |'

        assert true
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.versionList
            model.versionCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.version!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def version = new Version()
            version.validate()
            controller.save(version)

        then:"The create view is rendered again with the correct model"
            model.version!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            version = new Version(params)

            controller.save(version)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/version/show/1'
            controller.flash.message != null
            Version.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def version = new Version(params)
            controller.show(version)

        then:"A model is populated containing the domain instance"
            model.version == version
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def version = new Version(params)
            controller.edit(version)

        then:"A model is populated containing the domain instance"
            model.version == version
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/version/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def version = new Version()
            version.validate()
            controller.update(version)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.version == version

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            version = new Version(params).save(flush: true)
            controller.update(version)

        then:"A redirect is issued to the show action"
            version != null
            response.redirectedUrl == "/version/show/$version.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/version/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def version = new Version(params).save(flush: true)

        then:"It exists"
            Version.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(version)

        then:"The instance is deleted"
            Version.count() == 0
            response.redirectedUrl == '/version/index'
            flash.message != null
    }
}
