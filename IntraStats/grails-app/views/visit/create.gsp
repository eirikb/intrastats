
<%@ page import="no.forsvaret.intrastats.Visit" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'visit.label', default: 'Visit')}" />
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
            <g:hasErrors bean="${visitInstance}">
            <div class="errors">
                <g:renderErrors bean="${visitInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="referral"><g:message code="visit.referral.label" default="Referral" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: visitInstance, field: 'referral', 'errors')}">
                                    <g:textField name="referral" value="${visitInstance?.referral}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="browserWidth"><g:message code="visit.browserWidth.label" default="Browser Width" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: visitInstance, field: 'browserWidth', 'errors')}">
                                    <g:textField name="browserWidth" value="${fieldValue(bean: visitInstance, field: 'browserWidth')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="browserHeight"><g:message code="visit.browserHeight.label" default="Browser Height" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: visitInstance, field: 'browserHeight', 'errors')}">
                                    <g:textField name="browserHeight" value="${fieldValue(bean: visitInstance, field: 'browserHeight')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="client"><g:message code="visit.client.label" default="Client" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: visitInstance, field: 'client', 'errors')}">
                                    <g:select name="client.id" from="${no.forsvaret.intrastats.Client.list()}" optionKey="id" value="${visitInstance?.client?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="page"><g:message code="visit.page.label" default="Page" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: visitInstance, field: 'page', 'errors')}">
                                    <g:select name="page.id" from="${no.forsvaret.intrastats.Page.list()}" optionKey="id" value="${visitInstance?.page?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastUpdated"><g:message code="visit.lastUpdated.label" default="Last Updated" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: visitInstance, field: 'lastUpdated', 'errors')}">
                                    <g:datePicker name="lastUpdated" precision="day" value="${visitInstance?.lastUpdated}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateCreated"><g:message code="visit.dateCreated.label" default="Date Created" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: visitInstance, field: 'dateCreated', 'errors')}">
                                    <g:datePicker name="dateCreated" precision="day" value="${visitInstance?.dateCreated}"  />
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
