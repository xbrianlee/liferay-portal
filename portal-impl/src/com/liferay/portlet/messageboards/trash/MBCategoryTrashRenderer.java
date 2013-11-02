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

package com.liferay.portlet.messageboards.trash;

import com.liferay.portal.kernel.trash.BaseTrashRenderer;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.messageboards.model.MBCategory;

import java.util.Locale;

/**
 * @author Eduardo Garcia
 */
public class MBCategoryTrashRenderer extends BaseTrashRenderer {

	public static final String TYPE = "category";

	public MBCategoryTrashRenderer(MBCategory category) {
		_category = category;
	}

	@Override
	public String getClassName() {
		return MBCategory.class.getName();
	}

	@Override
	public long getClassPK() {
		return _category.getPrimaryKey();
	}

	@Override
	public String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/category.png";
	}

	@Override
	public String getPortletId() {
		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				MBCategory.class.getName());

		return assetRendererFactory.getPortletId();
	}

	@Override
	public String getSummary(Locale locale) {
		return HtmlUtil.stripHtml(_category.getDescription());
	}

	@Override
	public String getTitle(Locale locale) {
		return _category.getName();
	}

	@Override
	public String getType() {
		return TYPE;
	}

	private MBCategory _category;

}