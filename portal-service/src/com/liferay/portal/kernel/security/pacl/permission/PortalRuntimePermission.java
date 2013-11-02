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

package com.liferay.portal.kernel.security.pacl.permission;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.security.BasicPermission;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class PortalRuntimePermission extends BasicPermission {

	public static void checkDynamicQuery(Class<?> implClass) {
		_pacl.checkDynamicQuery(implClass);
	}

	public static void checkExpandoBridge(String className) {
		_pacl.checkExpandoBridge(className);
	}

	public static void checkGetBeanProperty(Class<?> clazz) {
		_checkGetBeanProperty("portal", clazz, null);
	}

	public static void checkGetBeanProperty(Class<?> clazz, String property) {
		_checkGetBeanProperty("portal", clazz, property);
	}

	public static void checkGetBeanProperty(
		String servletContextName, Class<?> clazz) {

		_checkGetBeanProperty(servletContextName, clazz, null);
	}

	public static void checkGetBeanProperty(
		String servletContextName, Class<?> clazz, String property) {

		_checkGetBeanProperty(servletContextName, clazz, property);
	}

	public static void checkGetClassLoader(String classLoaderReferenceId) {
		_pacl.checkGetClassLoader(classLoaderReferenceId);
	}

	public static void checkPortletBagPool(String portletId) {
		_pacl.checkPortletBagPool(portletId);
	}

	public static void checkSearchEngine(String searchEngineId) {
		_pacl.checkSearchEngine(searchEngineId);
	}

	public static void checkSetBeanProperty(Class<?> clazz) {
		_pacl.checkSetBeanProperty("portal", clazz, null);
	}

	public static void checkSetBeanProperty(Class<?> clazz, String property) {
		_pacl.checkSetBeanProperty("portal", clazz, property);
	}

	public static void checkSetBeanProperty(
		String servletContextName, Class<?> clazz) {

		_pacl.checkSetBeanProperty(servletContextName, clazz, null);
	}

	public static void checkSetBeanProperty(
		String servletContextName, Class<?> clazz, String property) {

		_pacl.checkSetBeanProperty(servletContextName, clazz, property);
	}

	public static void checkThreadPoolExecutor(String name) {
		_pacl.checkThreadPoolExecutor(name);
	}

	public PortalRuntimePermission(String name, String property) {
		super(name);

		_property = property;

		_init();
	}

	public PortalRuntimePermission(
		String name, String servletContextName, String subject) {

		this(name, servletContextName, subject, null);
	}

	public PortalRuntimePermission(
		String name, String servletContextName, String subject,
		String property) {

		super(_createLongName(name, servletContextName, subject));

		_property = property;

		_init();
	}

	@Override
	public String getActions() {
		return _property;
	}

	public String getProperty() {
		return _property;
	}

	public String getServletContextName() {
		return _servletContextName;
	}

	public String getShortName() {
		return _shortName;
	}

	public String getSubject() {
		return _subject;
	}

	public static interface PACL {

		public void checkDynamicQuery(Class<?> implClass);

		public void checkExpandoBridge(String className);

		public void checkGetBeanProperty(
			String servletContextName, Class<?> clazz, String property);

		public void checkGetClassLoader(String classLoaderReferenceId);

		public void checkPortletBagPool(String portletId);

		public void checkSearchEngine(String searchEngineId);

		public void checkSetBeanProperty(
			String servletContextName, Class<?> clazz, String property);

		public void checkThreadPoolExecutor(String name);

	}

	/**
	 * This method ensures the calls stack is the proper length.
	 */
	private static void _checkGetBeanProperty(
		String servletContextName, Class<?> clazz, String property) {

		_pacl.checkGetBeanProperty(servletContextName, clazz, property);
	}

	private static String _createLongName(
		String name, String servletContextName, String subject) {

		StringBundler sb = new StringBundler(5);

		sb.append(name);
		sb.append(StringPool.POUND);

		if (Validator.isNull(servletContextName)) {
			sb.append("portal");
		}
		else {
			sb.append(servletContextName);
		}

		sb.append(StringPool.POUND);
		sb.append(subject);

		return sb.toString();
	}

	private void _init() {
		String[] nameParts = StringUtil.split(getName(), StringPool.POUND);

		if (nameParts.length != 3) {
			throw new IllegalArgumentException(
				"Name " + getName() + " does not follow the format " +
					"[name]#[servletContextName]#[subject]");
		}

		_shortName = nameParts[0];
		_servletContextName = nameParts[1];
		_subject = nameParts[2];
	}

	private static PACL _pacl = new NoPACL();

	private String _property;
	private String _servletContextName;
	private String _shortName;
	private String _subject;

	private static class NoPACL implements PACL {

		@Override
		public void checkDynamicQuery(Class<?> implClass) {
		}

		@Override
		public void checkExpandoBridge(String className) {
		}

		@Override
		public void checkGetBeanProperty(
			String servletContextName, Class<?> clazz, String property) {
		}

		@Override
		public void checkGetClassLoader(String classLoaderReferenceId) {
		}

		@Override
		public void checkPortletBagPool(String portletId) {
		}

		@Override
		public void checkSearchEngine(String searchEngineId) {
		}

		@Override
		public void checkSetBeanProperty(
			String servletContextName, Class<?> clazz, String property) {
		}

		@Override
		public void checkThreadPoolExecutor(String name) {
		}

	}

}