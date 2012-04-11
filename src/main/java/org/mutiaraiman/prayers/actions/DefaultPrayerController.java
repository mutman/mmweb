/*
 * Copyright 2012 Meruvian
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

import org.meruvian.yama.actions.DefaultAction;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.utils.PagingUtils;
import org.mutiaraiman.prayers.Prayer;
import org.mutiaraiman.prayers.Prayer.Type;
import org.mutiaraiman.prayers.service.PrayerService;

/**
 * @author Dian Aditya
 * 
 */
public class DefaultPrayerController extends DefaultAction {
	@Inject
	protected PrayerService prayerService;

	protected EntityListWrapper<Prayer> getByType(Type type) {
		int limit = 10;
		int page = 0;
		String title = parameter.getString("title");
		long from = 0;
		long to = 0;
		title = title == null ? "" : title;

		try {
			from = new Long(parameter.getString("from") == null ? "0"
					: parameter.getString("from"));
			to = new Long(parameter.getString("to") == null ? "0"
					: parameter.getString("to"));
			limit = new Integer(parameter.getString("max") == null ? "10"
					: parameter.getString("max"));
			page = new Integer(parameter.getString("page") == null ? "0"
					: parameter.getString("page"));

			from = from == 0 ? System.currentTimeMillis() : from;
			to = to == 0 ? System.currentTimeMillis() : to;
		} catch (Exception e) {
			LOG.error("Wrong parameter format", e);
		}

		long rowcount = 0;
		long totalpage = 0;

		if (parameter.getString("date") != null) {
			rowcount = prayerService.getCountByDate(from, to, type);
		} else {
			rowcount = prayerService.getCountByTitle(title, type);
		}

		totalpage = PagingUtils.getTotalPage(rowcount, limit);

		if (page + 1 > totalpage)
			page = 0;

		EntityListWrapper<Prayer> prayers = parameter.getString("date") == null ? prayerService
				.getAllByTitle(title, type, limit, page) : prayerService.sync(
				from, to, type, limit, page);

		prayers.setTotalPage(totalpage);
		prayers.setRowCount(rowcount);

		return prayers;
	}
}
