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

package com.liferay.portal.template;

import java.security.AccessControlContext;

/**
 * @author Raymond Augé
 */
public class TemplateControlContext {

	public TemplateControlContext(
		AccessControlContext accessControlContext, ClassLoader classLoader) {

		_accessControlContext = accessControlContext;
		_classLoader = classLoader;
	}

	public AccessControlContext getAccessControlContext() {
		return _accessControlContext;
	}

	public ClassLoader getClassLoader() {
		return _classLoader;
	}

	private AccessControlContext _accessControlContext;
	private ClassLoader _classLoader;

}