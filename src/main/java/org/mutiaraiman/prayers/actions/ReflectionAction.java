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
package org.mutiaraiman.prayers.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.actions.annotations.ActionParam;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.mutiaraiman.prayers.Prayer;
import org.mutiaraiman.prayers.Prayer.Reminder;
import org.mutiaraiman.prayers.Prayer.Type;

/**
 * @author Dian Aditya
 * 
 */
@Action("reflections")
@Namespace("/module")
@Results({
		@Result(name = DefaultAction.INDEX, type = "freemarker", location = "/org/mutiaraiman/prayers/view/prayers/prayers-list.ftl"),
		@Result(name = DefaultAction.NEW, type = "freemarker", location = "/org/mutiaraiman/prayers/view/reflections/reflections-add.ftl"),
		@Result(name = DefaultAction.CREATE, type = "freemarker", location = "/org/mutiaraiman/global/view/save-success.ftl"),
		@Result(name = DefaultAction.DELETE, type = "freemarker", location = "/org/mutiaraiman/prayers/view/prayers/prayers-delete.ftl"),
		@Result(name = DefaultAction.SUCCESS, type = "redirectAction", location = "reflections/delete") })
public class ReflectionAction extends DefaultPrayerController {

	private static final Log LOG = LogFactory.getLog(ReflectionAction.class);

	@ActionParam("prayer")
	private Prayer prayer = new Prayer();

	public String editNew() {
		model = null;
		parameter.put("reminders", Reminder.values());
		
		return NEW;
	}

	public String create() {
		prayer.setCategory(null);
		prayer.setType(Type.REFLECTION);
		prayerService.save(prayer);
		FacebookPagePostUtil.post(prayer);
		return CREATE;
	}

	public String index() {
		model = getByType(Type.REFLECTION);

		return INDEX;
	}

	public String show() {
		if (parameter.getParameterId().equalsIgnoreCase("delete")) {
			index();

			return DELETE;
		}

		model = prayerService.findById(parameter.getParameterId());
		parameter.clear();

		return SHOW;
	}

	public String delete() {
		prayer = prayerService.findById(parameter.getParameterId());
		prayer.getLogInformation().setStatusFlag(StatusFlag.INACTIVE);
		prayerService.save(prayer);

		return SUCCESS;
	}
}
