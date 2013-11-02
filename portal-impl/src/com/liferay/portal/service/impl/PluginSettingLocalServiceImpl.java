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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Plugin;
import com.liferay.portal.model.PluginSetting;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.PluginSettingImpl;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.base.PluginSettingLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Jorge Ferrer
 */
public class PluginSettingLocalServiceImpl
	extends PluginSettingLocalServiceBaseImpl {

	@Override
	public void checkPermission(long userId, String pluginId, String pluginType)
		throws PortalException {

		if (!hasPermission(userId, pluginId, pluginType)) {
			throw new PrincipalException();
		}
	}

	@Override
	public PluginSetting getDefaultPluginSetting() {
		PluginSettingImpl pluginSetting = new PluginSettingImpl();

		pluginSetting.setRoles(StringPool.BLANK);
		pluginSetting.setActive(true);

		return pluginSetting;
	}

	@Override
	public PluginSetting getPluginSetting(
			long companyId, String pluginId, String pluginType)
		throws SystemException {

		PluginSetting pluginSetting = pluginSettingPersistence.fetchByC_I_T(
			companyId, pluginId, pluginType);

		if (pluginSetting != null) {
			return pluginSetting;
		}

		Plugin plugin = null;

		if (pluginType.equals(Plugin.TYPE_LAYOUT_TEMPLATE)) {
			plugin = layoutTemplateLocalService.getLayoutTemplate(
				pluginId, false, null);
		}
		else if (pluginType.equals(Plugin.TYPE_THEME)) {
			boolean wapTheme = true;

			plugin = themeLocalService.getTheme(companyId, pluginId, wapTheme);
		}

		if ((plugin == null) || (plugin.getDefaultPluginSetting() == null)) {
			pluginSetting = getDefaultPluginSetting();

			pluginSetting.setCompanyId(companyId);
		}
		else {
			pluginSetting = plugin.getDefaultPluginSetting(companyId);
		}

		return pluginSetting;
	}

	@Override
	public boolean hasPermission(
		long userId, String pluginId, String pluginType) {

		try {
			User user = userPersistence.findByPrimaryKey(userId);

			PluginSetting pluginSetting = getPluginSetting(
				user.getCompanyId(), pluginId, pluginType);

			if (!pluginSetting.hasPermission(userId)) {
				return false;
			}
			else {
				return true;
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Could not check permissions for " + pluginId, e);
			}

			return false;
		}
	}

	@Override
	public PluginSetting updatePluginSetting(
			long companyId, String pluginId, String pluginType, String roles,
			boolean active)
		throws SystemException {

		pluginId = PortalUtil.getJsSafePortletId(pluginId);

		PluginSetting pluginSetting = pluginSettingPersistence.fetchByC_I_T(
			companyId, pluginId, pluginType);

		if (pluginSetting == null) {
			long pluginSettingId = counterLocalService.increment();

			pluginSetting = pluginSettingPersistence.create(pluginSettingId);

			pluginSetting.setCompanyId(companyId);
			pluginSetting.setPluginId(pluginId);
			pluginSetting.setPluginType(pluginType);
		}

		pluginSetting.setRoles(roles);
		pluginSetting.setActive(active);

		pluginSettingPersistence.update(pluginSetting);

		return pluginSetting;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PluginSettingLocalServiceImpl.class);

}