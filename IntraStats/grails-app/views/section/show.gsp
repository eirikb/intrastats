
<%@ page import="no.forsvaret.intrastats.Section" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'section.label', default: 'Section')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
  <g:javascript src="FusionCharts.js" />
  <g:javascript src="jquery-ui-1.7.2.custom.min.js" />
  <link rel="stylesheet" href="${resource(dir:'css/ui-lightness',file:'jquery-ui-1.7.2.custom.css')}" />
  <script type="text/javascript">
    $(function() {
      $("#fromDate").datepicker({ dateFormat: 'yy-mm-dd' });
      $("#toDate").datepicker({ dateFormat: 'yy-mm-dd' });
      getVisitsAjax();
    });

    function getVisitsAjax() {
      $.get("${createLink(uri: '/')}section/getVisitsAjax", {id : ${params.id}, fromDate: $("#fromDate").val(), toDate: $("#toDate").val()}, function(data) {

        var chart = new FusionCharts("${createLink(uri: '/')}flash/FCF_Column3D.swf", "ChartId", "750", "350");
        chart.setTransparent(true);
        chart.setDataXML(data.visitData);
        chart.render("visitData");

        var myChart2 = new FusionCharts("${createLink(uri: '/')}flash/FCF_Pie3D.swf", "myChartId2", "600", "500");
        myChart2.setTransparent(true);
        myChart2.setDataXML(data.browserData);
        myChart2.render("browserData");

        $("#visitCount").html(data.visitCount);
      });
    }
  </script>
</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    <span class="menuButton"><g:link class="list" controller="page" action="list" id="${params.id}">Page list for this section</g:link></span>
  </div>
  <div class="body">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <div align="center" style="width: 420px; margin: 0 auto;">
      <h3>Visits: <span id="visitCount"></span></h3>
      <p style="float:left">From: <input type="text" id="fromDate"></p>
      <p style="float:left">To: <input type="text" id="toDate"></p>
      <a href="${params.id}" style="background:white;padding:2px 5px;border: 1px solid #ccc;float:left;display:block;margin:3px 0 0 4px" onclick="getVisitsAjax(); return false;">Fetch</a>
    </div>
    <div id="visitData" align="center">Visits</div>
    <div id="browserData" align="center">browsers</div>
  </div>
</body>
</html>
