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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Juan Fernández
 * @author Shuyang Zhou
 */
public class InputAssetLinksTag extends AssetLinksTag {

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		long assetEntryId = getAssetEntryId();
		String className = getClassName();
		long classPK = getClassPK();

		if ((assetEntryId <= 0) && (classPK > 0)) {
			try {
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
					className, classPK);

				if (assetEntry != null) {
					assetEntryId = assetEntry.getEntryId();
				}
			}
			catch (SystemException se) {
			}
		}

		request.setAttribute(
			"liferay-ui:input-asset-links:assetEntryId",
			String.valueOf(assetEntryId));
		request.setAttribute(
			"liferay-ui:input-asset-links:className", className);
	}

	private static final String _PAGE =
		"/html/taglib/ui/input_asset_links/page.jsp";

}