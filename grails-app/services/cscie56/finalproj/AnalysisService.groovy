package cscie56.finalproj

import grails.transaction.Transactional
import groovy.sql.GroovyRowResult
import groovy.sql.Sql

@Transactional
class AnalysisService {

    def serviceMethod() {

    }

    def dataSource



    List<GroovyRowResult> findKeys(String genre) {  // count Keys/dance
        genre = genre ?: ""
        genre = genre.trim().toUpperCase()
        def query = ""

        if (genre.isEmpty()) {
            query = "select COUNT(setting) from tune t, version v GROUP BY key, dance ORDER BY key, dance"  //works
            query = "select COUNT(setting) as count,key,dance as genre  from tune t, version v GROUP BY key, dance ORDER BY key, dance"

        } else {
            query = "select COUNT(setting) as count,key from tune t, version v GROUP BY key  ORDER BY key "
            "where t.dance='${genre}'"
        }

        Sql sql = new Sql(dataSource)
        def results = sql.rows(query)

        println("Query: " + query)
        //def results = sql.rows(query, search: searchString)
        println('Number of results ' + results.size())
        if(genre.isEmpty()) {
            results.each {
                println('Key Result ' + it.key + '\t' + it.genre + '\t' + it.count)
            }
        } else {
            results.each {
                println('Key Result ' + it.key + '\t' + it.count)
            }
        }
        results
    }

    List<GroovyRowResult> findGenreKeyMeters(String genre) {  // count Keys/dance
        genre = genre ?: ""
        genre = genre.trim().toUpperCase()
        def query = ""

        if (genre.isEmpty()) {
            query = "select COUNT(setting) from tune t, version v GROUP BY key, dance ORDER BY key, dance"  //works
            query = "select COUNT(setting) as count,meter,key,dance as genre  from tune t, version v GROUP BY key, meter, dance ORDER BY key, meter, dance"

        } else {
            query = "select COUNT(setting) as count,meter,key from tune t, version v GROUP BY key,meter  ORDER BY key,meter "
            "where t.dance='${genre}'"
        }

        Sql sql = new Sql(dataSource)
        def results = sql.rows(query)

        println("Query: " + query)
        //def results = sql.rows(query, search: searchString)
        println('Number of results ' + results.size())
        if(genre.isEmpty()) {
            results.each {
                println('GenreKeyMeter Result ' + it.key + '\t' + it.meter + '\t' + it.genre + '\t' + it.count)
            }
        } else {
            results.each {
                println('GenreKeyMeter Result ' + it.key + '\t' + it.meter + '\t' + it.count)
            }
        }
        results
    }

    List<GroovyRowResult> findGenres() {  // count Keys/dance
        def query = ""

        query = "select COUNT(setting) as count, dance as genre from tune t, version v GROUP BY dance ORDER BY dance"

        Sql sql = new Sql(dataSource)
        def results = sql.rows(query)

        println("Query: " + query)
        //def results = sql.rows(query, search: searchString)
        println('Number of results ' + results.size())
        results.each {
            println('Genre Result ' + it.genre + '\t' + it.count)
        }

        results
    }


    List<GroovyRowResult> findMeters(String genre) {  // count Keys/dance
        genre = genre ?: ""
        genre = genre.trim().toUpperCase()
        def query = ""

        if (genre.isEmpty()) {
            query = "select COUNT(setting) from tune t, version v GROUP BY key, dance ORDER BY key, dance"  //works
            query = "select COUNT(setting) as count, meter, dance as genre from tune t, version v GROUP BY meter, dance ORDER BY dance, meter"

        } else {
            query = "select COUNT(setting) as count, meter  from tune t, version v GROUP BY meter ORDER BY meter "
            "where t.dance='${genre}'"
        }
        //query = "select COUNT(setting) as count, meter from tune t, version v GROUP BY meter ORDER BY meter"

        Sql sql = new Sql(dataSource)
        def results = sql.rows(query)

        println("Query: " + query)
        //def results = sql.rows(query, search: searchString)
        println('Number of results ' + results.size())
        if (genre.isEmpty()) {
            results.each {
                println('Meter Result ' + it.meter + '\t' + it.genre + '\t' + it.count)
            }
        } else {
            results.each {
                println('Meter Result ' + it.meter + '\t' + it.count)
            }
        }
        results
    }

}
