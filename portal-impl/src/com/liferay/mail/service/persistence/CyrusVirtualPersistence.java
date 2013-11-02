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

import com.liferay.mail.NoSuchCyrusVirtualException;
import com.liferay.mail.model.CyrusVirtual;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Dummy;
import com.liferay.portal.service.persistence.BasePersistence;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public interface CyrusVirtualPersistence extends BasePersistence<Dummy> {

	public CyrusVirtual findByPrimaryKey(String emailAddress)
		throws NoSuchCyrusVirtualException, SystemException;

	public List<CyrusVirtual> findByUserId(long userId) throws SystemException;

	public void remove(String emailAddress)
		throws NoSuchCyrusVirtualException, SystemException;

	public void removeByUserId(long userId) throws SystemException;

	public void update(CyrusVirtual virtual) throws SystemException;

}