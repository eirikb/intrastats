
<%@ page import="no.forsvaret.intrastats.Page" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'page.label', default: 'Page')}" />
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
            <td valign="top" class="name"><g:message code="page.id.label" default="Id" /></td>

        <td valign="top" class="value">${fieldValue(bean: pageInstance, field: "id")}</td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="page.title.label" default="Title" /></td>

        <td valign="top" class="value">${fieldValue(bean: pageInstance, field: "title")}</td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="page.url.label" default="Url" /></td>

        <td valign="top" class="value">${fieldValue(bean: pageInstance, field: "url")}</td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="page.dateCreated.label" default="Date Created" /></td>

        <td valign="top" class="value"><g:formatDate date="${pageInstance?.dateCreated}" /></td>

        </tr>

        <tr class="prop">
          <td valign="top" class="name"><g:message code="page.visits.label" default="Visits" /></td>

        <td valign="top" style="text-align: left;" class="value">
          <ul>
            <g:each in="${pageInstance.visits}" var="v">
              <li><g:link controller="visit" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
            </g:each>
          </ul>
        </td>

        </tr>

        </tbody>
      </table>
    </div>
    <div class="buttons">
      <g:form>
        <g:hiddenField name="id" value="${pageInstance?.id}" />
        <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
      </g:form>
    </div>
  </div>
</body>
</html>
