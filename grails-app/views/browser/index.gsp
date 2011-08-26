
<%@ page import="no.forsvaret.intrastats.Section" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <title>Browsers</title>
  <g:javascript src="FusionCharts.js" />
  <g:javascript src="jquery-ui-1.7.2.custom.min.js" />
  <link rel="stylesheet" href="${resource(dir:'css/ui-lightness',file:'jquery-ui-1.7.2.custom.css')}" />
  <script type="text/javascript">
    $(function() {
      getVisitsAjax();
    });

    function getVisitsAjax() {
        var myChart2 = new FusionCharts("${createLink(uri: '/')}flash/FCF_Pie3D.swf", "myChartId2", "600", "500");
        myChart2.setTransparent(true);
        myChart2.setDataXML("${browserData}");
        myChart2.render("browserData");
    }
  </script>
</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
  </div>
  <div class="body" align="center">
    <h1>All browsers</h1>
    <p><b>Browser size (based on site visits):</b></p>
    <p>Browser width: ${(int)browserWidth}</p>
    <p>Browser height: ${(int)browserHeight}</p>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <div id="browserData" align="center">browsers</div>
  </div>
</body>
</html>
