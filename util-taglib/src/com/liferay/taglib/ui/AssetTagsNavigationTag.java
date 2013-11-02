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

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alvaro del Castillo
 * @author Eduardo Lundgren
 * @author Jorge Ferrer
 */
public class AssetTagsNavigationTag extends IncludeTag {

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public void setDisplayStyle(String displayStyle) {
		_displayStyle = displayStyle;
	}

	public void setHidePortletWhenEmpty(boolean hidePortletWhenEmpty) {
		_hidePortletWhenEmpty = hidePortletWhenEmpty;
	}

	public void setMaxAssetTags(int maxAssetTags) {
		_maxAssetTags = maxAssetTags;
	}

	public void setShowAssetCount(boolean showAssetCount) {
		_showAssetCount = showAssetCount;
	}

	public void setShowZeroAssetCount(boolean showZeroAssetCount) {
		_showZeroAssetCount = showZeroAssetCount;
	}

	@Override
	protected void cleanUp() {
		_classNameId = 0;
		_displayStyle = "cloud";
		_hidePortletWhenEmpty = false;
		_maxAssetTags = 0;
		_showAssetCount = false;
		_showZeroAssetCount = false;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:asset-tags-navigation:classNameId",
			String.valueOf(_classNameId));
		request.setAttribute(
			"liferay-ui:asset-tags-navigation:displayStyle", _displayStyle);
		request.setAttribute(
			"liferay-ui:asset-tags-navigation:hidePortletWhenEmpty",
			String.valueOf(_hidePortletWhenEmpty));
		request.setAttribute(
			"liferay-ui:asset-tags-navigation:maxAssetTags",
			String.valueOf(_maxAssetTags));
		request.setAttribute(
			"liferay-ui:asset-tags-navigation:showAssetCount",
			String.valueOf(_showAssetCount));
		request.setAttribute(
			"liferay-ui:asset-tags-navigation:showZeroAssetCount",
			String.valueOf(_showZeroAssetCount));
	}

	private static final String _PAGE =
		"/html/taglib/ui/asset_tags_navigation/page.jsp";

	private long _classNameId;
	private String _displayStyle = "cloud";
	private boolean _hidePortletWhenEmpty;
	private int _maxAssetTags = 10;
	private boolean _showAssetCount;
	private boolean _showZeroAssetCount;

}