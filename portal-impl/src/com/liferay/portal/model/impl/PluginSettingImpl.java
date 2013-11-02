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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.PluginSetting;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class PluginSettingImpl extends PluginSettingBaseImpl {

	public PluginSettingImpl() {
	}

	public PluginSettingImpl(PluginSetting pluginSetting) {
		setCompanyId(pluginSetting.getCompanyId());
		setPluginId(pluginSetting.getPluginId());
		setPluginType(pluginSetting.getPluginType());
		setRoles(pluginSetting.getRoles());
		setActive(pluginSetting.getActive());
	}

	/**
	 * Adds a role to the list of roles.
	 */
	@Override
	public void addRole(String role) {
		setRolesArray(ArrayUtil.append(_rolesArray, role));
	}

	/**
	 * Returns an array of required roles of the plugin.
	 *
	 * @return an array of required roles of the plugin
	 */
	@Override
	public String[] getRolesArray() {
		return _rolesArray;
	}

	/**
	 * Returns <code>true</code> if the user has permission to use this plugin
	 *
	 * @param  userId the primary key of the user
	 * @return <code>true</code> if the user has permission to use this plugin
	 */
	@Override
	public boolean hasPermission(long userId) {
		try {
			if (_rolesArray.length == 0) {
				return true;
			}

			if (RoleLocalServiceUtil.hasUserRoles(
					userId, getCompanyId(), _rolesArray, true)) {

				return true;
			}

			if (RoleLocalServiceUtil.hasUserRole(
					userId, getCompanyId(), RoleConstants.ADMINISTRATOR,
					true)) {

				return true;
			}

			User user = UserLocalServiceUtil.getUserById(userId);

			if (user.isDefaultUser() && hasRoleWithName(RoleConstants.GUEST)) {
				return true;
			}
		}
		catch (Exception e) {
			_log.error(e);
		}

		return false;
	}

	/**
	 * Returns <code>true</code> if the plugin has a role with the specified
	 * name.
	 *
	 * @param  roleName the role name
	 * @return <code>true</code> if the plugin has a role with the specified
	 *         name
	 */
	@Override
	public boolean hasRoleWithName(String roleName) {
		for (int i = 0; i < _rolesArray.length; i++) {
			if (StringUtil.equalsIgnoreCase(_rolesArray[i], roleName)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Sets a string of ordered comma delimited plugin IDs.
	 */
	@Override
	public void setRoles(String roles) {
		_rolesArray = StringUtil.split(roles);

		super.setRoles(roles);
	}

	/**
	 * Sets an array of required roles of the plugin.
	 */
	@Override
	public void setRolesArray(String[] rolesArray) {
		_rolesArray = rolesArray;

		super.setRoles(StringUtil.merge(rolesArray));
	}

	/**
	 * Log instance for this class.
	 */
	private static Log _log = LogFactoryUtil.getLog(PluginSettingImpl.class);

	/**
	 * An array of required roles of the plugin.
	 */
	private String[] _rolesArray;

}