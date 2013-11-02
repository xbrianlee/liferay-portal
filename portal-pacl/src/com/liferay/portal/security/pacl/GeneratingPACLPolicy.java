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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.pacl.checker.AuthorizationProperty;
import com.liferay.portal.security.pacl.checker.Checker;
import com.liferay.portal.security.pacl.checker.JNDIChecker;
import com.liferay.portal.security.pacl.checker.SQLChecker;
import com.liferay.portal.util.PropsValues;

import java.io.IOException;

import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Raymond Augé
 */
public class GeneratingPACLPolicy extends ActivePACLPolicy {

	public GeneratingPACLPolicy(
		String servletContextName, ClassLoader classLoader,
		Properties properties) {

		super(servletContextName, classLoader, properties);
	}

	@Override
	public boolean hasJNDI(String name) {
		JNDIChecker jndiChecker = getJndiChecker();

		if (!jndiChecker.hasJNDI(name)) {
			AuthorizationProperty authorizationProperty =
				jndiChecker.generateAuthorizationProperty(name);

			if (authorizationProperty == null) {
				return false;
			}

			mergeAuthorizationProperty(authorizationProperty);
		}

		return true;
	}

	@Override
	public boolean hasSQL(String sql) {
		SQLChecker sqlChecker = getSqlChecker();

		if (!sqlChecker.hasSQL(sql)) {
			AuthorizationProperty authorizationProperty =
				sqlChecker.generateAuthorizationProperty(sql);

			if (authorizationProperty == null) {
				return false;
			}

			mergeAuthorizationProperty(authorizationProperty);
		}

		return true;
	}

	@Override
	public boolean implies(Permission permission) {
		Checker checker = getChecker(permission.getClass());

		if (checker.implies(permission)) {
			return true;
		}

		try {
			AuthorizationProperty authorizationProperty =
				checker.generateAuthorizationProperty(permission);

			if (authorizationProperty == null) {
				return false;
			}

			mergeAuthorizationProperty(authorizationProperty);
		}
		catch (Exception e) {
			return false;
		}

		return true;
	}

	protected void mergeAuthorizationProperty(
		AuthorizationProperty authorizationProperty) {

		AccessController.doPrivileged(
			new AuthorizationPropertyPrivilegedAction(authorizationProperty));
	}

	protected void mergeExistingProperties() {

		// Merge existing properties so that the the written policy is the
		// complete picture rather than only a list of the modified properties.
		// Therefore, the developer needs only to copy the entire policy.

		Properties properties = getProperties();

		Enumeration<Object> enumeration = properties.keys();

		while (enumeration.hasMoreElements()) {
			String key = (String)enumeration.nextElement();

			if (_properties.containsKey(key) ||
				!key.startsWith("security-manager-") ||
				key.equals("security-manager-enabled")) {

				continue;
			}

			_properties.put(key, getPropertySet(key));
		}
	}

	protected void writePACLPolicyFile() {
		try {
			StringBundler sb = new StringBundler();

			for (Map.Entry<String, Set<String>> entry :
					_properties.entrySet()) {

				String key = entry.getKey();

				sb.append(key);
				sb.append(StringPool.EQUAL);

				Set<String> values = entry.getValue();

				for (String value : values) {
					sb.append(StringPool.BACK_SLASH);
					sb.append(StringPool.NEW_LINE);
					sb.append(StringPool.FOUR_SPACES);
					sb.append(value);
					sb.append(StringPool.COMMA);
				}

				sb.setIndex(sb.index() - 1);

				sb.append("\n\n");
			}

			if (sb.length() > 0) {
				sb.setIndex(sb.index() - 1);
			}

			FileUtil.write(
				PropsValues.LIFERAY_HOME + "/pacl-policy",
				getServletContextName() + ".pacl-policy", sb.toString());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(GeneratingPACLPolicy.class);

	private Map<String, Set<String>> _properties =
		new ConcurrentSkipListMap<String, Set<String>>();
	private ReentrantLock _reentrantLock = new ReentrantLock();

	private class AuthorizationPropertyPrivilegedAction
		implements PrivilegedAction<Void> {

		public AuthorizationPropertyPrivilegedAction(
			AuthorizationProperty authorizationProperty) {

			_authorizationProperty = authorizationProperty;
		}

		@Override
		public Void run() {
			String key = _authorizationProperty.getKey();

			Set<String> values = _properties.get(key);

			boolean modified = false;

			if (values == null) {
				values = getPropertySet(key);

				modified = true;
			}

			for (String value : _authorizationProperty.getValues()) {
				if (!values.contains(value)) {
					values.add(value);

					modified = true;
				}
			}

			if (!modified) {
				return null;
			}

			_reentrantLock.lock();

			try {
				if (_log.isDebugEnabled()) {
					_log.debug(
						getServletContextName() +
							" generated authorization property " +
								_authorizationProperty);
				}

				_properties.put(key, values);

				mergeExistingProperties();

				writePACLPolicyFile();
			}
			finally {
				_reentrantLock.unlock();
			}

			return null;
		}

		private AuthorizationProperty _authorizationProperty;

	}

}