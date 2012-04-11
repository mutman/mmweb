var showError = function(container, xhr, textStatus, errorThrown) {
	$(container).empty();
	$(conteiner).append('<h2>HTTP Status ' + xhr.status +' - ' + textStatus +'</h2>');
	$(container).append(errorThrown);
};

$(function() {
	$('.content').find('td.td-label').each(function() {
		var txt = $(this).text();
		txt = $.trim(txt);
		var ln = txt.length;
		
		if(txt.charAt(ln - 1) == ':')
		 txt = txt.substring(0, ln - 1);
		txt = $.trim(txt);	
		
		$(this).empty().text(txt + " :");
	});
	
	$('.content').find('a').each(function() {
		var href = $(this).attr('href');
		var namespace = location.hash.substring(0, location.hash.lastIndexOf("/")) + "/";

		if (href.charCodeAt(0) != 47) {
			$(this).attr('href', namespace + href);
		} else {
			$(this).attr('href', '#' + href);
		}
	});
	
	$('.content').find('style').each(function() {
		var href = $(this).attr('href');
		var namespace = location.hash.substring(2, location.hash.lastIndexOf("/")) + "/";

		if (href.charCodeAt(0) != 47) {
			$(this).attr('href', $.address.contextpath + namespace + href);
		}
	});
	
	$('.content').find('script, img').each(function() {
		var href = $(this).attr('src');
		var namespace = location.hash.substring(2, location.hash.lastIndexOf("/")) + "/";

		if (href.charCodeAt(0) != 47) {
			$(this).attr('src', $.address.contextpath + namespace + href);
		}
	});
	
	$('.content').find('form').submit(function() {
		var requestMethod = $(this).attr('method');
		var href = $(this).attr('action');
		var namespace = location.hash.substring(2);
		
		
		if(namespace.lastIndexOf('/') > 0) {
			namespace = namespace.substring(0, namespace.lastIndexOf('/')) + '/';
		} else {
			namespace = '';
		}
		
		namespace = $.address.contextpath + namespace;
		
		if (href.charCodeAt(0) != 47) {
			href = namespace + href;
		}
		
		$.address.path(href.replace($.address.contextpath, ''));
		
		jQuery.ajax({
			type : requestMethod,
			data : $(this).serialize(),
			url : href,
			success : function(data) {
				var script = document.createElement('script');
				$(script).attr('src', $.address.contextpath + 'static/dynamicajax.js');
				$(script).attr('type', 'text/javascript');
				
				$('.content').empty().html(data);
				$('.content').append(script);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				showError('.content', jqXHR, textStatus, errorThrown);
			}
		});
		
		return false;
	});
});