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

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.cache.CacheRegistryItem;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.security.lang.PortalSecurityManager;
import com.liferay.portal.security.lang.SecurityManagerUtil;

import java.security.AccessController;
import java.security.BasicPermission;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

/**
 * @author Raymond Augé
 */
public class PACLUtil {

	public static Class<?> getClass(Object object) {
		Class<?> clazz = object.getClass();

		if (object instanceof Class) {
			clazz = (Class<?>)object;
		}

		if (ProxyUtil.isProxyClass(clazz) || !clazz.isInterface()) {
			Class<?>[] interfaces = clazz.getInterfaces();

			if (interfaces.length > 0) {
				clazz = interfaces[0];

				if (clazz.equals(CacheRegistryItem.class) &&
					(interfaces.length > 1)) {

					clazz = interfaces[1];
				}
			}
		}

		return clazz;
	}

	public static PACLPolicy getPACLPolicy() {
		SecurityManager securityManager = System.getSecurityManager();

		try {
			securityManager.checkPermission(_permission);

			PACLPolicy paclPolicy = PACLPolicyThreadLocal.get();

			return paclPolicy;
		}
		finally {
			PACLPolicyThreadLocal.set(null);
		}
	}

	public static String getServiceInterfaceName(String serviceClassName) {
		int pos = serviceClassName.indexOf(".impl.");

		if (pos != -1) {
			serviceClassName =
				serviceClassName.substring(0, pos + 1) +
					serviceClassName.substring(pos + 6);
		}

		if (serviceClassName.endsWith("Impl")) {
			serviceClassName = serviceClassName.substring(
				0, serviceClassName.length() - 4);
		}

		return serviceClassName;
	}

	public static boolean hasSameOrigin(Class<?> callerClass) {
		PACLPolicy paclPolicy = getPACLPolicy();

		if (paclPolicy == null) {
			return true;
		}

		ProtectionDomain protectionDomain = AccessController.doPrivileged(
			new ProtectionDomainPrivilegedAction(callerClass));

		PortalSecurityManager portalSecurityManager =
			SecurityManagerUtil.getPortalSecurityManager();

		Policy portalPolicy = portalSecurityManager.getPolicy();

		PermissionCollection permissionCollection = portalPolicy.getPermissions(
			protectionDomain);

		return _hasSameOrigin(
			protectionDomain, permissionCollection, paclPolicy);
	}

	public static boolean isTrustedCaller(
		Class<?> callerClass, java.security.Permission permission,
		PACLPolicy paclPolicy) {

		ProtectionDomain protectionDomain = AccessController.doPrivileged(
			new ProtectionDomainPrivilegedAction(callerClass));

		return _isTrustedCaller(protectionDomain, permission, paclPolicy);
	}

	public static class Permission extends BasicPermission {

		public Permission() {
			super("getPACLPolicy");
		}

	}

	private static boolean _hasSameOrigin(
		ProtectionDomain protectionDomain,
		PermissionCollection permissionCollection, PACLPolicy paclPolicy) {

		PACLPolicy callerPACLPolicy = null;

		if (permissionCollection instanceof PortalPermissionCollection) {
			PortalPermissionCollection portalPermissionCollection =
				(PortalPermissionCollection)permissionCollection;

			callerPACLPolicy = portalPermissionCollection.getPACLPolicy();
		}
		else {
			callerPACLPolicy = PACLPolicyManager.getPACLPolicy(
				protectionDomain);
		}

		if (paclPolicy == callerPACLPolicy) {
			return true;
		}

		return false;
	}

	private static boolean _isTrustedCaller(
		ProtectionDomain protectionDomain, java.security.Permission permission,
		PACLPolicy paclPolicy) {

		PortalSecurityManager portalSecurityManager =
			SecurityManagerUtil.getPortalSecurityManager();

		Policy portalPolicy = portalSecurityManager.getPolicy();

		PermissionCollection permissionCollection = portalPolicy.getPermissions(
			protectionDomain);

		if (!_hasSameOrigin(
				protectionDomain, permissionCollection, paclPolicy) &&
			permissionCollection.implies(permission)) {

			return true;
		}

		return false;
	}

	private static Permission _permission = new PACLUtil.Permission();

	private static class ProtectionDomainPrivilegedAction
		implements PrivilegedAction<ProtectionDomain> {

		public ProtectionDomainPrivilegedAction(Class<?> clazz) {
			_clazz = clazz;
		}

		@Override
		public ProtectionDomain run() {
			return _clazz.getProtectionDomain();
		}

		private Class<?> _clazz;

	}

}