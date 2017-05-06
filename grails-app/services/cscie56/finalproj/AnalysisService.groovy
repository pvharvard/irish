package cscie56.finalproj

import grails.transaction.Transactional
import groovy.sql.GroovyRowResult
import groovy.sql.Sql

@Transactional
class AnalysisService {

    def serviceMethod() {

    }

    List<GroovyRowResult> findTunes(String genre) {
        tuneName = tuneName ?: ""
        genre = genre ?: ""
        tuneName = tuneName.trim().toUpperCase()
        genre = genre.trim().toUpperCase()

        if (genre.isEmpty() && tuneName.isEmpty()) {
            return []
        }
        String query
        if(genre.isEmpty()) {
            query = "select distinct t.id from tune t " +
                    "inner join tune_names tn on t.id = tn.tune_id " +
                    "inner join name n on tn.name_id = n.id " +
                    "where UPPER(n.name) like '%${tuneName}%'"
        } else if(tuneName.isEmpty()) {
            query = "select distinct t.id from tune t " +
                    "inner join tune_names tn on t.id = tn.tune_id " +
                    "inner join name n on tn.name_id = n.id " +
                    "where t.dance = '${genre}'"
        } else {
            query = "select distinct t.id from tune t " +
                    "inner join tune_names tn on t.id = tn.tune_id " +
                    "inner join name n on tn.name_id = n.id " +
                    "where t.dance = '${genre}' and UPPER(n.name) like '%${tuneName}%'"
        }

        Sql sql = new Sql(dataSource)
        def results = sql.rows(query)

        println("Query: " + query)
        //def results = sql.rows(query, search: searchString)
        println('Number of results ' + results.size())
        results.each {
            println('Result ' + it)
        }

        results.each {
            println('Result ' + it.ID)
        }
        results
    }
}
