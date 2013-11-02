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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Account;
import com.liferay.portal.service.base.AccountLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class AccountLocalServiceImpl extends AccountLocalServiceBaseImpl {

	@Override
	public Account getAccount(long companyId, long accountId)
		throws PortalException, SystemException {

		return accountPersistence.findByPrimaryKey(accountId);
	}

}