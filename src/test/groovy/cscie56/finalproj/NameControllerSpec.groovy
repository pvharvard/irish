package cscie56.finalproj

import grails.test.mixin.*
import spock.lang.*

@TestFor(NameController)
@Mock(Name)
class NameControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

       params['name'] = 'name1'
        assert true
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.nameList
            model.nameCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.name!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def name = new Name()
            name.validate()
            controller.save(name)

        then:"The create view is rendered again with the correct model"
            model.name!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            name = new Name(params)

            controller.save(name)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/name/show/1'
            controller.flash.message != null
            Name.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def name = new Name(params)
            controller.show(name)

        then:"A model is populated containing the domain instance"
            model.name == name
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def name = new Name(params)
            controller.edit(name)

        then:"A model is populated containing the domain instance"
            model.name == name
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/name/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def name = new Name()
            name.validate()
            controller.update(name)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.name == name

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            name = new Name(params).save(flush: true)
            controller.update(name)

        then:"A redirect is issued to the show action"
            name != null
            response.redirectedUrl == "/name/show/$name.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/name/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def name = new Name(params).save(flush: true)

        then:"It exists"
            Name.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(name)

        then:"The instance is deleted"
            Name.count() == 0
            response.redirectedUrl == '/name/index'
            flash.message != null
    }
}
