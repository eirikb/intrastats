<!DOCTYPE html>
<html>
  <head>
    <title><g:layoutTitle default="Grails" /></title>
    <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
    <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
  <g:javascript library="jquery.min" />
  <g:layoutHead />
</head>
<body>
  <div id="spinner" class="spinner" style="display:none;">
    <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
  </div>
  <div id="grailsLogo" class="logo"><a href="${createLink(uri: '/')}"><img src="${resource(dir:'images',file:'header.png')}" alt="Grails" border="0" /></a></div>
<g:layoutBody />
</body>
</html>