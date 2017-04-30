<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tune.label', default: 'Tune')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    </head>
    <body>
        <%--<a href="#list-tune" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>--%>
        <div id="list-tune" class="content scaffold-list" role="main">
            <h1>Tunes Ranked by Popularity</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

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
                <g:each in="${tuneList}" var="tune">
                    <g:render template="displayIndexTune" bean="${tune}"/>
                </g:each>
            </table>
            </div>
            <%--<f:table collection="${tuneList}" />--%>

            <div class="pagination">
                <g:paginate total="${tuneCount ?: 0}" />
            </div>
        </div>
    </body>
</html>