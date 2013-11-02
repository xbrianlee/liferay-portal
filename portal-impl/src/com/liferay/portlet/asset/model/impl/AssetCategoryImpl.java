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

package com.liferay.portlet.asset.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetCategoryImpl extends AssetCategoryBaseImpl {

	public AssetCategoryImpl() {
	}

	@Override
	public List<AssetCategory> getAncestors()
		throws PortalException, SystemException {

		List<AssetCategory> categories = new ArrayList<AssetCategory>();

		AssetCategory category = this;

		while (!category.isRootCategory()) {
			category = AssetCategoryLocalServiceUtil.getAssetCategory(
				category.getParentCategoryId());

			categories.add(category);
		}

		return categories;
	}

	@Override
	public String getTitle(String languageId) {
		String value = super.getTitle(languageId);

		if (Validator.isNull(value)) {
			value = getName();
		}

		return value;
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		String value = super.getTitle(languageId, useDefault);

		if (Validator.isNull(value)) {
			value = getName();
		}

		return value;
	}

	@Override
	public boolean isRootCategory() {
		if (getParentCategoryId() == 0) {
			return true;
		}

		return false;
	}

}