/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.test.mail;

import com.liferay.mail.model.Filter;
import com.liferay.mail.service.MailService;
import com.liferay.portal.kernel.mail.MailMessage;

import java.util.List;

import javax.mail.Session;

/**
 * @author Sergio González
 */
public class MockMailServiceImpl implements MailService {

	@Override
	public void addForward(
		long companyId, long userId, List<Filter> filters,
		List<String> emailAddresses, boolean leaveCopy) {
	}

	@Override
	public void addUser(
		long companyId, long userId, String password, String firstName,
		String middleName, String lastName, String emailAddress) {
	}

	@Override
	public void addVacationMessage(
		long companyId, long userId, String emailAddress,
		String vacationMessage) {
	}

	@Override
	public void clearSession() {
	}

	@Override
	public void deleteEmailAddress(long companyId, long userId) {
	}

	@Override
	public void deleteUser(long companyId, long userId) {
	}

	@Override
	public Session getSession() {
		return null;
	}

	@Override
	public void sendEmail(MailMessage mailMessage) {
	}

	@Override
	public void updateBlocked(
		long companyId, long userId, List<String> blocked) {
	}

	@Override
	public void updateEmailAddress(
		long companyId, long userId, String emailAddress) {
	}

	@Override
	public void updatePassword(long companyId, long userId, String password) {
	}

}