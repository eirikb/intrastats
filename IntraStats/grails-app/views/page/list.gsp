
<%@ page import="no.forsvaret.intrastats.Page" %>
<%@ page import="no.forsvaret.intrastats.Section" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'page.label', default: 'Page')}" />
  <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    <g:if test="${params.id}">
      <span class="menuButton"><g:link class="list" controller="page" action="list">All pages</g:link></span>
      <span class="menuButton"><g:link class="list" controller="section" id="${params.id}" action="show">Back to parent section</g:link></span>
    </g:if>
  </div>
  <div class="body">
    <g:if test="${params.id}">
      <h1>Pages for section ${Section.get(params.id)?.name}</h1>
    </g:if>
    <g:else>
      <h1>All pages</h1>
    </g:else>

    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
      <p>Items: ${pageInstanceTotal}</p>
      <g:each var="i" in="${ [10, 25, 50, 100] }">
        <g:link action="list" id="${params.id}" params="[max:i]">${i}</g:link>
      </g:each>

      <table>
        <thead>
          <tr>
        <g:sortableColumn property="url"  params="[max: params.max]" title="Url (open in new tab)" />
        <g:sortableColumn property="title"  params="[max: params.max]" title="${message(code: 'page.title.label', default: 'Title')}" />
        <g:sortableColumn property="count(pvr)"  params="[max: params.max]" title="${message(code: 'page.url.label', default: 'Visits')}" />
        <g:sortableColumn property="p.dateCreated"  params="[max: params.max]" title="${message(code: 'page.dateCreated.label', default: 'Date Created')}" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${pageInstanceList}" status="i" var="pageInstance">
          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            <td><a href="${pageInstance[1]}" target="_blank">${pageInstance[1]}</a></td>
            <td>${pageInstance[2]}</td>
            <td>${pageInstance[4]}</td>
            <td><g:formatDate date="${pageInstance[3]}" /></td>
          </tr>
        </g:each>
        </tbody>
      </table>
    </div>
    <div class="paginateButtons">
      <g:paginate total="${pageInstanceTotal}" id="${params.id}" />
    </div>
  </div>
</body>
</html>
