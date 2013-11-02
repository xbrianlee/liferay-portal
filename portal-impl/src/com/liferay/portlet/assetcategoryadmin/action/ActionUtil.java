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

package com.liferay.portlet.assetcategoryadmin.action;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyServiceUtil;

import java.util.List;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Iliyan Peychev
 * @author Julio Camarero
 */
public class ActionUtil {

	public static void getCategory(HttpServletRequest request)
		throws Exception {

		long categoryId = ParamUtil.getLong(request, "categoryId");

		AssetCategory category = null;

		if (categoryId > 0) {
			category = AssetCategoryServiceUtil.getCategory(categoryId);
		}

		request.setAttribute(WebKeys.ASSET_CATEGORY, category);
	}

	public static void getCategory(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getCategory(request);
	}

	public static void getVocabularies(HttpServletRequest request)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<AssetVocabulary> vocabularies =
			AssetVocabularyServiceUtil.getGroupVocabularies(
				themeDisplay.getScopeGroupId());

		request.setAttribute(WebKeys.ASSET_VOCABULARIES, vocabularies);
	}

	public static void getVocabularies(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getVocabularies(request);
	}

	public static void getVocabulary(HttpServletRequest request)
		throws Exception {

		long vocabularyId = ParamUtil.getLong(request, "vocabularyId");

		AssetVocabulary vocabulary = null;

		if (vocabularyId > 0) {
			vocabulary = AssetVocabularyServiceUtil.getVocabulary(vocabularyId);
		}

		request.setAttribute(WebKeys.ASSET_VOCABULARY, vocabulary);
	}

	public static void getVocabulary(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getVocabulary(request);
	}

}