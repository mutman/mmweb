/**
 * Copyright 2012 BlueOxygen Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mutiaraiman.security.actions;

import javax.inject.Inject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.actions.annotations.ActionParam;
import org.meruvian.yama.plugins.mail.EmailService;
import org.meruvian.yama.security.BackendUserService;
import org.meruvian.yama.security.user.BackendUser;

/**
 * @author Dian Aditya
 * 
 */
@Action("forgot")
@Results({
		@Result(name = "INDEX", type = "freemarker", location = "/org/mutiaraiman/global/view/forgotpassword.ftl"),
		@Result(name = "CREATE", type = "redirect", location = "/forgot?notfound") })
public class ForgotPasswordAction extends DefaultAction {
	@Inject
	protected BackendUserService service;

	@Inject
	protected EmailService emailService;

	@ActionParam("user")
	protected BackendUser user = new BackendUser();

	public String index() {
		return INDEX;
	}

	public String create() {
		user = service.loadBackendUserByUsername(user.getUsername());

		if (user == null) {
			return CREATE;
		}

		try {
			emailService.sendMessage(
					user.getEmail(),
					"Konfirmasi Lupa Password",
					getText("forgotpassword")
							.replace("mailx.email", user.getEmail())
							.replace("mailx.user", user.getUsername())
							.replace("mailx.pass", user.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return CREATE;
	}
}
