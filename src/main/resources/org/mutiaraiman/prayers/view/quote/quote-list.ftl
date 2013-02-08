<header>
	<h1><@s.text name="title" /></h1>
</header>
<div class="row">
	<div class="span12">
		<ul class="nav nav-tabs nav-stacked list">
			<#list model.entityList as m>
			<li>
				<a id="${m.id}" href="#${m.id}" class="p" content="${m.content}">
					<div><h4>${m.title}</h4></div>
					<div class="date" style="color: #999999;">${m.logInformation.createDate?string("dd-MM-yyyy HH:mm")}</div>
				</a>
			</li>
			</#list>
			<#if (model.totalPage > 1)>
			<li class="loadmore">
				<a href="#" class="loadmore" page="1">Load more... <i class="icon-book" style="float: right;"></i></a>
			</li>
			</#if>
		</ul>
	</div>
</div>
<div class="row">
	<div class="span12 c"></div>
</div>
<script type="text/javascript">
	$(function() {
		$('a.loadmore').click(function() {
			var page = new Number($(this).attr('page'));
			
			$.getJSON(location.pathname + ".json?max=${model.limit}&page=" + page, function(data, textStatus) {
				for (d in data.entityList) {
					d = data.entityList[d];
					var li = document.createElement('li');
					var a = document.createElement('a');
					$(a).attr("id", d.id);
					$(a).attr("href", "#" + d.id);
					$(a).text(d.title);
					$(li).append(a);
					$('li.loadmore').before(li);
					
					if ((page + 1) == ${model.totalPage}) {
						$('li.loadmore').remove();
					} else {
						$(this).attr('page', page + 1);
					}
				}
			});
			
			return false;
		});
		
		$.address.change(function() {
			if (location.hash.length > 1) {
				$.getJSON(location.pathname + "/" + location.hash.substring(1) + ".json", function(data, textStatus) {
					if(data.id != undefined) {
						$('ul.list').hide();
						$('div.c').fadeIn().empty();
						$('div.c').append('<p class="lead">' + data.title + '</p>');
						$('div.c').append('<p style="color: #999999;">' + new Date(data.logInformation.createDate) + '</p>');
						$('div.c').append(data.content.replace(/(\r\n|[\r\n])/g, "<br />"));
					}
				});
			} else {
				$('div.c').hide();
				$('ul.list').fadeIn();
			}
		});
	});
</script>