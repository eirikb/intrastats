$(function() {
    var scriptUrl = $("#intrastats").attr("src");
  
    var host = scriptUrl.substring(0, scriptUrl.lastIndexOf('/js/'));
    host += host.charAt(host.length - 1) == '/' ? "" :  '/';
    
    var paramsPos = scriptUrl.indexOf('?');
    var params = paramsPos >= 0 ? scriptUrl.substring(paramsPos + 1) : "";

    $.getJSON(host + "pageVisit/index?" + params + "&jsoncallback=?", {
        url: window.location.href.split(';')[0],
        browserWidth: $(window).width(),
        browserHeight: $(window).height(),
        title: document.title
    });
});