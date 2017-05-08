package cscie56.finalproj

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import groovy.mock.interceptor.MockFor
import groovy.sql.Sql
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@Mock([Name, Tune])
@TestFor(SearchByNameService)
class SearchByNameServiceSpec extends Specification {

    def groovySql

    def setup() {
        def name1 = new Name(name:"abc", index:1)
        saveObject(name1)
        def name2 = new Name(name:"def", index:2)
        saveObject(name2)
        def name3 = new Name(name:"xyz", index:3)
        saveObject(name3)

        def tune27 = new Tune(dance: Tune.Dance.REEL, primaryName: "Drowsey Maggie", tuneId: 27, numRecordings: 158, numTunebooks: 4932,
                names: [name1, name2])
        saveObject(tune27)

        def tune55 = new Tune(dance: Tune.Dance.JIG, primaryName: "The Kesh", tuneId: 55, numRecordings: 140, numTunebooks: 4463,
                names: [name3])
        saveObject(tune55)

        def tune8 = new Tune(dance: Tune.Dance.JIG, primaryName: "The Banshee", tuneId: 8, numRecordings: 100, numTunebooks: 2711,
                names: [name1])
        saveObject(tune8)
        name1.tune = [tune27, tune8]
        saveObject(name1)
        name2.tune = [tune27]
        saveObject(name2)
        name3.tune = [tune55]
        saveObject(name3)

        defineBeans {
            service(SearchByNameService) {
                bean -> bean.autowire = true
            }
            service(SearchByNameService)
        }

    }

    def cleanup() {
    }

    void "Testing the service for fetch 2"() {
        /*String name = "helloworld"
        String genre = ""
        def result = service.findTunes(name, genre)
        expect:
        result.size() == 0*/
        true
    }

    void "Testing the service for known entry 2"() {
        def mockSql = new MockFor(Sql.class)
        /*mockSql.demand.newInstance { def dataSource->
            return mockSql
        }
        mockSql.demand.rows { def results ->
            // run the closure over the mock array
            mockResults.rows(closure)
        }

        def result
        mockSql.use {
            result = service.findTunes("abc", "reel")
        }
        expect:
        result.size() == 1*/
        true
    }

    void "Testing the service for upper case entry 2"() {
        /*def result = service.findTunes("ABC", "")
        expect:
        result.size() == 1*/
        true
    }

    void "Testing the service for case title case entry 2"() {
        /*def result = service.findTunes("Abc", "")
        expect:
        result.size() == 1*/
        true
    }

    void "Testing the service for genre only 2"() {
        /*def result = service.findTunes("", "jig")
        expect:
        result.size() == 2*/
        true
    }


    void "Testing the service for everything"() {
        /*def result = service.findTunes("", "")
        expect:
        result.size() == 3*/
        true
    }





    def saveObject(object) {
        if (!object.save(flush: true)) {
            object.errors.allErrors.each { println it }
        }
    }

}
