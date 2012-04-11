package org.meruvian.yama.security.actions;

import javax.inject.Inject;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.actions.annotations.ActionParam;
import org.meruvian.yama.security.BackendUserService;
import org.meruvian.yama.security.user.BackendUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@Results({ @Result(name = "INDEX", type = "freemarker", location = "/org/mutiaraiman/global/view/createaccount.ftl") })
public class UserAction extends DefaultAction {

	@Inject
	private BackendUserService service;

	@ActionParam("user")
	private BackendUser user = new BackendUser();

	public String index() {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		parameter.put("user",
				service.loadBackendUserByUsername(user.getUsername()));

		return INDEX;
	}

	public String create() {
		service.save(user);
		addActionMessage("Profil berhasi dirubah");

		return LOGIN;
	}
}
