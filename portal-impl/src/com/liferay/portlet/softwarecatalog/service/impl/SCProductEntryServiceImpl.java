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
import com.liferay.portlet.softwarecatalog.model.SCProductEntry;
import com.liferay.portlet.softwarecatalog.service.base.SCProductEntryServiceBaseImpl;
import com.liferay.portlet.softwarecatalog.service.permission.SCPermission;
import com.liferay.portlet.softwarecatalog.service.permission.SCProductEntryPermission;

import java.util.List;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class SCProductEntryServiceImpl extends SCProductEntryServiceBaseImpl {

	@Override
	public SCProductEntry addProductEntry(
			String name, String type, String tags, String shortDescription,
			String longDescription, String pageURL, String author,
			String repoGroupId, String repoArtifactId, long[] licenseIds,
			List<byte[]> thumbnails, List<byte[]> fullImages,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		SCPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_PRODUCT_ENTRY);

		return scProductEntryLocalService.addProductEntry(
			getUserId(), name, type, tags, shortDescription, longDescription,
			pageURL, author, repoGroupId, repoArtifactId, licenseIds,
			thumbnails, fullImages, serviceContext);
	}

	@Override
	public void deleteProductEntry(long productEntryId)
		throws PortalException, SystemException {

		SCProductEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.DELETE);

		scProductEntryLocalService.deleteProductEntry(productEntryId);
	}

	@Override
	public SCProductEntry getProductEntry(long productEntryId)
		throws PortalException, SystemException {

		SCProductEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.VIEW);

		return scProductEntryLocalService.getProductEntry(productEntryId);
	}

	@Override
	public SCProductEntry updateProductEntry(
			long productEntryId, String name, String type, String tags,
			String shortDescription, String longDescription, String pageURL,
			String author, String repoGroupId, String repoArtifactId,
			long[] licenseIds, List<byte[]> thumbnails, List<byte[]> fullImages)
		throws PortalException, SystemException {

		SCProductEntryPermission.check(
			getPermissionChecker(), productEntryId, ActionKeys.UPDATE);

		return scProductEntryLocalService.updateProductEntry(
			productEntryId, name, type, tags, shortDescription, longDescription,
			pageURL, author, repoGroupId, repoArtifactId, licenseIds,
			thumbnails, fullImages);
	}

}