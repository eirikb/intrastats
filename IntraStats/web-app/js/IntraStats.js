$(function() {
	$.extend({4(window
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

	$.getJSON('ip:port?jsoncallback=?', {
		url: window.location.href.split(';')[0],
		articleID: articleID,
		browserWidth: $(window).width(),
		browserHeight: $(window).height(),
		referrer: document.referrer.split(';')[0],
		title: articleID ? $('.artTitle').text() : document.title
	}); 
});