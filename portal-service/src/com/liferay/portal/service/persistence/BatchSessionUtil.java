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

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.BaseModel;

/**
 * @author     Brian Wing Shun Chan
 * @see        BatchSession
 * @deprecated As of 6.2.0, see LPS-30598.
 */
public class BatchSessionUtil {

	public static void delete(Session session, BaseModel<?> model)
		throws ORMException {

		getBatchSession().delete(session, model);
	}

	public static BatchSession getBatchSession() {
		return _batchSession;
	}

	public static boolean isEnabled() {
		return getBatchSession().isEnabled();
	}

	public static void setEnabled(boolean enabled) {
		getBatchSession().setEnabled(enabled);
	}

	public static void update(
			Session session, BaseModel<?> model, boolean merge)
		throws ORMException {

		getBatchSession().update(session, model, merge);
	}

	public void setBatchSession(BatchSession batchSession) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_batchSession = batchSession;
	}

	private static BatchSession _batchSession;

}