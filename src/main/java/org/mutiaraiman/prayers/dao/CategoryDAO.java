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

import javax.persistence.TypedQuery;

import org.meruvian.yama.persistence.EntityListWrapper;
import org.meruvian.yama.persistence.LogInformation.StatusFlag;
import org.meruvian.yama.persistence.access.PersistenceDAO;
import org.mutiaraiman.prayers.Category;
import org.mutiaraiman.prayers.Prayer;
import org.mutiaraiman.prayers.Prayer.Type;
import org.springframework.stereotype.Repository;

import com.sun.tools.internal.xjc.model.CAdapter;

/**
 * @author Dian Aditya
 * 
 */
@Repository
public class CategoryDAO extends PersistenceDAO<Category> {
	
	public EntityListWrapper<Category> getAllByName(String name, int limit, int page) {
		String ql = "SELECT d FROM "
				+ entityClass.getName()
				+ " d WHERE d.logInformation.statusFlag = :status  AND d.name like :name";
		TypedQuery<Category> query = entityManager.createQuery(ql, entityClass);
		query.setParameter("status", StatusFlag.ACTIVE);
//		query.setParameter("type", type);
		query.setParameter("name", "%" + name + "%");

		if (limit > 0) {
			query.setMaxResults(limit);
		}

		query.setFirstResult(page);

		EntityListWrapper<Category> paging = new EntityListWrapper<Category>();
		paging.setCurrentPage(page);
		paging.setLimit(limit);
		paging.setRowCount(getRowCount());
		paging.setEntityList(query.getResultList());

		return paging;
	}
	
	

}
