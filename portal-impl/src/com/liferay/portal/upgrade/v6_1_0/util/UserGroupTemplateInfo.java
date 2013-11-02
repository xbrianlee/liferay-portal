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

package com.liferay.portal.upgrade.v6_1_0.util;

/**
 * @author Miguel Pastor
 */
public class UserGroupTemplateInfo {

	public String getColorSchemeId() {
		return _colorSchemeId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public String getCss() {
		return _css;
	}

	public long getGroupId() {
		return _groupId;
	}

	public long getLayoutSetId() {
		return _layoutSetId;
	}

	public long getLayoutSetPrototypeId() {
		return _layoutSetPrototypeId;
	}

	public short getLogo() {
		return _logo;
	}

	public long getLogoId() {
		return _logoId;
	}

	public long getPageCount() {
		return _pageCount;
	}

	public String getSettings() {
		return _settings;
	}

	public String getThemeId() {
		return _themeId;
	}

	public long getUserGroupId() {
		return _userGroupId;
	}

	public String getWapColorSchemeId() {
		return _wapColorSchemeId;
	}

	public String getWapThemeId() {
		return _wapThemeId;
	}

	public boolean isPrivateLayout() {
		return _privateLayout;
	}

	public void setColorSchemeId(String colorSchemeId) {
		_colorSchemeId = colorSchemeId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setCss(String css) {
		_css = css;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setLayoutSetId(long layoutSetId) {
		_layoutSetId = layoutSetId;
	}

	public void setLayoutSetPrototypeId(long layoutSetPrototypeId) {
		_layoutSetPrototypeId = layoutSetPrototypeId;
	}

	public void setLogo(short logo) {
		_logo = logo;
	}

	public void setLogoId(long logoId) {
		_logoId = logoId;
	}

	public void setPageCount(long pageCount) {
		_pageCount = pageCount;
	}

	public void setPrivateLayout(boolean privateLayout) {
		_privateLayout = privateLayout;
	}

	public void setSettings(String settings) {
		_settings = settings;
	}

	public void setThemeId(String themeId) {
		_themeId = themeId;
	}

	public void setUserGroupId(long userGroupId) {
		_userGroupId = userGroupId;
	}

	public void setWapColorSchemeId(String wapColorSchemeId) {
		_wapColorSchemeId = wapColorSchemeId;
	}

	public void setWapThemeId(String wapThemeId) {
		_wapThemeId = wapThemeId;
	}

	private String _colorSchemeId;
	private long _companyId;
	private String _css;
	private long _groupId;
	private long _layoutSetId;
	private long _layoutSetPrototypeId;
	private short _logo;
	private long _logoId;
	private long _pageCount;
	private boolean _privateLayout;
	private String _settings;
	private String _themeId;
	private long _userGroupId;
	private String _wapColorSchemeId;
	private String _wapThemeId;

}