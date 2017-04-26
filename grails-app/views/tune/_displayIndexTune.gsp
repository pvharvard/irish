<tr>
    <th><g:link action="show" id="${it.id}">${it.primaryName}</g:link></th>
    <th class="hidden-xs">${it.numTunebooks}</th>
    <th class="hidden-xs">${it.numRecordings}</th>
    <th><g:danceTag dance="${it.dance}"/></th>
    <th class="hidden-xs">${it.names.size()}</th>
    <th class="hidden-xs">${it.versions.size()}</th>
</tr>