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

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.net.SocketPermission;

import java.security.Permission;
import java.security.Permissions;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class SocketChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
		initAcceptHostsAndPorts();
		initConnectHostsAndPorts();
		initListenPorts();
	}

	@Override
	public AuthorizationProperty generateAuthorizationProperty(
		Object... arguments) {

		if ((arguments == null) || (arguments.length != 1) ||
			!(arguments[0] instanceof Permission)) {

			return null;
		}

		Permission permission = (Permission)arguments[0];

		String actions = permission.getActions();

		if (actions.equals(SOCKET_PERMISSION_RESOLVE)) {

			// There is no need for an authorization property because this
			// action is always allowed

			return null;
		}

		String name = permission.getName();

		int index = name.indexOf(StringPool.COLON);

		int port = GetterUtil.getInteger(name.substring(index + 1));

		String key = null;
		String value = null;

		if (actions.contains(SOCKET_PERMISSION_ACCEPT)) {
			key = "security-manager-sockets-accept";
			value = name;
		}
		else if (actions.contains(SOCKET_PERMISSION_CONNECT)) {
			key = "security-manager-sockets-connect";
			value = name;
		}
		else if (actions.contains(SOCKET_PERMISSION_LISTEN)) {
			key = "security-manager-sockets-listen";
			value = String.valueOf(port);
		}
		else {
			return null;
		}

		AuthorizationProperty authorizationProperty =
			new AuthorizationProperty();

		authorizationProperty.setKey(key);
		authorizationProperty.setValue(value);

		return authorizationProperty;
	}

	@Override
	public boolean implies(Permission permission) {
		String actions = permission.getActions();
		String name = permission.getName();

		if (!_permissions.implies(permission)) {
			logSecurityException(
				_log, "Attempted " + actions + " for address " + name);

			return false;
		}

		return true;
	}

	protected void initAcceptHostsAndPorts() {
		String[] networkParts = getPropertyArray(
			"security-manager-sockets-accept");

		for (String networkPart : networkParts) {
			initHostsAndPorts(networkPart, SOCKET_PERMISSION_ACCEPT);
		}
	}

	protected void initConnectHostsAndPorts() {
		String[] networkParts = getPropertyArray(
			"security-manager-sockets-connect");

		for (String networkPart : networkParts) {
			initHostsAndPorts(networkPart, SOCKET_PERMISSION_CONNECT);
		}
	}

	protected void initHostsAndPorts(String networkPart, String action) {
		SocketPermission socketPermission = new SocketPermission(
			networkPart, action);

		_permissions.add(socketPermission);
	}

	protected void initListenPorts() {
		String[] listenParts = getPropertyArray(
			"security-manager-sockets-listen");

		for (String listenPart : listenParts) {
			initListenPorts(listenPart);
		}
	}

	protected void initListenPorts(String listenPart) {
		initHostsAndPorts("*:" + listenPart, SOCKET_PERMISSION_LISTEN);
	}

	private static Log _log = LogFactoryUtil.getLog(SocketChecker.class);

	private Permissions _permissions = new Permissions();

}