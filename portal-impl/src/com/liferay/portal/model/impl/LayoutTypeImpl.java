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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutType;

/**
 * @author Brian Wing Shun Chan
 */
public class LayoutTypeImpl implements LayoutType {

	public LayoutTypeImpl(Layout layout) {
		setLayout(layout);
	}

	@Override
	public Layout getLayout() {
		return _layout;
	}

	@Override
	public UnicodeProperties getTypeSettingsProperties() {
		return _layout.getTypeSettingsProperties();
	}

	@Override
	public String getTypeSettingsProperty(String key) {
		return getTypeSettingsProperty(key, null);
	}

	@Override
	public String getTypeSettingsProperty(String key, String defaultValue) {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		return typeSettingsProperties.getProperty(key, defaultValue);
	}

	@Override
	public void setLayout(Layout layout) {
		_layout = layout;
	}

	@Override
	public void setTypeSettingsProperty(String key, String value) {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		typeSettingsProperties.setProperty(key, value);
	}

	private Layout _layout;

}