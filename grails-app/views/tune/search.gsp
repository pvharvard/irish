<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tune.label', default: 'Tune')}" />
        <title>Tune Search</title>
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

            <g:form name='searchForm', id="searchIdForm" action="searchByName">
                Name:   <g:textField name="name" value="" size="50" />
                Genre:  <g:select name="genre" from="['','Jig','Hornpipe','Polka','Reel','Slide','Slip-Jig','Waltz']" value="0" />
                Rhythm: <g:select name="beat" from="['','2/2','3/4','4/4','6/8','Other']" value="0"  />
                <g:submitButton name="update" value="Search" />
            </g:form>

            <g:each var="tuneName" in="${tuneNames}">
                <li>${tuneName.tune.primaryName}</li>
            </g:each>
        </div>
    </body>
</html>
