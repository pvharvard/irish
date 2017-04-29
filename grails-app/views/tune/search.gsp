<%@ page import="cscie56.finalproj.Tune" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tune.label', default: 'Tune')}" />
        <title>Tune Search</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    </head>
    <body>
        <%--<a href="#edit-tune" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--%>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-tune" class="content scaffold-edit" role="main">
            <h1>Tune Search By Name</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:form name='searchForm', id="searchIdForm" action="searchByNameGenre">
                Name:   <g:textField name="name" size="50" />
                Genre:  <g:select name="genre" from="['','Jig','Hornpipe','Polka','Reel','Slide','Slip-Jig','Waltz']" />
                <%--Rhythm: <g:select name="beat" from="['','2/2','3/4','4/4','6/8','Other']" value="0"  />--%>
                <g:submitButton name="update" value="Search" />
            </g:form>

            <g:if test="${results?.size() > 0}">
            <div class="container">
                <table class="table table-striped table-bordered table-hover">
                    <tr>
                        <th><g:link action="index" params="[sort:'primaryName', order: 'asc']">Common Name</g:link></th>
                        <th class="hidden-xs"><g:link action="index" params="[sort:'numTunebooks', order: 'asc']"># Tunebooks</g:link></th>
                        <th class="hidden-xs"><g:link action="index" params="[sort:'numRecordings', order: 'asc']"># Recordings</g:link></th>
                        <th><g:link action="index" params="[sort:'dance', order: 'asc']">Dance</g:link></th>
                        <th class="hidden-xs"># Names</th>
                        <th class="hidden-xs"># Versions</th>
                    </tr>
                    <g:each in="${results}" var="result">
                        <g:render template="displaySearchResultTune" bean="${cscie56.finalproj.Tune.findById(result.ID)}"/>
                    </g:each>
                </table>
            </div>
            </g:if>
            <g:else>
                <h4>No records found ${nameSearch} ${genreSearch}</h4>
            </g:else>

        </div>
    </body>
</html>
