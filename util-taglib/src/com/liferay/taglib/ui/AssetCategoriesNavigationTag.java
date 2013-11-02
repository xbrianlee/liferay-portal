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
public class AssetCategoriesNavigationTag extends IncludeTag {

	public void setHidePortletWhenEmpty(boolean hidePortletWhenEmpty) {
		_hidePortletWhenEmpty = hidePortletWhenEmpty;
	}

	public void setVocabularyIds(long[] vocabularyIds) {
		_vocabularyIds = vocabularyIds;
	}

	@Override
	protected void cleanUp() {
		_hidePortletWhenEmpty = false;
		_vocabularyIds = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:asset-tags-navigation:hidePortletWhenEmpty",
			String.valueOf(_hidePortletWhenEmpty));
		request.setAttribute(
			"liferay-ui:asset-tags-navigation:vocabularyIds", _vocabularyIds);
	}

	private static final String _PAGE =
		"/html/taglib/ui/asset_categories_navigation/page.jsp";

	private boolean _hidePortletWhenEmpty;
	private long[] _vocabularyIds;

}