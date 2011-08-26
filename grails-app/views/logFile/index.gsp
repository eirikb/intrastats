
<%@ page import="no.forsvaret.intrastats.Section" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <title>Log File</title>
  </head>
  <body>
    <div class="nav">
      <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    </div>
    <div class="body">
      <div align="center">
        <h1>Log File</h1>
        <p>Tail:
        <g:link params="[tailSize:'1000']">[1KB]</g:link>
        <g:link params="[tailSize:'500000']">[500KB]</g:link>
        <g:link params="[tailSize:'1000000']">[1MB]</g:link>
        <g:link params="[tailSize:'2000000']">[2MB]</g:link>
        <g:link params="[tailSize:'5000000']">[5MB]</g:link><br />
        <g:link params="[wholeFile:'true']">[Whole file]</g:link>
        </p>
      </div>
    </div>
    <pre>
${logFileContent}
    </pre>
  </body>
</html>
