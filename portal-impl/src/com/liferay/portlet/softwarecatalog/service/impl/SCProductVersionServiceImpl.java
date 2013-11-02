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

package com.liferay.portlet.softwarecatalog.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.softwarecatalog.model.SCProductVersion;
import com.liferay.portlet.softwarecatalog.service.base.SCProductVersionServiceBaseImpl;
import com.liferay.portlet.softwarecatalog.service.permission.SCProductEntryPermission;

import java.util.List;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class SCProductVersionServiceImpl
	extends SCProductVersionServiceBaseImpl {

	@Override
	public SCProductVersion addProductVersion(
			long productEntryId, String version, String changeLog,
			String downloadPageURL, String directDownloadURL,
			boolean testDirectDownloadURL, boolean repoStoreArtifact,
			long[] frameworkVersionIds, ServiceContext serviceContext)
		throws PortalException, SystemException {

		SCProductEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.UPDATE);

		return scProductVersionLocalService.addProductVersion(
			getUserId(), productEntryId, version, changeLog, downloadPageURL,
			directDownloadURL, testDirectDownloadURL, repoStoreArtifact,
			frameworkVersionIds, serviceContext);
	}

	@Override
	public void deleteProductVersion(long productVersionId)
		throws PortalException, SystemException {

		SCProductVersion productVersion =
			scProductVersionLocalService.getProductVersion(productVersionId);

		SCProductEntryPermission.check(
			getPermissionChecker(), productVersion.getProductEntryId(),
			ActionKeys.UPDATE);

		scProductVersionLocalService.deleteProductVersion(productVersionId);
	}

	@Override
	public SCProductVersion getProductVersion(long productVersionId)
		throws PortalException, SystemException {

		SCProductVersion productVersion =
			scProductVersionLocalService.getProductVersion(productVersionId);

		SCProductEntryPermission.check(
			getPermissionChecker(), productVersion.getProductEntryId(),
			ActionKeys.VIEW);

		return productVersion;
	}

	@Override
	public List<SCProductVersion> getProductVersions(
			long productEntryId, int start, int end)
		throws PortalException, SystemException {

		SCProductEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.VIEW);

		return scProductVersionLocalService.getProductVersions(
			productEntryId, start, end);
	}

	@Override
	public int getProductVersionsCount(long productEntryId)
		throws PortalException, SystemException {

		SCProductEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.VIEW);

		return scProductVersionLocalService.getProductVersionsCount(
			productEntryId);
	}

	@Override
	public SCProductVersion updateProductVersion(
			long productVersionId, String version, String changeLog,
			String downloadPageURL, String directDownloadURL,
			boolean testDirectDownloadURL, boolean repoStoreArtifact,
			long[] frameworkVersionIds)
		throws PortalException, SystemException {

		SCProductVersion productVersion =
			scProductVersionLocalService.getProductVersion(productVersionId);

		SCProductEntryPermission.check(
			getPermissionChecker(), productVersion.getProductEntryId(),
			ActionKeys.UPDATE);

		return scProductVersionLocalService.updateProductVersion(
			productVersionId, version, changeLog, downloadPageURL,
			directDownloadURL, testDirectDownloadURL, repoStoreArtifact,
			frameworkVersionIds);
	}

}