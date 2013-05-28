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
package org.mutiaraiman.prayers.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.meruvian.yama.persistence.access.PersistenceDAO;
import org.mutiaraiman.prayers.Prayer;
import org.mutiaraiman.prayers.Prayer.Type;
import org.springframework.stereotype.Repository;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class PrayerDAO extends PersistenceDAO<Prayer> {
    
    private static final Logger log = Logger.getLogger(PrayerDAO.class);
    
	public EntityListWrapper<String> getAllTitle(int limit, int page) {

		TypedQuery<String> query = createQuery(String.class, "d.title", "d",
				"d.logInformation.statusFlag = ?"
						+ " ORDER BY d.logInformation.createDate DESC",
				StatusFlag.ACTIVE);

		if (limit > 0) {
			query.setMaxResults(limit);
		}

		query.setFirstResult(page);

		EntityListWrapper<String> paging = new EntityListWrapper<String>();
		paging.setCurrentPage(page);
		paging.setLimit(limit);
		paging.setRowCount(getRowCount());
		paging.setEntityList(query.getResultList());

		return paging;
	}
	
	public Prayer getLatestPrayerByType(Type type) {
	    TypedQuery<Prayer> query = createQuery(entityClass, "d", "d", "d.logInformation.statusFlag = ? AND d.type = ?"
                        + " ORDER BY d.logInformation.createDate DESC", StatusFlag.ACTIVE, type);
        try {
            query.setMaxResults(1);
            List<Prayer> prayers = query.getResultList();
            return prayers.get(0);
        } catch (IndexOutOfBoundsException e) {
            Prayer p = new Prayer();
            p.setId("hasdh2hgaew2rgdaweg3asd3gasdga");
            p.setTitle("Content title");
            p.setContent("Newest data, coming soon..");
            return p;
        }
	}
	
	public EntityListWrapper<Prayer> getAllByType(Type type, int limit, int page) {
		TypedQuery<Prayer> query = createQuery(entityClass, "d", "d",
				"d.logInformation.statusFlag = ? AND d.type = ?"
						+ " ORDER BY d.logInformation.createDate DESC",
				StatusFlag.ACTIVE, type);

		if (limit > 0) {
			query.setMaxResults(limit);
		}

		query.setFirstResult(page);

		EntityListWrapper<Prayer> paging = new EntityListWrapper<Prayer>();
		paging.setCurrentPage(page);
		paging.setLimit(limit);
		paging.setEntityList(query.getResultList());

		return paging;
	}

	public EntityListWrapper<Prayer> getAllByTitle(String title, Type type,
			int limit, int page) {
		TypedQuery<Prayer> query = createQuery(entityClass, "d", "d",
				"d.logInformation.statusFlag = ? AND d.type = ?"
						+ " AND d.title like ?"
						+ " ORDER BY d.logInformation.createDate DESC",
				StatusFlag.ACTIVE, type, "%" + title + "%");

		if (limit > 0) {
			query.setMaxResults(limit);
		}

		query.setFirstResult(page * limit);

		EntityListWrapper<Prayer> paging = new EntityListWrapper<Prayer>();
		paging.setCurrentPage(page);
		paging.setLimit(limit);
		paging.setRowCount(getRowCount());
		paging.setEntityList(query.getResultList());

		return paging;
	}
	
	public Prayer getTodayStory(){
		String date = new SimpleDateFormat("dd MMMM", new Locale("in")).format(new java.util.Date());
		TypedQuery<Prayer> query = createQuery(entityClass, "d", "d", "d.title LIKE ? AND d.type = ?", "%"+date+"%", Type.STORIES);
		try{
			return query.getSingleResult();	
		}catch (Exception e) {
			return null;
		}
	}
	
	public EntityListWrapper<Prayer> getAllByDate(long timeMilisFrom,
			long timeMilisTo, Type type, int limit, int page) {
		TypedQuery<Prayer> query = createQuery(entityClass, "d", "d",
				"d.logInformation.statusFlag = ?" + " AND d.type = ?"
						+ " AND (d.logInformation.createDate"
						+ " BETWEEN ? AND ?)"
						+ " ORDER BY d.logInformation.createDate DESC",
				StatusFlag.ACTIVE, type, new Date(timeMilisFrom), new Date(
						timeMilisTo));
		
		log.debug("getAllByDate("+new Date(timeMilisFrom)+", "+new Date(timeMilisTo)+", "+type+", "+limit+", "+page+")");
		
		if (limit > 0) {
			query.setMaxResults(limit);
		}

		query.setFirstResult(page);

		EntityListWrapper<Prayer> paging = new EntityListWrapper<Prayer>();
		paging.setCurrentPage(page);
		paging.setLimit(limit);
		paging.setRowCount(getRowCount());
		paging.setEntityList(query.getResultList());

		return paging;
	}

	public long getCountByTitle(String title, Type type) {
		return getRowCount("d", "d.logInformation.statusFlag = ? "
				+ "AND d.type = ? AND d.title like ?", StatusFlag.ACTIVE, type,
				"%" + title + "%");
	}

	public long getCountByDate(long timeMilisFrom, long timeMilisTo, Type type) {
		return getRowCount("d", "d.logInformation.statusFlag = ?"
				+ " AND d.type = ?" + " AND (d.logInformation.createDate"
				+ " BETWEEN ? AND ?)", StatusFlag.ACTIVE, type, new Date(
				timeMilisFrom), new Date(timeMilisTo));
	}
}
