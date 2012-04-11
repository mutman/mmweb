<header>
	<h1>Tambah Doa</h1>
</header>
<div class="row">
	<div class="span12">
		<@s.actionerror />

		<@s.form theme="bootstrap" cssClass="form-horizontal">
			<@s.textfield label="Judul" name="prayer.title" maxLength="100" required="true" cssClass="span2" />
			<@s.textarea label="Konten" name="prayer.content" rows="5" cssClass="span3" />
			<div class="control-group">
				<label class="control-label" for="_kategori">Kategori</label>
				<div class="controls">
					<select name="prayer.category.id" id="_kategori" class="span3">
						<#list model.categories.entityList as c>
						<option value="${c.id}">${c.name}</option>
						</#list>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="_pengingat">Pengingat</label>
				<div class="controls">
					<select name="prayer.r" id="_pengingat" class="span2">
						<#list model.reminders as r>
						<option value="${r.ordinal()}">${r}</option>
						</#list>
					</select>
				</div>
			</div>
			<div class="form-actions" style="background-color: transparent;">
				<button value="<@s.text name="button.save" />" class="btn btn-primary"><@s.text name="button.save" /></button>
				<input value="<@s.text name="button.cancel" />" type="reset" class="btn" />
			</div>
		</@s.form>
	</div>
</div>