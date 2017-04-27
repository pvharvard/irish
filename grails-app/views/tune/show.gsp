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
        <a href="#show-tune" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-tune" class="content scaffold-show" role="main">
            <%--<h1><g:message code="default.show.label" args="[entityName]" /></h1>--%>
            <h1>
                ${tune.primaryName}
                <g:if test="! ${tune.dance}.isEmpty()">
                    (<g:danceTag dance="${tune.dance}"/>)
                </g:if>
            </h1>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div class="container">
            <div id="column1" class="col-sm-6">
                <h3>Alternative Names</h3>
                <ul>
                    <g:each in="${tune.names.sort()}" var="othername">
                        <li>${othername?.name}</li>
                    </g:each>
                </ul>
            </div>

            <div id="column2" class="col-sm-6">
                <h3>Characteristics
                </h3>
                <ul>
                    <li>#Tunebooks:  ${tune.numTunebooks}</li>
                    <li>#Recordings: ${tune.numRecordings}</li>
                    <li><g:link url="https://thesession.org/tunes/${tune.tuneId}">Link to the Session.org</g:link></li>
                </ul>
            </div>
            </div>

            <table class="table table-striped table-bordered table-hover">
                <tr>
                    <th class="hidden-xs">Index</th>
                    <th>Key</th>
                    <th class="hidden-xs">Meter</th>
                    <th class="hidden-xs">Unit Length</th>
                    <th>ABC Start</th>
                    <th>ABC</th>
                </tr>
                <g:each in="${tune.versions.sort()}" var="version">
                    <g:render template="/version/versionRow" bean="${version}"/>
                </g:each>
            </table>

            <%--<f:display bean="tune" />--%>
            <g:form resource="${this.tune}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.tune}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
        <hr/>
    <div id="notation">
        Other notation
        <pre>X:4<br/>${tune?.versions[0]?.abc}</pre>
    </div>


    </div>

    <div id="print-friendly">
        X: 2
        T: Cooley's
        M: 4/4
        L: 1/8
        R: reel
        K: Emin
        D2|:"Em"EB{c}BA B2 EB|~B2 AB dBAG|"D"FDAD BDAD|
    </div>


   <p id="notation2">

        ${raw('X:3\nT: Cooley\'s\nM: 4/4\nL: 1/8\nR: reel\nK: Emin\nD2|:\"Em\"EB{c}BA B2 EB|~B2 AB dBAG|\"D\"FDAD BDAD|FDAD dAFD|\n\"Em\"EBBA B2 EB|B2 AB defg|\"D\"afe^c dBAF|1\"Em\"DEFD E2 D2:|2"Em"DEFD E2 gf||\n|:\"Em\"eB B2 efge|eB B2 gedB|\"D\"A2 FA DAFA|A2 FA defg|\n\"Em\"eB B2 eBgB|eB B2 defg|\"D\"afe^c dBAF|1\"Em\"DEFD E2 gf:|2\"Em\"DEFD E4|]\n'.replaceAll('\n','<br/>'))}

    </p>


    <p id="print-friendly">
        X: 3<br/>
        M: 3/4<br/>
        L: 1/8<br/>
        K: Emin<br/>
        D2|:"Em"EB{c}BA B2 EB|~B2 AB dBAG|<br/>
        "D"FDAD BDAD|
    </p>

    </body>
</html>
