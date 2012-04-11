<header>
	<h1>Kategori</h1>
</header>
<div class="row">
	<div class="span12">
	<@s.actionerror />

		<@s.form theme="bootstrap" cssClass="well form-search" method="GET">
			<@s.textfield label="Nama"  name="category.name" maxLength="100" required="true" cssClass="input-medium search-query" />
				<button value="<@s.text name="button.search" />" class="btn"><@s.text name="button.search" /></button>
				</@s.form> 
	
		<table width="100%" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th width="5%">#</th>
					<th width="25%"><@s.text name="label.name" /></th>
					<th width="70%"><@s.text name="label.description" /></th>
				</tr>
			</thead>
			<tbody>
				<#assign num = 1>
				<#list model.entityList as m>
				<tr>
					<td>${num} <#assign num = num + 1></td>
					<td>${m.name}</td>
					<td>${m.description}</td>
				</tr>
				</#list>
			</tbody>
		</table>
		
					
		<div align="right">
		
		<i class="icon-fast-backward"></i>
		
			<i class="icon-step-backward"></i>
		
						
		<i class="icon-fast-forward"></i> 
		
		
			<i class="icon-step-forward"></i>
		
		
			
				
		
		
		</div>
	</div>
</div>