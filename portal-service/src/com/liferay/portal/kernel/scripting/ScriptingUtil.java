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

package com.liferay.portal.kernel.scripting;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.ClassLoaderPool;

import java.util.Map;
import java.util.Set;

/**
 * @author Alberto Montero
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ScriptingUtil {

	public static void clearCache(String language) throws ScriptingException {
		getScripting().clearCache(language);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #eval(Set, Map, Set, String,
	 *             String, String...)}
	 */
	public static Map<String, Object> eval(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			Set<String> outputNames, String language, String script,
			ClassLoader... classLoaders)
		throws ScriptingException {

		return getScripting().eval(
			allowedClasses, inputObjects, outputNames, language, script,
			_getServletContextNames(classLoaders));
	}

	public static Map<String, Object> eval(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			Set<String> outputNames, String language, String script,
			String... servletContextNames)
		throws ScriptingException {

		return getScripting().eval(
			allowedClasses, inputObjects, outputNames, language, script,
			servletContextNames);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #exec(Set, Map, String,
	 *             String, String...)}
	 */
	public static void exec(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			String language, String script, ClassLoader... classLoaders)
		throws ScriptingException {

		getScripting().exec(
			allowedClasses, inputObjects, language, script,
			_getServletContextNames(classLoaders));
	}

	public static void exec(
			Set<String> allowedClasses, Map<String, Object> inputObjects,
			String language, String script, String... servletContextNames)
		throws ScriptingException {

		getScripting().exec(
			allowedClasses, inputObjects, language, script,
			servletContextNames);
	}

	public static Scripting getScripting() {
		PortalRuntimePermission.checkGetBeanProperty(ScriptingUtil.class);

		return _scripting;
	}

	public static Set<String> getSupportedLanguages() {
		return getScripting().getSupportedLanguages();
	}

	public void setScripting(Scripting scripting) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_scripting = scripting;
	}

	private static String[] _getServletContextNames(
		ClassLoader[] classLoaders) {

		String[] servletContextNames = new String[classLoaders.length];

		for (int i = 0; i < classLoaders.length; i++) {
			servletContextNames[i] = ClassLoaderPool.getContextName(
				classLoaders[i]);
		}

		return servletContextNames;
	}

	private static Scripting _scripting;

}