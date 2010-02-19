
<%@ page import="no.forsvaret.intrastats.Client" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'client.label', default: 'Client')}" />
  <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
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

        <g:sortableColumn property="id" title="${message(code: 'client.id.label', default: 'Id')}" />

        <g:sortableColumn property="userAgent" title="${message(code: 'client.userAgent.label', default: 'User Agent')}" />

        <g:sortableColumn property="remoteAddress" title="${message(code: 'client.remoteAddress.label', default: 'Remote Address')}" />

        <g:sortableColumn property="remoteHost" title="${message(code: 'client.remoteHost.label', default: 'Remote Host')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${clientInstanceList}" status="i" var="clientInstance">
          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

            <td><g:link action="show" id="${clientInstance.id}">${fieldValue(bean: clientInstance, field: "id")}</g:link></td>

          <td>${fieldValue(bean: clientInstance, field: "userAgent")}</td>

          <td>${fieldValue(bean: clientInstance, field: "remoteAddress")}</td>

          <td>${fieldValue(bean: clientInstance, field: "remoteHost")}</td>

          </tr>
        </g:each>
        </tbody>
      </table>
    </div>
    <div class="paginateButtons">
      <g:paginate total="${clientInstanceTotal}" />
    </div>
  </div>
</body>
</html>
