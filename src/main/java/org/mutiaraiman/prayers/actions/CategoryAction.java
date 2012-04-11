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

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.actions.annotations.ActionParam;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.mutiaraiman.prayers.Category;
import org.mutiaraiman.prayers.service.CategoryService;

/**
 * @author Dian Aditya
 * 
 */
@Action("category_of_prayer")
@Namespace("/module")
@Results({
		@Result(name = DefaultAction.INDEX, type = "freemarker", location = "/org/mutiaraiman/prayers/view/category/category-list.ftl"),
		@Result(name = DefaultAction.NEW, type = "freemarker", location = "/org/mutiaraiman/prayers/view/category/category-add.ftl"),
		@Result(name = DefaultAction.CREATE, type = "freemarker", location = "/org/mutiaraiman/global/view/save-success.ftl"),
		@Result(name = DefaultAction.DELETE, type = "freemarker", location = "/org/mutiaraiman/prayers/view/category/category-delete.ftl"),
		@Result(name = DefaultAction.SUCCESS, type = "redirectAction", location = "category_of_prayer/delete") })
public class CategoryAction extends DefaultAction {

	private static final Log LOG = LogFactory.getLog(CategoryAction.class);

	@Inject
	private CategoryService service;

	@ActionParam("category")
	private Category category = new Category();

	// GET /module/category_of_prayer
	public String index() {
		// model = service.getAllByLimit(0, 0);
		String title = parameter.getString("title");
		title = title == null ? "" : title;

		model = service.getAllByName(title, 0, 0);

		return INDEX;
	}

	// GET /module/category_of_prayer/new
	public String editNew() {
		return NEW;
	}

	// POST /module/category_of_prayer
	public String create() {
		category.getLogInformation().setStatusFlag(StatusFlag.ACTIVE);
		service.save(category);

		return CREATE;
	}

	// GET /module/category_of_prayer/{id}
	public String show() {
		if (parameter.getParameterId().equalsIgnoreCase("delete")) {
			index();

			return DELETE;
		}

		model = service.findById(parameter.getParameterId());
		parameter.clear();

		return SHOW;
	}

	// GET /module/category_of_prayer/{id}/delete
	// DELETE /module/category_of_prayer/{id}
	public String delete() {
		category = service.findById(parameter.getParameterId());
		category.getLogInformation().setStatusFlag(StatusFlag.INACTIVE);
		service.save(category);

		return SUCCESS;
	}
}
