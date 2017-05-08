<%@ page import="cscie56.finalproj.Tune" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tune.label', default: 'Tune')}" />
        <title>Tune Search</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="abcjs-midi.css" media="all" type="text/css" />
        <asset:javascript src="abcjs_plugin_3.1.2-min.js" />
        <%--<asset:javascript src="abcjs_basic_midi_3.1.1-min.js" />--%>
        <g:set var="grailsx" value="-1"/>


        <script type="text/javascript">
            ABCJS.plugin.show_midi = true;
            ABCJS.plugin.hide_abc = true;
            ABCJS.plugin.midi_options = {program: 41};
            var x = 0;
        </script>
    </head>
    <body>

    <nav class="navbar navbar-fixed-top navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">GT Session Compassion</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/tune/index">Top Tunes</a></li>
                    <li class="active"><a href="/tune/search">Search</a></li>
                    <li class="active"><a href="/tune/analysis">Analysis</a></li>
                    <%--<li><a href="http://getbootstrap.com/examples/offcanvas/#about">About</a></li>
                    <li><a href="http://getbootstrap.com/examples/offcanvas/#contact">Contact</a></li>--%>
                </ul>
            </div><!-- /.nav-collapse -->
        </div><!-- /.container -->
    </nav><!-- /.navbar -->
    <style>
    .top-gap { margin-bottom: 1.5cm; }
    .search-gap { margin-bottom: 0.4cm; }
    </style>
    <p class="top-gap" ></p>
    <div class="container">
        <%--<a href="#edit-tune" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--%>
        <%--<div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>--%>

        <div id="edit-tune" class="content scaffold-edit" role="main">
            <h2 id="h2header">Tune Search By Name</h2>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <%--console.log('Search values ' + abcoption ?: 'x')--%>
            <%--<div id="varnames">
                <ul>
                    <li>abcOption: ${abcOption}</li>
                    <li>nameSearch: ${nameSearch}</li>
                    <li>genreSEarch: ${genreSearch}</li>
                    <li>results: ${results?.size()}</li>

                </ul>
            </div>--%>

            <g:form name='searchForm', id="searchIdForm" action="searchByNameGenre">
                Name:   <g:textField name="name" size="50" value="${nameSearch}"/>
                Genre:  <g:select name="genre" from="['','Jig','Hornpipe','Polka','Reel','Slide','Slip-Jig','Waltz']" value="${genreSearch}"/>
            <%--Rhythm: <g:select name="beat" from="['','2/2','3/4','4/4','6/8','Other']" value="0"  />--%>
                ABC Options: <g:select name="abcOption" from="['None','First Bars ABC','First Bars Music', 'Full ABC', 'Full Music']" value="${abcOption}"/>
                <g:submitButton name="update" value="Search" />
            </g:form>

            <%--<div align="right">
            <g:form name='abcViewForm', id="abcViewForm" action="searchDisplay">
                ABC Options: <g:select name="abcOption" from="['None','First Bars ABC','First Bars Music', 'Full ABC', 'Full Music']" value="${abcOption}"/>
                <g:submitButton name="Colors" value="Display" />
            </g:form>
            </div>--%>
            <p class="search-gap"/>
            <g:if test="${results?.size() > 0}">
            <div class="container", id="resultsTable">
                <table class="table table-striped table-bordered table-hover">
                    <tr>
                        <th><g:link action="index" params="[sort:'primaryName', order: 'asc']">Common Name</g:link></th>
                        <th class="hidden-xs"><g:link action="index" params="[sort:'numTunebooks', order: 'asc']"># Tunebooks</g:link></th>
                        <th class="hidden-xs"><g:link action="index" params="[sort:'numRecordings', order: 'asc']"># Recordings</g:link></th>
                        <th><g:link action="index" params="[sort:'dance', order: 'asc']">Dance</g:link></th>
                        <th class="hidden-xs"># Names</th>
                        <th class="hidden-xs"># Versions</th>
                        <g:if test="${abcOption != null && ! abcOption.equals('None')}">
                            <th>Music</th>
                        </g:if>
                    </tr>
                    <g:each in="${results}" var="result">
                        <%--<g:render template="displaySearchResultTune" bean="${cscie56.finalproj.Tune.findById(result.ID)}"  model="['grailsx': '${grailsx}']"/>--%>
                        <g:render template="displaySearchResultTune"   model="['tune':cscie56.finalproj.Tune.findById(result.ID)]"/>
                    </g:each>
                </table>
            </div>
            </g:if>
            <g:else>
                <h4>No records found ${nameSearch} ${genreSearch}</h4>
            </g:else>

        </div>
    </div>
    </body>

<%--<script>
    $(document).ready( function() {
        $('#abcViewForm').change(function() {
            x = x+1;
            <g:set var="grailsx" value="x"/>
            alert("Called with value " + x + " " + ${grailsx})
            $("#resultsTable").load(window.location.)

        });
    });
</script>--%>
</html>
