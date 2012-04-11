<header>
	<h1>Ubah Password</h1>
</header>
<div class="row">
	<div class="span12">
		<@s.form theme="bootstrap" action="/change" cssClass="form-horizontal"> 
			<@s.password label="Password Sekarang" name="oldpassword" />
			<@s.password label="Password Baru" name="newpassword" />
			<@s.password label="Konfirmasi Password" name="confirmpassword" />
		<div class="form-actions" style="background-color: transparent;">
				<button value="<@s.text name="button.save" />" class="btn btn-primary"><@s.text name="button.save" /></button>
				<input value="<@s.text name="button.cancel" />" type="reset" class="btn" />
			</div>
		</@s.form>


	</div>
</div>