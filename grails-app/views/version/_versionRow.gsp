<tr>
    <%--<td class="hidden-xs">${it.index}</td>--%>
    <td class="hidden-xs"><g:link url="https://thesession.org/tunes/${tune.tuneId}#setting${it.setting}">${it.index}</g:link></td>
    <td>${it.key}</td>
    <td class="hidden-xs">${it.meter}</td>
    <td class="hidden-xs">${it.unitLength}</td>

    <g:if test="${abcOption.equals('First Bars ABC')}">
        <%--<th class="hidden-xs">${raw(tune.version1StartAbc)}</th>--%>
        <th class="hidden-xs">${raw(it.startAbc)}</th>
    </g:if>
    <g:elseif test="${abcOption.equals('First Bars Music')}">
        <th class="hidden-xs"><pre>X:1<br/>K:${it.key}<br/>M:${it.meter}<br/>${raw(it.startAbc)}</pre></th>
    </g:elseif>
    <g:elseif test="${abcOption.equals('Full ABC')}">
        <th class="hidden-xs">${raw(it.abc)}</th>
    </g:elseif>
    <g:elseif test="${abcOption.equals('Full Music')}">
        <th class="hidden-xs"><pre>X:1<br/>K:${it.key}<br/>M:${it.meter}<br/>${raw(it.abc)}</pre></th>
    </g:elseif>

    <%--<td><pre>X:1<br/>${it.startAbc}</pre></td>
    <td><pre>X:3<br/>${it.abc}</pre></td>--%>
</tr>


