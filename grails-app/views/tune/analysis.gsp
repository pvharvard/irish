<%@ page import="cscie56.finalproj.Tune" %>
<!DOCTYPE html>
<html>
    <head>


        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tune.label', default: 'Tune')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <%--<script src="https://use.fontawesome.com/491e16d960.js"></script>--%>
        <link href="abcjs-midi.css" media="all" rel="stylesheet" type="text/css" />
        <%--<g:javascript src="abcjs_basic_midi_3.1.1-min.js"/>--%>
        <g:javascript src="abcjs_plugin_3.1.2-min.js" />
        <asset:javascript src="abcjs_plugin_3.1.2-min.js" />
        <asset:javascript src="abcjs_basic_midi_3.1.1-min.js" />
        <r:layoutResources/>

        <<%--link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <link href="abcjs-midi.css" media="all" rel="stylesheet" type="text/css" />
        <g:javascript src="abcjs_editor_midi_3.1.1-min.js"/>
        <style>
        .abcjs-inline-midi {
            max-width: 740px;
        }
        </style>--%>
        <%--<style>
        .abcjs-inline-midi {
            max-width: 400px;
        }
        </style>--%>


        <script type="text/javascript">

            ABCJS.plugin.show_midi = true;
            ABCJS.plugin.hide_abc = true;

        </script>


        <%--<script type="text/javascript">
            var cooleys = 'X:1\nT: Cooley\'s\nM: 4/4\nL: 1/8\nR: reel\nK: Emin\nD2|:"Em"EB{c}BA B2 EB|~B2 AB dBAG|"D"FDAD BDAD|FDAD dAFD|\n"Em"EBBA B2 EB|B2 AB defg|"D"afe^c dBAF|1"Em"DEFD E2 D2:|2"Em"DEFD E2 gf||\n|:"Em"eB B2 efge|eB B2 gedB|"D"A2 FA DAFA|A2 FA defg|\n"Em"eB B2 eBgB|eB B2 defg|"D"afe^c dBAF|1"Em"DEFD E2 gf:|2"Em"DEFD E4|]\n';
            var chorus = '%%staffwidth 500\nX: 1\nT: Chorus\nV: T1 clef=treble name="Soprano"\nV: T2 clef=treble name="Alto"\nV: B1 clef=bass name="Tenor"\nV: B2 clef=bass name="Bass"\nL:1/8\nK:G\nP:First Part\n[V: T1]"C"ed"Am"ed "F"cd"G7"gf |\n[V: T2]GGAA- A2BB |\n[V: B1]C3D- DF,3 |\n[V: B2]C,2A,,2 F,,2G,,2 |';
            var bigNotes = "X:1\nM: 4/4\nL: 1/8\nK: Emin\n|:D2|EB{c}BA B2 EB|\n";
        </script>--%>
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

    <h2>Analysis</h2>
    <p class="top-gap" ></p>
    <p class="top-gap" ></p>
    <h3>Coming soon...</h3>

    <%--<a href="#show-tune" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>--%>




    </body>
</html>
