<header>
	<h1><@s.text name="title.delete" /></h1>
</header>
<div class="row">
	<div class="span12">
		<@s.actionerror /> 
		<div class="row">
			<div class="span12">
				<form class="well form" method="GET"> 
					<input type="text" name="title" class="span3" placeholder="Search Title" value="<@s.property value="title" />" />
					<button value="<@s.text name="button.search" />" class="btn"><@s.text name="button.search" /></button>
				</form>
				
				<ul class="nav nav-tabs nav-stacked">
					<#list model.entityList as m>
					<li>
						<a href="${m.id}/delete" onclick="return confirm('<@s.text name="message.confirm.delete" /> &nbsp;${m.name}?');" value="Show a confirm box">${m.name} <i class="icon-trash" style="float: right;"></i></a>
					</li>
					</#list>
				</ul>
			</div>
		</div>
	</div>
</div>