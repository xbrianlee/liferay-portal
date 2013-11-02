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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import java.security.CodeSource;
import java.security.ProtectionDomain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;

import org.junit.runners.model.InitializationError;

/**
 * @author Raymond Augé
 */
public class PACLIntegrationJUnitTestRunner
	extends LiferayIntegrationJUnitTestRunner {

	public PACLIntegrationJUnitTestRunner(Class<?> clazz)
		throws InitializationError {

		super(_wrapTestClass(clazz));
	}

	@Override
	public void initApplicationContext() {
		if (_initialized) {
			return;
		}

		Class<?> clazz = getClass();

		URL resource = clazz.getResource("pacl-test.properties");

		if (resource != null) {
			System.setProperty("external-properties", resource.getPath());
		}

		System.setProperty("catalina.base", ".");

		System.setProperty(
			Context.INITIAL_CONTEXT_FACTORY,
			"org.apache.naming.java.javaURLContextFactory");

		ServiceTestUtil.initServices();
		ServiceTestUtil.initPermissions();

		_initialized = true;
	}

	protected static final String RESOURCE_PATH =
		"com/liferay/portal/security/pacl/test/dependencies";

	private static Class<?> _wrapTestClass(Class<?> clazz)
		throws InitializationError {

		try {
			ProtectionDomain protectionDomain = clazz.getProtectionDomain();

			CodeSource codeSource = protectionDomain.getCodeSource();

			ClassLoader classLoader = new PACLClassLoader(
				new URL[] {codeSource.getLocation()}, clazz.getClassLoader());

			return Class.forName(clazz.getName(), true, classLoader);
		}
		catch (Exception e) {
			throw new InitializationError(e);
		}
	}

	private static final String _PACKAGE_PATH =
		"com.liferay.portal.security.pacl.test.";

	private static boolean _initialized = false;

	private static class PACLClassLoader extends URLClassLoader {

		public PACLClassLoader(URL[] urls, ClassLoader parentClassLoader) {
			super(urls, parentClassLoader);
		}

		@Override
		public URL getResource(String name) {
			if (name.equals(
					"com/liferay/util/bean/PortletBeanLocatorUtil.class")) {

				URL url = findResource("/");

				String path = url.getPath();

				path = path.substring(
					0, path.length() - RESOURCE_PATH.length() - 1);

				path = path.concat(name);

				try {
					return new URL("file", null, path);
				}
				catch (MalformedURLException murle) {
				}
			}

			URL url = findResource(name);

			if (url != null) {
				return url;
			}

			return super.getResource(name);
		}

		@Override
		public URL findResource(String name) {
			if (_urls.containsKey(name)) {
				return _urls.get(name);
			}

			URL resource = null;

			if (!name.contains(RESOURCE_PATH)) {
				String newName = name;

				if (!newName.startsWith(StringPool.SLASH)) {
					newName = StringPool.SLASH.concat(newName);
				}

				newName = RESOURCE_PATH.concat(newName);

				resource = super.findResource(newName);
			}

			if ((resource == null) && !name.contains(RESOURCE_PATH)) {
				String newName = name;

				if (!newName.startsWith(StringPool.SLASH)) {
					newName = StringPool.SLASH.concat(newName);
				}

				newName = RESOURCE_PATH.concat("/WEB-INF/classes").concat(
					newName);

				resource = super.findResource(newName);
			}

			if (resource == null) {
				resource = super.findResource(name);
			}

			if (resource != null) {
				_urls.put(name, resource);
			}

			return resource;
		}

		@Override
		public Class<?> loadClass(String name) throws ClassNotFoundException {
			if (name.startsWith(_PACKAGE_PATH)) {
				if (_classes.containsKey(name)) {
					return _classes.get(name);
				}

				Class<?> clazz = super.findClass(name);

				_classes.put(name, clazz);

				return clazz;
			}

			return super.loadClass(name);
		}

		@Override
		protected synchronized Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {

			if (name.startsWith(_PACKAGE_PATH)) {
				if (_classes.containsKey(name)) {
					return _classes.get(name);
				}

				Class<?> clazz = super.findClass(name);

				_classes.put(name, clazz);

				return clazz;
			}

			return super.loadClass(name, resolve);
		}

		private Map<String, Class<?>> _classes =
			new ConcurrentHashMap<String, Class<?>>();
		private Map<String, URL> _urls = new ConcurrentHashMap<String, URL>();

	}

}