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

import com.liferay.portal.NoSuchWebDAVPropsException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.model.WebDAVProps;
import com.liferay.portal.service.base.WebDAVPropsLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;

/**
 * @author Alexander Chow
 */
public class WebDAVPropsLocalServiceImpl
	extends WebDAVPropsLocalServiceBaseImpl {

	@Override
	public void deleteWebDAVProps(String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		try {
			webDAVPropsPersistence.removeByC_C(classNameId, classPK);
		}
		catch (NoSuchWebDAVPropsException nswdavpe) {
		}
	}

	@Override
	public WebDAVProps getWebDAVProps(
			long companyId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		WebDAVProps webDavProps = webDAVPropsPersistence.fetchByC_C(
			classNameId, classPK);

		if (webDavProps == null) {
			webDavProps = webDAVPropsPersistence.create(
				counterLocalService.increment());

			Date now = new Date();

			webDavProps.setCompanyId(companyId);
			webDavProps.setCreateDate(now);
			webDavProps.setModifiedDate(now);
			webDavProps.setClassNameId(classNameId);
			webDavProps.setClassPK(classPK);

			webDAVPropsLocalService.updateWebDAVProps(webDavProps);
		}

		return webDavProps;
	}

	@Override
	public void storeWebDAVProps(WebDAVProps webDavProps)
		throws PortalException, SystemException {

		try {
			webDavProps.store();
		}
		catch (Exception e) {
			throw new WebDAVException("Problem trying to store WebDAVProps", e);
		}

		webDAVPropsPersistence.update(webDavProps);
	}

}