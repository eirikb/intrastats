
<%@ page import="no.forsvaret.intrastats.Page" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <title>Site - day by day</title>
  <g:javascript src="FusionCharts.js" />
  <g:javascript src="jquery-ui-1.7.2.custom.min.js" />
  <link rel="stylesheet" href="${resource(dir:'css/ui-lightness',file:'jquery-ui-1.7.2.custom.css')}" />
</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
  </div>
  <div class="body">
    <h1>Site - day by day</h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <g:render template="/graph/graphTemplate" model="[type: 'site']" />
  </div>
</body>
</html>
