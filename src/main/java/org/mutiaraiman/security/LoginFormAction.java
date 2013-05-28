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
package org.mutiaraiman.security;

import javax.inject.Inject;

import org.apache.struts2.ServletActionContext;
import org.meruvian.yama.actions.DefaultAction;
import org.mutiaraiman.prayers.Prayer.Type;
import org.mutiaraiman.prayers.service.PrayerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Dian Aditya
 * 
 */
public class LoginFormAction extends DefaultAction {
    
    private static final long serialVersionUID = -1825039661172525306L;
    
    @Inject
    private PrayerService prayerService;
    
	public String execute() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();
		
		parameter.put("doa", prayerService.getLatestPrayerByType(Type.PRAYER));
		parameter.put("renungan", prayerService.getLatestPrayerByType(Type.REFLECTION));
		parameter.put("quote", prayerService.getLatestPrayerByType(Type.QUOTE));
		parameter.put("stories", prayerService.getTodayStory());
        parameter.put("worship", prayerService.getLatestPrayerByType(Type.PRAISE_AND_WORSHIP));
        
		if (principal instanceof String
				&& principal.toString().equals("anonymousUser"))
			return INPUT;
		ServletActionContext.getResponse().sendRedirect("home");
		
		return null;
	}

}
