$(document).ready(function() {
    $.extend({
        getUrlVars: function(){
            var vars = [], hash;
            var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
            for(var i = 0; i < hashes.length; i++){
                hash = hashes[i].split('=');
                vars.push(hash[0]);
                vars[hash[0]] = hash[1];
            }
            return vars;
        },
        unparam: function(name){
            return $.getUrlVars()[name];
        }
    });

    var articleID = $.unparam('articleID');

    $.getJSON('/pageVisit/index?jsoncallback=?', {
        url: window.location.href.split(';')[0],
        articleID: articleID,
        screenWidth: screen.width,
        screenHeight: screen.height,
        referrer: document.referrer.split(';')[0],
        title: articleID ? $('.artTitle').text() : document.title
    });
});