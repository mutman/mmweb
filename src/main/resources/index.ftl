<#--
<div class="row" style="margin: 20px;">
	<center>
		<img alt="logo" src="<@s.url value="/statics/img/logo-512.png" />" style="width: 50%; max-width: 200px;" />
	</center>
</div>
-->
<header>
	<h1>Login</h1>
</header>
<div class="row">
	<div class="span12">
		<@s.form theme="bootstrap" action="/login" cssClass="form-horizontal">
		<@s.actionmessage theme="bootstrap" />
		<@s.textfield label="Username" name="username" cssClass="span3" /> 
		<@s.password label="Password" name="password" cssClass="span3" />
		<div class="form-actions" style="background-color: transparent;">
			<button value="Save" class="btn btn-primary">Login</button>
			<br> <br>
			<p class="lead">
				<font size="3">Forget Password ? Click 
					<a href="<@s.url value="/forgot" />"> Here </a>
			</p>
		</div>
		</@s.form>
	</div>
</div>