<header>
	<h1>Tambah Renungan</h1>
</header>
<div class="row">
	<div class="span12">
		<@s.actionerror />

		<@s.form theme="bootstrap" cssClass="form-horizontal">
			<@s.textfield label="Judul" name="prayer.title" maxLength="100" required="true" cssClass="span2" />
			<@s.textarea label="Konten" name="prayer.content" rows="5" cssClass="span3" />
			<div class="form-actions" style="background-color: transparent;">
				<button value="<@s.text name="button.save" />" class="btn btn-primary"><@s.text name="button.save" /></button>
				<input value="<@s.text name="button.cancel" />" type="reset" class="btn" />
			</div>
		</@s.form>
	</div>
</div>