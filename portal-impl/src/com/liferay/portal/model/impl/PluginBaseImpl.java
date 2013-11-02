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

import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.model.Plugin;
import com.liferay.portal.model.PluginSetting;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jorge Ferrer
 */
public abstract class PluginBaseImpl implements Plugin {

	@Override
	public PluginPackage getPluginPackage() {
		return _pluginPackage;
	}

	@Override
	public void setPluginPackage(PluginPackage pluginPackage) {
		_pluginPackage = pluginPackage;
	}

	@Override
	public PluginSetting getDefaultPluginSetting() {
		return _defaultPluginSetting;
	}

	@Override
	public PluginSetting getDefaultPluginSetting(long companyId) {
		PluginSetting setting = _defaultPluginSettings.get(companyId);

		if (setting == null) {
			setting = new PluginSettingImpl(_defaultPluginSetting);

			setting.setCompanyId(companyId);

			_defaultPluginSettings.put(companyId, setting);
		}

		return setting;
	}

	@Override
	public void setDefaultPluginSetting(PluginSetting pluginSetting) {
		_defaultPluginSetting = pluginSetting;
	}

	private PluginPackage _pluginPackage;
	private PluginSetting _defaultPluginSetting;
	private Map<Long, PluginSetting> _defaultPluginSettings =
		new HashMap<Long, PluginSetting>();

}