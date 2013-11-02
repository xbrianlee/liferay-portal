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

package com.liferay.mail.service.persistence;

import com.liferay.mail.NoSuchCyrusUserException;
import com.liferay.mail.model.CyrusUser;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Dummy;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * @author Brian Wing Shun Chan
 */
public interface CyrusUserPersistence extends BasePersistence<Dummy> {

	public CyrusUser findByPrimaryKey(long userId)
		throws NoSuchCyrusUserException, SystemException;

	public void remove(long userId)
		throws NoSuchCyrusUserException, SystemException;

	public void update(CyrusUser user) throws SystemException;

}