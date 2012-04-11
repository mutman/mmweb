<header>
	<h1>Profil</h1>
</header>
<div class="row">
		<div class="span">
		<@s.actionerror /> 
		<@s.actionmessage theme="bootstrap" />
		
		<@s.form theme="bootstrap" cssClass="form-horizontal"> 
			<input type="hidden" name="user.id" value="${user.id}" />
			<@s.textfield label="Username" name="user.username" maxLength="20" required="true" cssClass="span3" /> 
			<@s.textfield label="Email" name="user.email" maxLength="20" required="true" cssClass="span3" type="email" />
			<div class="form-actions"  style="background-color : transparent;">
				<button value="<@s.text name="button.save" />" class="btn btn-primary"><@s.text name="button.save" /></button>
				<input value="<@s.text name="button.cancel" />" type="reset" class="btn" />
			</div>
		</@s.form>
			
		</div>
</div>

