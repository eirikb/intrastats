
<%@ page import="no.forsvaret.intrastats.Section" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'section.label', default: 'Section')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
  <g:javascript src="FusionCharts.js" />
</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
  </div>
  <div class="body" style="background: #fff; width: 90%">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <h3>Visits: ${visitCount}</h3>


    <div id="chartdiv" align="center">
      FusionCharts. </div>

    <script type="text/javascript">
                 var chart = new FusionCharts("${createLink(uri: '/')}flash/FCF_Column3D.swf", "ChartId", "600", "350");
                 chart.setDataXML("${data}");
                 chart.render("chartdiv");
    </script>


  </div>
</body>
</html>
