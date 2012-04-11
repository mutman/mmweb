<header>
	<h1>Forgot Password</h1>
</header>
<div class="row">
	<div class="span12">
		<@s.form theme="bootstrap" cssClass="form-horizontal">
			<#if notfound?exists>
			<div class="alert alert-error">
				<a class="close" data-dismiss="alert">×</a>
				Username tidak ditemukan!
			</div>
			</#if>
			<@s.textfield label="Username" name="user.username" />
			<div class="form-actions" style="background-color: transparent;">
				<button value="Save" class="btn btn-primary">Get Password</button>
			</div>
		</@s.form>
	</div>
</div>