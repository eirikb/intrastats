
<%@ page import="no.forsvaret.intrastats.Page" %>
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
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
  </div>
  <div class="body">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
      <table>
        <thead>
          <tr>
        <g:sortableColumn property="id" title="${message(code: 'page.id.label', default: 'Id')}" />
        <g:sortableColumn property="url" title="${message(code: 'page.url.label', default: 'Url')}" />
        <g:sortableColumn property="title" title="${message(code: 'page.title.label', default: 'Title')}" />
        <g:sortableColumn property="visits.size" title="${message(code: 'page.url.label', default: 'Visits')}" />
        <g:sortableColumn property="dateCreated" title="${message(code: 'page.dateCreated.label', default: 'Date Created')}" />
        <g:sortableColumn property="lastUpdated" title="${message(code: 'page.lastUpdated.label', default: 'Last Updated')}" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${pageInstanceList}" status="i" var="pageInstance">
          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

            <td><g:link action="show" id="${pageInstance[0]}">${pageInstance[0]}</g:link></td>
          <td><g:link action="show" id="${pageInstance[0]}">${pageInstance[1]}</g:link></td>
          <td><g:link action="show" id="${pageInstance[0]}">${pageInstance[2]}</g:link></td>
          <td><g:link action="show" id="${pageInstance[0]}">${pageInstance[5]}</g:link></td>
          <td><g:formatDate date="${pageInstance[3]}" /></td>
          <td><g:formatDate date="${pageInstance[4]}" /></td>
          </tr>
        </g:each>
        </tbody>
      </table>
    </div>
    <div class="paginateButtons">
      <g:paginate total="${pageInstanceTotal}" />
    </div>
  </div>
</body>
</html>
