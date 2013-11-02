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

package com.liferay.portlet.assetpublisher.util;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portlet.asset.model.AssetEntry;

/**
 * @author Juan Fernández
 */
public class AssetPublisherHelperUtil {

	public static AssetPublisherHelper getAssetPublisherHelper() {
		return _assetPublisherHelper;
	}

	public static String getAssetViewURL(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, AssetEntry assetEntry) {

		return getAssetPublisherHelper().getAssetViewURL(
			liferayPortletRequest, liferayPortletResponse, assetEntry);
	}

	public void setAssetPublisherHelper(
		AssetPublisherHelper assetPublisherHelper) {

		_assetPublisherHelper = assetPublisherHelper;
	}

	private static AssetPublisherHelper _assetPublisherHelper;

}