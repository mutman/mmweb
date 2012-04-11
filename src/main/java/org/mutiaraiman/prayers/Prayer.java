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
package org.mutiaraiman.prayers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.meruvian.yama.persistence.DefaultPersistence;

/**
 * @author Dian Aditya
 * 
 */
@Entity
@Table(name = "mik_prayer")
public class Prayer extends DefaultPersistence {

	public enum Reminder {
		_6_AM("06.00 AM"), _12_PM("12.00 PM"), _6_PM("06.00 PM");

		private String string;

		Reminder(String string) {
			this.string = string;
		}

		public String toString() {
			return string;
		}
	}

	public enum Type {
		PRAYER, REFLECTION
	}

	private String title;
	private String content;
	private Category category;
	private Reminder reminder = Reminder._6_AM;
	private Type type = Type.PRAYER;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kategori")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Enumerated(EnumType.ORDINAL)
	public Reminder getReminder() {
		return reminder;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}

	@Transient
	public void setR(int r) {
		this.reminder = Reminder.values()[r];
	}

	@JsonIgnore
	@Enumerated(EnumType.ORDINAL)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(length = 100)
	public String getTitle() {
		return title;
	}

	@Column(length = 500)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
