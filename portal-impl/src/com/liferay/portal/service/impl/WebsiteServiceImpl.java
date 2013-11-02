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
import com.liferay.portal.model.User;
import com.liferay.portal.model.Website;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.WebsiteServiceBaseImpl;
import com.liferay.portal.service.permission.CommonPermissionUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class WebsiteServiceImpl extends WebsiteServiceBaseImpl {

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #addWebsite( String, long,
	 *             String, int, boolean, ServiceContext)}
	 */
	@Override
	public Website addWebsite(
			String className, long classPK, String url, int typeId,
			boolean primary)
		throws PortalException, SystemException {

		CommonPermissionUtil.check(
			getPermissionChecker(), className, classPK, ActionKeys.UPDATE);

		return websiteLocalService.addWebsite(
			getUserId(), className, classPK, url, typeId, primary);
	}

	@Override
	public Website addWebsite(
			String className, long classPK, String url, int typeId,
			boolean primary, ServiceContext serviceContext)
		throws PortalException, SystemException {

		CommonPermissionUtil.check(
			getPermissionChecker(), className, classPK, ActionKeys.UPDATE);

		return websiteLocalService.addWebsite(
			getUserId(), className, classPK, url, typeId, primary,
			serviceContext);
	}

	@Override
	public void deleteWebsite(long websiteId)
		throws PortalException, SystemException {

		Website website = websitePersistence.findByPrimaryKey(websiteId);

		CommonPermissionUtil.check(
			getPermissionChecker(), website.getClassNameId(),
			website.getClassPK(), ActionKeys.UPDATE);

		websiteLocalService.deleteWebsite(websiteId);
	}

	@Override
	public Website getWebsite(long websiteId)
		throws PortalException, SystemException {

		Website website = websitePersistence.findByPrimaryKey(websiteId);

		CommonPermissionUtil.check(
			getPermissionChecker(), website.getClassNameId(),
			website.getClassPK(), ActionKeys.VIEW);

		return website;
	}

	@Override
	public List<Website> getWebsites(String className, long classPK)
		throws PortalException, SystemException {

		CommonPermissionUtil.check(
			getPermissionChecker(), className, classPK, ActionKeys.VIEW);

		User user = getUser();

		return websiteLocalService.getWebsites(
			user.getCompanyId(), className, classPK);
	}

	@Override
	public Website updateWebsite(
			long websiteId, String url, int typeId, boolean primary)
		throws PortalException, SystemException {

		Website website = websitePersistence.findByPrimaryKey(websiteId);

		CommonPermissionUtil.check(
			getPermissionChecker(), website.getClassNameId(),
			website.getClassPK(), ActionKeys.UPDATE);

		return websiteLocalService.updateWebsite(
			websiteId, url, typeId, primary);
	}

}