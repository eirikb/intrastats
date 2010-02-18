
<%@ page import="no.forsvaret.intrastats.Visit" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'visit.label', default: 'Visit')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'visit.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="referral" title="${message(code: 'visit.referral.label', default: 'Referral')}" />
                        
                            <g:sortableColumn property="browserWidth" title="${message(code: 'visit.browserWidth.label', default: 'Browser Width')}" />
                        
                            <g:sortableColumn property="browserHeight" title="${message(code: 'visit.browserHeight.label', default: 'Browser Height')}" />
                        
                            <th><g:message code="visit.client.label" default="Client" /></th>
                   	    
                            <th><g:message code="visit.page.label" default="Page" /></th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${visitInstanceList}" status="i" var="visitInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${visitInstance.id}">${fieldValue(bean: visitInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: visitInstance, field: "referral")}</td>
                        
                            <td>${fieldValue(bean: visitInstance, field: "browserWidth")}</td>
                        
                            <td>${fieldValue(bean: visitInstance, field: "browserHeight")}</td>
                        
                            <td>${fieldValue(bean: visitInstance, field: "client")}</td>
                        
                            <td>${fieldValue(bean: visitInstance, field: "page")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${visitInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
