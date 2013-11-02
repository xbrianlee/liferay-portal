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

package com.liferay.portlet.asset.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portlet.asset.model.AssetCategoryProperty;
import com.liferay.portlet.asset.service.base.AssetCategoryPropertyServiceBaseImpl;
import com.liferay.portlet.asset.service.permission.AssetCategoryPermission;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class AssetCategoryPropertyServiceImpl
	extends AssetCategoryPropertyServiceBaseImpl {

	@Override
	public AssetCategoryProperty addCategoryProperty(
			long entryId, String key, String value)
		throws PortalException, SystemException {

		AssetCategoryPermission.check(
			getPermissionChecker(), entryId, ActionKeys.UPDATE);

		return assetCategoryPropertyLocalService.addCategoryProperty(
			getUserId(), entryId, key, value);
	}

	@Override
	public void deleteCategoryProperty(long categoryPropertyId)
		throws PortalException, SystemException {

		AssetCategoryProperty assetCategoryProperty =
			assetCategoryPropertyLocalService.getAssetCategoryProperty(
				categoryPropertyId);

		AssetCategoryPermission.check(
			getPermissionChecker(), assetCategoryProperty.getCategoryId(),
			ActionKeys.UPDATE);

		assetCategoryPropertyLocalService.deleteCategoryProperty(
			categoryPropertyId);
	}

	@Override
	public List<AssetCategoryProperty> getCategoryProperties(long entryId)
		throws SystemException {

		return assetCategoryPropertyLocalService.getCategoryProperties(entryId);
	}

	@Override
	public List<AssetCategoryProperty> getCategoryPropertyValues(
			long companyId, String key)
		throws SystemException {

		return assetCategoryPropertyLocalService.getCategoryPropertyValues(
			companyId, key);
	}

	@Override
	public AssetCategoryProperty updateCategoryProperty(
			long categoryPropertyId, String key, String value)
		throws PortalException, SystemException {

		AssetCategoryProperty assetCategoryProperty =
			assetCategoryPropertyLocalService.getAssetCategoryProperty(
				categoryPropertyId);

		AssetCategoryPermission.check(
			getPermissionChecker(), assetCategoryProperty.getCategoryId(),
			ActionKeys.UPDATE);

		return assetCategoryPropertyLocalService.updateCategoryProperty(
			categoryPropertyId, key, value);
	}

}