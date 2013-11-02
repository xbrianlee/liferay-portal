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

package com.liferay.mail.service;

import com.liferay.mail.model.Filter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

import javax.mail.Session;

/**
 * @author Brian Wing Shun Chan
 */
@Transactional(rollbackFor = {PortalException.class, SystemException.class})
public interface MailService {

	public void addForward(
		long companyId, long userId, List<Filter> filters,
		List<String> emailAddresses, boolean leaveCopy);

	public void addUser(
		long companyId, long userId, String password, String firstName,
		String middleName, String lastName, String emailAddress);

	public void addVacationMessage(
		long companyId, long userId, String emailAddress,
		String vacationMessage);

	public void clearSession();

	public void deleteEmailAddress(long companyId, long userId);

	public void deleteUser(long companyId, long userId);

	public Session getSession() throws SystemException;

	public void sendEmail(MailMessage mailMessage);

	public void updateBlocked(
		long companyId, long userId, List<String> blocked);

	public void updateEmailAddress(
		long companyId, long userId, String emailAddress);

	public void updatePassword(long companyId, long userId, String password);

}