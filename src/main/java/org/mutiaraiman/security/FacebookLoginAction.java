package org.mutiaraiman.security;

import org.apache.struts2.ServletActionContext;
import org.meruvian.yama.actions.DefaultAction;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

public class FacebookLoginAction extends DefaultAction {

	private static final long serialVersionUID = -9197837608459867699L;

	@Override
	public String execute() throws Exception {
		Facebook facebook = new FacebookFactory().getInstance();
		ServletActionContext.getRequest().getSession().setAttribute("facebook", facebook);
		StringBuffer callbackURL = ServletActionContext.getRequest()
				.getRequestURL();
		int index = callbackURL.lastIndexOf("/");
		callbackURL.replace(index, callbackURL.length(), "")
				.append("/callback");
		ServletActionContext.getResponse().sendRedirect(
				facebook.getOAuthAuthorizationURL(callbackURL.toString()));
		return null;
	}
}
