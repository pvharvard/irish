<tr>
    <th><g:link action="show" id="${tune.id}">${tune.primaryName}</g:link></th>
    <th class="hidden-xs">${tune.numTunebooks}</th>
    <th class="hidden-xs">${tune.numRecordings}</th>
    <th><g:danceTag dance="${tune.dance}"/></th>
    <th class="hidden-xs">${tune.names.size()}</th>
    <th class="hidden-xs">${tune.versions.size()}</th>
    <%--<th class="hidden-xs"><pre>X:1<br/>${raw(it.version1StartAbc)}</pre></th>--%>
    <g:if test="${abcOption.equals('First Bars ABC')}">
        <th class="hidden-xs">${raw(tune.version1StartAbc)}</th>
    </g:if>
    <g:elseif test="${abcOption.equals('First Bars Music')}">
        <th class="hidden-xs"><pre>X:1<br/>${raw(tune.version1StartAbc)}</pre></th>
    </g:elseif>
    <g:elseif test="${abcOption.equals('Full ABC')}">
        <th class="hidden-xs">T:The Kesh<br/>M:6/8<br/>K: Gmaj<br/>L:1/8<br/>${raw(tune.version1Abc)}</th>
    </g:elseif>
    <g:elseif test="${abcOption.equals('Full Music')}">
        <th class="hidden-xs"><pre>X:1<br/>T:The Kesh<br/>M:6/8<br/>K: Gmaj<br/>L:1/8<br/>${raw(tune.version1Abc)}</pre></th>
    </g:elseif>

</tr>
