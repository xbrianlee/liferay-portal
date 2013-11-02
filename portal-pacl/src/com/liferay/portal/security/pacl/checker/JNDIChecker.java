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

import java.security.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class JNDIChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
		initNames();
	}

	@Override
	public AuthorizationProperty generateAuthorizationProperty(
		Object... arguments) {

		if ((arguments == null) || (arguments.length != 1) ||
			!(arguments[0] instanceof String)) {

			return null;
		}

		AuthorizationProperty authorizationProperty =
			new AuthorizationProperty();

		authorizationProperty.setKey("security-manager-jndi-names");
		authorizationProperty.setValue((String)arguments[0]);

		return authorizationProperty;
	}

	public boolean hasJNDI(String name) {
		for (Pattern pattern : _patterns) {
			Matcher matcher = pattern.matcher(name);

			if (matcher.matches()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean implies(Permission permission) {
		throw new UnsupportedOperationException();
	}

	protected void initNames() {
		Set<String> names = getPropertySet("security-manager-jndi-names");

		_patterns = new ArrayList<Pattern>(names.size());

		for (String name : names) {
			Pattern pattern = Pattern.compile(name);

			_patterns.add(pattern);

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Allowing access to JNDI names that match the regular " +
						"expression " + name);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(JNDIChecker.class);

	private List<Pattern> _patterns;

}