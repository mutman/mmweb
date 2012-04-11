<header>
	<h1>Tambah Kategori</h1>
</header>
<div class="row">
	<div class="span12">
		<@s.actionerror />

		<@s.form method="POST" theme="bootstrap" cssClass="form-horizontal">
			<@s.textfield key="label.name" name="category.name" maxLength="100" required="true" cssClass=" span2" />
			<@s.textarea key="label.description" name="category.description" rows="5" cssClass=" span3" />
			<div class="form-actions" style="background-color: transparent;">
				<button value="<@s.text name="button.save" />" class="btn btn-primary"><@s.text name="button.save" /></button>
				<input value="<@s.text name="button.cancel" />" type="reset" class="btn" />
			</div>
		</@s.form>
	</div>
</div>