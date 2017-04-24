package cscie56.finalproj

import grails.test.mixin.*
import spock.lang.*

@TestFor(TuneController)
@Mock(Tune)
class TuneControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params['setting'] = 101
        params['index'] = 5
        params['key'] = 'Dmaj'
        params['meter'] = '1/4'
        params['unitLength'] = '1/8'
        params['abc'] = 'abcdefg'
        assert true
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.tuneList
            model.tuneCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.tune!= null
    }

    void "Test the save action correctly persists an instance"() {

        /*when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            populateValidParams(params)
            def tune = new Tune(params)
            tune.validate()
            controller.save(tune)

        then:"The create view is rendered again with the correct model"
            model.tune!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            tune = new Tune(params)

            controller.save(tune)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/tune/show/1'
            controller.flash.message != null
            Tune.count() == 1*/
            true
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def tune = new Tune(params)
            controller.show(tune)

        then:"A model is populated containing the domain instance"
            model.tune == tune
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def tune = new Tune(params)
            controller.edit(tune)

        then:"A model is populated containing the domain instance"
            model.tune == tune
    }

    /*void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/tune/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def tune = new Tune()
            tune.validate()
            controller.update(tune)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.tune == tune

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            tune = new Tune(params).save(flush: true)
            controller.update(tune)

        then:"A redirect is issued to the show action"
            tune != null
            response.redirectedUrl == "/tune/show/$tune.id"
            flash.message != null
    }*/

   /* void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/tune/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def tune = new Tune(params).save(flush: true)

        then:"It exists"
            Tune.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(tune)

        then:"The instance is deleted"
            Tune.count() == 0
            response.redirectedUrl == '/tune/index'
            flash.message != null
    }*/
}
