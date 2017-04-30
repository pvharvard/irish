<tr>
    <%--<td class="hidden-xs">${it.index}</td>--%>
    <td class="hidden-xs"><g:link url="https://thesession.org/tunes/${tune.tuneId}#setting${it.setting}">${it.index}</g:link></td>
    <td>${it.key}</td>
    <td class="hidden-xs">${it.meter}</td>
    <td class="hidden-xs">${it.unitLength}</td>

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

    <%--<td><pre>X:1<br/>${it.startAbc}</pre></td>
    <td><pre>X:3<br/>${it.abc}</pre></td>--%>
</tr>


