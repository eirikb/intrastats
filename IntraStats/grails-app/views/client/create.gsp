
<%@ page import="no.forsvaret.intrastats.Client" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'client.label', default: 'Client')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${clientInstance}">
            <div class="errors">
                <g:renderErrors bean="${clientInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
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
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
