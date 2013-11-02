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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;

import javax.portlet.PortletURL;

/**
 * @author Juan Fernández
 */
public class AssetPublisherHelperImpl implements AssetPublisherHelper {

	@Override
	public String getAssetViewURL(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, AssetEntry assetEntry) {

		PortletURL viewURL = liferayPortletResponse.createRenderURL();

		viewURL.setParameter("struts_action", "/asset_publisher/view_content");

		String currentURL = PortalUtil.getCurrentURL(liferayPortletRequest);

		viewURL.setParameter("redirect", currentURL);

		viewURL.setParameter(
			"assetEntryId", String.valueOf(assetEntry.getEntryId()));

		AssetRendererFactory assetRendererFactory =
			assetEntry.getAssetRendererFactory();

		AssetRenderer assetRenderer = assetEntry.getAssetRenderer();

		viewURL.setParameter("type", assetRendererFactory.getType());

		if (Validator.isNotNull(assetRenderer.getUrlTitle())) {
			viewURL.setParameter("urlTitle", assetRenderer.getUrlTitle());
		}

		return viewURL.toString();
	}

}