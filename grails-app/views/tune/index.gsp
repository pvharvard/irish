<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tune.label', default: 'Tune')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
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