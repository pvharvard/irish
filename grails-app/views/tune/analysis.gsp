<%@ page import="cscie56.finalproj.Tune" %>
<!DOCTYPE html>
<html>
<head>

    <meta name="layout" content="main"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <g:set var="entityName" value="${message(code: 'tune.label', default: 'Tune')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>

    <meta name="viewport" content="width=device-width; initial-scale=1.0">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

</head>

<body>
<div class="container"></div>
<nav class="navbar navbar-fixed-top navbar-inverse">
    <div>
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
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
.top-gap {
    margin-bottom: 1.5cm;
}

.search-gap {
    margin-bottom: 0.4cm;
}
</style>

<p class="top-gap"></p>

<h2>Analysis</h2>

<p class="top-gap"></p>

<p>
    <%--GenreSearch ${genreSearch} ${genreSearch?.isEmpty()}--%>
</p>

<p class="top-gap"></p>

<div class="container">

    <g:form name='analysisForm' , id="analysisIdForm" action="analyzeByGenre">
        Genre:  <g:select name="genre" from="['', 'Jig', 'Hornpipe', 'Polka', 'Reel', 'Slide', 'Slip-Jig', 'Waltz']"
                          value="${genreSearch}"/>
        <g:submitButton name="update" value="Analyze"/>
    </g:form>

    <p class="top-gap"></p>


    <div>
        <div role="tabpanel">
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#genre" aria-controls="settings" role="tab" data-toggle="tab">Genre</a>
                </li>
                <li role="presentation"><a href="#key" aria-controls="settings" role="tab" data-toggle="tab">Key</a></li>
                <li role="presentation"><a href="#meter" aria-controls="settings" role="tab" data-toggle="tab">Meter</a>
                </li>
                <li role="presentation"><a href="#combined" aria-controls="settings" role="tab" data-toggle="tab">Combined</a></li>
            </ul>

            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="genre">

                    <h3>Genre</h3>

                    <g:if test="${genreResults?.size() > 0}">
                        <table class="table table-striped table-bordered table-hover">
                            <tr>
                                <th>Genre</th>
                                <th>Count</th>
                            </tr>
                            <g:each in="${genreResults}" var="genre">
                                <tr>
                                    <td>${genre.genre}</td>
                                    <td>${genre.count}</td>
                                </tr>
                            </g:each>
                        </table>
                    </g:if>
                    <g:else>
                        <h4>No results</h4>
                    </g:else>
                </div>

                <div role="tabpanel" class="tab-pane" id="key">
                    <h3>Key</h3>
                    <table class="table table-striped table-bordered table-hover">
                        <tr>
                            <th>Key</th>
                            <g:if test="${genreSearch?.isEmpty()}">
                                <th>Genre</th>
                            </g:if>
                            <th>Count</th>
                        </tr>
                        <g:each in="${keyResults}" var="key">
                            <tr>
                                <td>${key.key}</td>
                                <g:if test="${genreSearch?.isEmpty()}">
                                    <th><%--${key.genre}--%>key</th>
                                </g:if>
                                <td>${key.count}</td>
                            </tr>
                        </g:each>
                    </table>
                </div>

                <div role="tabpanel" class="tab-pane" id="meter">
                    <h3>Meter</h3>
                    <table class="table table-striped table-bordered table-hover">
                        <tr>
                            <th>Key</th>
                            <g:if test="${genreSearch?.isEmpty()}">
                                <th>Genre</th>
                            </g:if>
                            <th>Count</th>
                        </tr>
                        <g:each in="${meterResults}" var="meter">
                        <tr>
                            <td>${meter.meter}</td>
                            <g:if test="${ genreSearch?.isEmpty()}">
                                <td>${meter.genre}</td>
                            </g:if>
                            <td>${meter.count}</td>
                        </tr>
                        </g:each>
                    </table>
                </div>

                <div role="tabpanel" class="tab-pane" id="combined">
                    <table class="table table-striped table-bordered table-hover">
                        <tr>
                            <th>Key</th>
                            <th>Hornpipe</th>
                            <th>Jig</th>
                            <th>Reel</th>
                            <th>Hornpipe</th>
                        </tr>
                        <g:each in="${keyResults}" var="meter">
                        <tr>
                            <td>${meter.meter}</td>
                            <td>${meter.genre}</td>
                            <td>${meter.count}</td>
                        </tr>
                        </g:each>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>
