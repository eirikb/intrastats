<div class="list">
  <p>Items: ${pageInstanceTotal}</p>
  <g:each var="i" in="${ [10, 25, 50, 100] }">
    <g:link action="${params.action}" id="${params.id}" params="[max:i]">${i}</g:link>
  </g:each>

  <table>
    <thead>
      <tr>
    <g:sortableColumn property="url"  params="[max: params.max]" title="Url (open in new tab)" />
    <g:sortableColumn property="title"  params="[max: params.max]" title="${message(code: 'page.title.label', default: 'Title')}" />
    <g:sortableColumn property="count(pvr)"  params="[max: params.max]" title="${message(code: 'page.url.label', default: 'Visits')}" />
    <g:sortableColumn property="p.dateCreated"  params="[max: params.max]" title="${message(code: 'page.dateCreated.label', default: 'Date Created')}" />
    <td><b>Show</b></td>
    </tr>
    </thead>
    <tbody>
    <g:each in="${pageInstanceList}" status="i" var="pageInstance">
      <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
        <td><a href="${pageInstance[1]}" target="_blank">${pageInstance[1]}</a></td>
        <td>${pageInstance[2]}</td>
        <td>${pageInstance[4]}</td>
        <td><g:formatDate date="${pageInstance[3]}" /></td>
      <td><g:link action="show" id="${pageInstance[0]}">Show</g:link></td>
      </tr>
    </g:each>
    </tbody>
  </table>
</div>
<div class="paginateButtons">
  <g:paginate total="${pageInstanceTotal}" action="${params.action}" id="${params.id}" />
</div>