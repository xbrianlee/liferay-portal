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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.base.ThemeServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ThemeServiceImpl extends ThemeServiceBaseImpl {

	@Override
	public List<Theme> getThemes(long companyId) {
		return themeLocalService.getThemes(companyId);
	}

	@Override
	public JSONArray getWARThemes() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<Theme> themes = themeLocalService.getWARThemes();

		for (Theme theme : themes) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("theme_id", theme.getThemeId());
			jsonObject.put("theme_name", theme.getName());
			jsonObject.put(
				"servlet_context_name", theme.getServletContextName());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

}