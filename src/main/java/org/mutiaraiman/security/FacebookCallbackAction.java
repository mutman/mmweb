package org.mutiaraiman.security;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.security.BackendUserService;
import org.meruvian.yama.security.user.BackendUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import facebook4j.Facebook;
import facebook4j.FacebookException;

public class FacebookCallbackAction extends DefaultAction {

	private static final long serialVersionUID = 564402821467054101L;
	
	@javax.inject.Inject
	protected BackendUserService service;
	
	@Override
	public String execute() throws Exception {
		Facebook facebook = (Facebook) ServletActionContext.getRequest().getSession().getAttribute("facebook");
        String oauthCode = ServletActionContext.getRequest().getParameter("code");
        try {
            facebook.getOAuthAccessToken(oauthCode);
        } catch (FacebookException e) {
            throw new ServletException(e);
        }
        BackendUser user = service.loadBackendUserByEmail(facebook.getEmail());
		if (user != null) {
			user.setFbasccesstoken(facebook.getOAuthAccessToken().getToken());
			if(facebook.getOAuthAccessToken().getExpires()!=null){
				user.setFbexpiredate(facebook.getOAuthAccessToken().getExpires());	
			}
			service.save(user);
			login(user);
        }else{
        	if(StringUtils.isNotEmpty(facebook.getOAuthAccessToken().getToken())){
        		BackendUser backendUser = new BackendUser();
        		if(StringUtils.isNotEmpty(facebook.getMe().getUsername()))
        			backendUser.setUsername(facebook.getMe().getUsername());
        		if(StringUtils.isNotEmpty(facebook.getEmail()))
        			backendUser.setEmail(facebook.getEmail());
        		backendUser.setRole("ADMINISTRATOR");
        		backendUser.setFbasccesstoken(facebook.getOAuthAccessToken().getToken());
        		if(facebook.getOAuthAccessToken().getExpires()!=null){
        			backendUser.setFbexpiredate(facebook.getOAuthAccessToken().getExpires());	
    			}
        		service.save(backendUser);
        		login(backendUser);
        	}else{
        		ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/?failure");
        	}
        }
		return null;
	}
	
	private void login(BackendUser user) throws IOException{
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null, AuthorityUtils.createAuthorityList("ADMINISTRATOR"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/home");
	}

}
