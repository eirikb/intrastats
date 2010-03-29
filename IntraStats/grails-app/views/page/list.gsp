
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
    <g:render template="/page/listTemplate" model="[pageInstanceList: pageInstanceList, pageInstanceTotal: pageInstanceTotal]" />
  
  </div>
</body>
</html>
