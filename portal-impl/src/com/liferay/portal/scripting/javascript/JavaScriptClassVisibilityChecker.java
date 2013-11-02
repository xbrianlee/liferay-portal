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

package com.liferay.portal.scripting.javascript;

import com.liferay.portal.scripting.ClassVisibilityChecker;

import java.util.Set;

import org.mozilla.javascript.ClassShutter;

/**
 * @author Alberto Montero
 */
public class JavaScriptClassVisibilityChecker
	extends ClassVisibilityChecker implements ClassShutter {

	public JavaScriptClassVisibilityChecker(Set<String> allowedClasses) {
		super(allowedClasses);
	}

	@Override
	public boolean visibleToScripts(String className) {
		return isVisible(className);
	}

}