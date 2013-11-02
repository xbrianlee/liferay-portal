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

package com.liferay.mail.service.impl;

import com.liferay.mail.NoSuchCyrusUserException;
import com.liferay.mail.model.CyrusUser;
import com.liferay.mail.model.CyrusVirtual;
import com.liferay.mail.service.CyrusService;
import com.liferay.mail.service.persistence.CyrusUserUtil;
import com.liferay.mail.service.persistence.CyrusVirtualUtil;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

/**
 * @author Alexander Chow
 */
@DoPrivileged
public class CyrusServiceImpl implements CyrusService, IdentifiableBean {

	@Override
	public void addUser(long userId, String emailAddress, String password)
		throws SystemException {

		CyrusUser cyrusUser = new CyrusUser(userId, password);

		CyrusUserUtil.update(cyrusUser);

		CyrusVirtual cyrusVirtual = new CyrusVirtual(emailAddress, userId);

		CyrusVirtualUtil.update(cyrusVirtual);
	}

	@Override
	public void deleteEmailAddress(long companyId, long userId)
		throws SystemException {

		CyrusVirtualUtil.removeByUserId(userId);
	}

	@Override
	public void deleteUser(long userId) throws SystemException {
		try {
			CyrusUserUtil.remove(userId);
		}
		catch (NoSuchCyrusUserException nscue) {
		}

		CyrusVirtualUtil.removeByUserId(userId);
	}

	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public void updateEmailAddress(
			long companyId, long userId, String emailAddress)
		throws SystemException {

		CyrusVirtualUtil.removeByUserId(userId);

		CyrusVirtual cyrusVirtual = new CyrusVirtual(emailAddress, userId);

		CyrusVirtualUtil.update(cyrusVirtual);
	}

	@Override
	public void updatePassword(long companyId, long userId, String password)
		throws SystemException {

		CyrusUser cyrusUser = null;

		try {
			cyrusUser = CyrusUserUtil.findByPrimaryKey(userId);
		}
		catch (NoSuchCyrusUserException nscue) {
			cyrusUser = new CyrusUser(userId, password);
		}

		cyrusUser.setPassword(password);

		CyrusUserUtil.update(cyrusUser);
	}

	private String _beanIdentifier;

}