<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>intraStats <g:meta name="app.version"></g:meta></title>
    <meta name="layout" content="main" />
    <style type="text/css" media="screen">
      body {
        background: #ddd url(images/bg.png);
        font-size: 1.3em;
        font-family: "Helvetica", Helvetica, sans-serif;
      }
      h1, h2 {
        text-align: center;
      }
      h1#header {
        text-indent: -9999px;
        width: 300px;
        height: 50px;
        display: block;
        margin: 80px auto -10px auto;
        background: url(images/header.png);
        padding: 0;
      }
      #container {
        margin: 0 auto;
        background: url(images/content-bg.png);
        width: 300px;
        height: 300px;
      }
      #container h2 {
        padding: 25px 0 20px 0;
        color: #fff;
        font-size: 25px;
      }
      #container ul {
        padding: 0;
        margin: 0;
      }
      #container ul li {
        list-style: none;
        padding: 0;
        margin: 0;
        text-align: center;
      }
      #container ul li a {
        display: inline-block;
        margin: 0;
        padding: 10px;
        text-decoration: none;
        outline: none;
      }
      #container ul li a:hover {
        text-decoration: none;
        background: rgba(255,255,255,0.1);
        border-radius: 3px;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        box-shadow: 0 0 10px rgba(0,0,0,0.04);
        -webkit-box-shadow: 0 0 10px rgba(0,0,0,0.04);
        -moz-box-shadow: 0 0 10px rgba(0,0,0,0.04);
      }
    </style>
    <script type="text/javascript">
    $(function(){

            Cufon.replace('h2, a', {
                    color: '-linear-gradient(#ffffff, #E9E9E9)',
                    textShadow: '0 1px #939393'
            });

    });
    </script>
  </head>
  <body>
    <h1 id="header">intraStats <g:meta name="app.version"></g:meta></h1>
    <div id="container">
      <h2>${no.forsvaret.intrastats.Visit.countByDateCreatedGreaterThan(new Date() - 1)} visits today</h2>
      <ul>
        <g:each in="${sections}" var="section">
          <li class="controller"><g:link controller="section" action="show" id="${section.id}">${section.name}</g:link></li>
        </g:each>
        <li>-</li>
        <li class="controller"><g:link controller="page">Pages</g:link></li>
        <li class="controller"><g:link url="Example.html">Example (How to)</g:link></li>
      </ul>
    </div>
  </body>
</html>