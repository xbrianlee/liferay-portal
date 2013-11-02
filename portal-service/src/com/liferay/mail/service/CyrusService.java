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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * @author Alexander Chow
 */
@Transactional(rollbackFor = {PortalException.class, SystemException.class})
public interface CyrusService {

	public void addUser(long userId, String emailAddress, String password)
		throws SystemException;

	public void deleteEmailAddress(long companyId, long userId)
		throws SystemException;

	public void deleteUser(long userId) throws SystemException;

	public void updateEmailAddress(
			long companyId, long userId, String emailAddress)
		throws SystemException;

	public void updatePassword(long companyId, long userId, String password)
		throws SystemException;

}