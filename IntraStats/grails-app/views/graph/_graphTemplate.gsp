<script type="text/javascript">
   $(function() {
     $("#fromDate").datepicker({ dateFormat: 'yy-mm-dd' });
     $("#toDate").datepicker({ dateFormat: 'yy-mm-dd' });
     getVisitsAjax();
   });

   function getVisitsAjax() {
     $.get("${createLink(uri: '/')}graph/graph", {id : ${params.id}, type: '${type}', fromDate: $("#fromDate").val(), toDate: $("#toDate").val()}, function(data) {

       var chart = new FusionCharts("${createLink(uri: '/')}flash/FCF_Column3D.swf", "ChartId", "750", "350");
       chart.setTransparent(true);
       chart.setDataXML(data.visitData);
       chart.render("visitData");

       $("#visitCount").html(data.visitCount);
     });
   }
</script>
<div align="center" style="width: 430px; margin: 0 auto;">
  <h3>Visits: <span id="visitCount"></span></h3>
  <p style="float:left">From: <input type="text" id="fromDate"></p>
  <p style="float:left">To: <input type="text" id="toDate"></p>
  <a href="${params.id}" style="background:white;padding:2px 5px;border: 1px solid #ccc;float:left;display:block;margin:3px 0 0 4px" onclick="getVisitsAjax(); return false;">Fetch</a>
  <div style="clear:both"></div>
</div>
<div id="visitData" align="center">Visits</div>