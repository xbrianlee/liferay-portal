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
import com.liferay.portal.model.User;
import com.liferay.portlet.asset.CategoryPropertyKeyException;
import com.liferay.portlet.asset.CategoryPropertyValueException;
import com.liferay.portlet.asset.model.AssetCategoryProperty;
import com.liferay.portlet.asset.service.base.AssetCategoryPropertyLocalServiceBaseImpl;
import com.liferay.portlet.asset.util.AssetUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class AssetCategoryPropertyLocalServiceImpl
	extends AssetCategoryPropertyLocalServiceBaseImpl {

	@Override
	public AssetCategoryProperty addCategoryProperty(
			long userId, long categoryId, String key, String value)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(key, value);

		long categoryPropertyId = counterLocalService.increment();

		AssetCategoryProperty categoryProperty =
			assetCategoryPropertyPersistence.create(categoryPropertyId);

		categoryProperty.setCompanyId(user.getCompanyId());
		categoryProperty.setUserId(user.getUserId());
		categoryProperty.setUserName(user.getFullName());
		categoryProperty.setCreateDate(now);
		categoryProperty.setModifiedDate(now);
		categoryProperty.setCategoryId(categoryId);
		categoryProperty.setKey(key);
		categoryProperty.setValue(value);

		assetCategoryPropertyPersistence.update(categoryProperty);

		return categoryProperty;
	}

	@Override
	public void deleteCategoryProperties(long entryId) throws SystemException {
		List<AssetCategoryProperty> categoryProperties =
			assetCategoryPropertyPersistence.findByCategoryId(entryId);

		for (AssetCategoryProperty categoryProperty : categoryProperties) {
			deleteCategoryProperty(categoryProperty);
		}
	}

	@Override
	public void deleteCategoryProperty(AssetCategoryProperty categoryProperty)
		throws SystemException {

		assetCategoryPropertyPersistence.remove(categoryProperty);
	}

	@Override
	public void deleteCategoryProperty(long categoryPropertyId)
		throws PortalException, SystemException {

		AssetCategoryProperty categoryProperty =
			assetCategoryPropertyPersistence.findByPrimaryKey(
				categoryPropertyId);

		deleteCategoryProperty(categoryProperty);
	}

	@Override
	public List<AssetCategoryProperty> getCategoryProperties()
		throws SystemException {

		return assetCategoryPropertyPersistence.findAll();
	}

	@Override
	public List<AssetCategoryProperty> getCategoryProperties(long entryId)
		throws SystemException {

		return assetCategoryPropertyPersistence.findByCategoryId(entryId);
	}

	@Override
	public AssetCategoryProperty getCategoryProperty(long categoryPropertyId)
		throws PortalException, SystemException {

		return assetCategoryPropertyPersistence.findByPrimaryKey(
			categoryPropertyId);
	}

	@Override
	public AssetCategoryProperty getCategoryProperty(
			long categoryId, String key)
		throws PortalException, SystemException {

		return assetCategoryPropertyPersistence.findByCA_K(categoryId, key);
	}

	@Override
	public List<AssetCategoryProperty> getCategoryPropertyValues(
			long groupId, String key)
		throws SystemException {

		return assetCategoryPropertyFinder.findByG_K(groupId, key);
	}

	@Override
	public AssetCategoryProperty updateCategoryProperty(
			long categoryPropertyId, String key, String value)
		throws PortalException, SystemException {

		validate(key, value);

		AssetCategoryProperty categoryProperty =
			assetCategoryPropertyPersistence.findByPrimaryKey(
				categoryPropertyId);

		categoryProperty.setModifiedDate(new Date());
		categoryProperty.setKey(key);
		categoryProperty.setValue(value);

		assetCategoryPropertyPersistence.update(categoryProperty);

		return categoryProperty;
	}

	protected void validate(String key, String value) throws PortalException {
		if (!AssetUtil.isValidWord(key)) {
			throw new CategoryPropertyKeyException();
		}

		if (!AssetUtil.isValidWord(value)) {
			throw new CategoryPropertyValueException();
		}
	}

}