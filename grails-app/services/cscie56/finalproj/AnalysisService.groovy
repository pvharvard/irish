package cscie56.finalproj

import grails.transaction.Transactional
import groovy.sql.GroovyRowResult
import groovy.sql.Sql

@Transactional
class AnalysisService {

    def dataSource



    List<GroovyRowResult> findKeys(String genre) {  // count Keys/dance
        genre = genre ?: ""
        genre = genre.trim().toUpperCase()
        def query = ""

        if (genre.isEmpty()) {
            query = "select COUNT(setting) from tune t, version v GROUP BY key, dance ORDER BY key, dance"  //works
            query = "select COUNT(setting) as count,key,dance as genre from tune t, version v GROUP BY key, dance ORDER BY dance, key" // what we had
            query = "select t.id as count,key,dance as genre from tune t, version v  " +
                    "where t.id = tune_version.tune_versions_id and v.id = tune_version.version_id"
            query = "select t.id as count,key,dance as genre from tune t join version v "
            query = "select t.id as count,key,dance as genre from tune t inner join tune_version tv on t.id = tv.tune_versions_id inner join version v on tv.version_id = v.id"

            query = "select t.id, v.id from tune t " +
                    "inner join tune_version tv on t.id = tv.tune_versions_id " +
                    "inner join version v on tv.version_id = v.id "
            query = "select count(v.id) as count,key,dance as genre from tune t " +
                    "inner join tune_version tv on t.id = tv.tune_versions_id " +
                    "inner join version v on tv.version_id = v.id GROUP BY key, dance ORDER by dance,key"

        } else {
            query = "select COUNT(setting) as count,key from tune t, version v GROUP BY key  ORDER BY key "
            "where t.dance='${genre}'"
            query = "select count(v.id) as count,key from tune t " +
                    "inner join tune_version tv on t.id = tv.tune_versions_id " +
                    "inner join version v on tv.version_id = v.id " +
                    "where t.dance='${genre}' " +
                    "GROUP BY key ORDER by key "
        }

        Sql sql = new Sql(dataSource)
        def results = sql.rows(query)

        /*println("Query: " + query)
        println('Number of results ' + results.size())
        if(genre.isEmpty()) {
            results.each {
                println('Key Result ' + it.key + '\t' + it.genre + '\t' + it.count)
            }
        } else {
            results.each {
                println('Key Result ' + it.key + '\t' + it.count)
            }
        }*/
        results
    }

    List<GroovyRowResult> findGenreKeyMeters(String genre) {  // count Keys/dance
        genre = genre ?: ""
        genre = genre.trim().toUpperCase()
        def query = ""

        if (genre.isEmpty()) {
            query = "select COUNT(setting) from tune t, version v GROUP BY key, dance ORDER BY key, dance"  //works
            query = "select COUNT(setting) as count,meter,key,dance as genre  from tune t, version v GROUP BY key, meter, dance ORDER BY dance, meter, key"
            query = "select count(v.id) as count,meter,key,dance as genre from tune t " +
                    "inner join tune_version tv on t.id = tv.tune_versions_id " +
                    "inner join version v on tv.version_id = v.id GROUP BY key, meter, dance ORDER by dance,meter,key"

        } else {
            query = "select COUNT(setting) as count,meter,key from tune t, version v GROUP BY key,meter  ORDER BY meter, key"
            "where t.dance='${genre}'"
            query = "select count(v.id) as count,meter,key from tune t " +
                    "inner join tune_version tv on t.id = tv.tune_versions_id " +
                    "inner join version v on tv.version_id = v.id " +
                    "where t.dance='${genre}' " +
                    " GROUP BY key, meter ORDER by meter,key "
        }

        Sql sql = new Sql(dataSource)
        def results = sql.rows(query)

        /*println("Query: " + query)
        println('Number of results ' + results.size())
        if(genre.isEmpty()) {
            results.each {
                println('GenreKeyMeter Result ' + it.key + '\t' + it.meter + '\t' + it.genre + '\t' + it.count)
            }
        } else {
            results.each {
                println('GenreKeyMeter Result ' + it.key + '\t' + it.meter + '\t' + it.count)
            }
        }*/
        results
    }

    List<GroovyRowResult> findGenres() {  // count Keys/dance
        def query = ""

        query = "select COUNT(setting) as count, dance as genre from tune t, version v GROUP BY dance ORDER BY dance"
        query = "select count(v.id) as count,dance as genre from tune t " +
                "inner join tune_version tv on t.id = tv.tune_versions_id " +
                "inner join version v on tv.version_id = v.id GROUP BY dance ORDER by dance"

        Sql sql = new Sql(dataSource)
        def results = sql.rows(query)

        /*println("Query: " + query)
        println('Number of results ' + results.size())
        results.each {
            println('Genre Result ' + it.genre + '\t' + it.count)
        }*/

        results
    }


    List<GroovyRowResult> findMeters(String genre) {  // count Keys/dance
        genre = genre ?: ""
        genre = genre.trim().toUpperCase()
        def query = ""

        if (genre.isEmpty()) {
            query = "select count(v.id) as count, meter, dance as genre from tune t " +
                    "inner join tune_version tv on t.id = tv.tune_versions_id " +
                    "inner join version v on tv.version_id = v.id GROUP BY dance, meter ORDER by dance, meter"

        } else {
            query = "select count(v.id) as count,meter from tune t " +
                    "inner join tune_version tv on t.id = tv.tune_versions_id " +
                    "inner join version v on tv.version_id = v.id " +
                    "where t.dance='${genre}' " +
                    "GROUP BY meter ORDER by meter "
        }
        //query = "select COUNT(setting) as count, meter from tune t, version v GROUP BY meter ORDER BY meter"

        Sql sql = new Sql(dataSource)
        def results = sql.rows(query)

        /*println("Query: " + query)
        println('Number of results ' + results.size())
        if (genre.isEmpty()) {
            results.each {
                println('Meter Result ' + it.meter + '\t' + it.genre + '\t' + it.count)
            }
        } else {
            results.each {
                println('Meter Result ' + it.meter + '\t' + it.count)
            }
        }*/
        results
    }

}
