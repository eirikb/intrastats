
<%@ page import="no.forsvaret.intrastats.Client" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'client.label', default: 'Client')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${clientInstance}">
            <div class="errors">
                <g:renderErrors bean="${clientInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${clientInstance?.id}" />
                <g:hiddenField name="version" value="${clientInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="userAgent"><g:message code="client.userAgent.label" default="User Agent" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: clientInstance, field: 'userAgent', 'errors')}">
                                    <g:textField name="userAgent" value="${clientInstance?.userAgent}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="remoteAddress"><g:message code="client.remoteAddress.label" default="Remote Address" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: clientInstance, field: 'remoteAddress', 'errors')}">
                                    <g:textField name="remoteAddress" value="${clientInstance?.remoteAddress}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="visits"><g:message code="client.visits.label" default="Visits" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: clientInstance, field: 'visits', 'errors')}">
                                    
<ul>
<g:each in="${clientInstance?.visits?}" var="v">
    <li><g:link controller="visit" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="visit" action="create" params="['client.id': clientInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'visit.label', default: 'Visit')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="remoteHost"><g:message code="client.remoteHost.label" default="Remote Host" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: clientInstance, field: 'remoteHost', 'errors')}">
                                    <g:textField name="remoteHost" value="${clientInstance?.remoteHost}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
