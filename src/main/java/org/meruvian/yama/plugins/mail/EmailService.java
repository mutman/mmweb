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
package org.meruvian.yama.plugins.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Dian Aditya
 * 
 */
@Service
public class EmailService {
	private HtmlEmail email;
	private String from;
	private String alias;

	@Autowired
	public EmailService(HtmlEmail email, @Value("${email.from}") String from,
			@Value("${email.alias}") String alias) {
		this.email = email;
		this.from = from;
		this.alias = alias;
	}

	public void sendMessage(String to, String subject, String message)
			throws EmailException {

		email.addTo(to);
		email.setFrom(from, alias);
		email.setSubject(subject);
		email.setHtmlMsg(message);

		email.send();
	}
}
