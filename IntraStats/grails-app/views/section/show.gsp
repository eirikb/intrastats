
<%@ page import="no.forsvaret.intrastats.Section" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'section.label', default: 'Section')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
  </div>
  <div class="body">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
      <table>
        <tbody>

          <tr class="prop">
            <td valign="top" class="name"><g:message code="section.id.label" default="Id" /></td>
        <td valign="top" class="value">${fieldValue(bean: sectionInstance, field: "id")}</td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="section.name.label" default="Name" /></td>
        <td valign="top" class="value">${fieldValue(bean: sectionInstance, field: "name")}</td>

        </tr>
        <tr class="prop">
          <td valign="top" class="name"><g:message code="section.dateCreated.label" default="Date Created" /></td>
        <td valign="top" class="value"><g:formatDate date="${sectionInstance?.dateCreated}" /></td>
        </tr>
        </tbody>
      </table>
    </div>
    <h3>Visits: ${visitCount}</h3>
    From:
    <g:form controller="section" action="show" id="${params.id}" method="get">
      <g:datePicker name="fromDate" value="${new Date() - 365}" precision="day"/>
      To:
      <g:datePicker name="toDate" value="${new Date()}" precision="day"/>
      <br />
      <g:submitButton name="submit" value="Calculate visits, yo" />
    </g:form>
  </div>
</body>
</html>
