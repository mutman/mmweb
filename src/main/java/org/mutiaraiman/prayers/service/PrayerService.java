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
package org.mutiaraiman.prayers.service;

import javax.inject.Inject;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.access.PersistenceDAO;
import org.meruvian.yama.persistence.access.PersistenceManager;
import org.mutiaraiman.prayers.Prayer;
import org.mutiaraiman.prayers.Prayer.Type;
import org.mutiaraiman.prayers.dao.PrayerDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dian Aditya
 * 
 */
@Service
@Transactional(readOnly = true)
public class PrayerService extends PersistenceManager<Prayer> {
	@Inject
	private PrayerDAO dao;

	public EntityListWrapper<Prayer> getAllByLimit(int limit, int page) {
		return dao.getAll(limit, page);
	}

	public EntityListWrapper<String> getAllTitle(int limit, int page) {
		return dao.getAllTitle(limit, page);
	}
	
	public Prayer getLatestPrayerByType(Type type) {
	    return dao.getLatestPrayerByType(type);
	}
	
	public EntityListWrapper<Prayer> getAllByType(Type type, int limit, int page) {
		return dao.getAllByType(type, limit, page);
	}

	public EntityListWrapper<Prayer> getAllByTitle(String title, Type type,
			int limit, int page) {
		return dao.getAllByTitle(title, type, limit, page);
	}

	public EntityListWrapper<Prayer> sync(long timeMilisFrom, long timeMilisTo,
			Type type, int limit, int page) {
		return dao.getAllByDate(timeMilisFrom, timeMilisTo, type, limit, page);
	}

	public long getCountByTitle(String title, Type type) {
		return dao.getCountByTitle(title, type);
	}

	public long getCountByDate(long timeMilisFrom, long timeMilisTo, Type type) {
		return dao.getCountByDate(timeMilisFrom, timeMilisTo, type);
	}
	
	public Prayer getTodayStory(){
		return dao.getTodayStory();
	}
	
	protected PersistenceDAO<Prayer> getDao() {
		return dao;
	}
}
