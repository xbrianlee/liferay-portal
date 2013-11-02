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

package com.liferay.taglib.theme;

import com.liferay.portal.model.Account;
import com.liferay.portal.model.ColorScheme;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Theme;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * @author Brian Wing Shun Chan
 */
public class DefineObjectsTei extends TagExtraInfo {

	@Override
	public VariableInfo[] getVariableInfo(TagData tagData) {
		return _variableInfo;
	}

	private static VariableInfo[] _variableInfo = new VariableInfo[] {
		new VariableInfo(
			"themeDisplay", ThemeDisplay.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"company", Company.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"account", Account.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"user", User.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"realUser", User.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"contact", Contact.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"layout", Layout.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"layouts", List.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"plid", Long.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"layoutTypePortlet", LayoutTypePortlet.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"scopeGroupId", Long.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"permissionChecker", PermissionChecker.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"locale", Locale.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"timeZone", TimeZone.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"theme", Theme.class.getName(), true, VariableInfo.AT_END),
		new VariableInfo(
			"colorScheme", ColorScheme.class.getName(), true,
			VariableInfo.AT_END),
		new VariableInfo(
			"portletDisplay", PortletDisplay.class.getName(), true,
			VariableInfo.AT_END),

		// Deprecated

		new VariableInfo(
			"portletGroupId", Long.class.getName(), true, VariableInfo.AT_END)
	};

}