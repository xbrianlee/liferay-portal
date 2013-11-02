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

package com.liferay.portal.module.framework;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.util.ClassLoaderUtil;

import java.io.InputStream;

import java.net.URL;

import java.util.List;
import java.util.Map;

/**
 * This class is a simple wrapper in order to make the framework module running
 * under its own class loader.
 *
 * @author Miguel Pastor
 * @author Raymond Augé
 * @see    ModuleFrameworkClassLoader
 */
public class ModuleFrameworkUtilAdapter {

	public static Object addBundle(String location) throws PortalException {
		return _moduleFramework.addBundle(location);
	}

	public static Object addBundle(String location, InputStream inputStream)
		throws PortalException {

		return _moduleFramework.addBundle(location, inputStream);
	}

	public static Map<String, List<URL>> getExtraPackageMap() {
		return _moduleFramework.getExtraPackageMap();
	}

	public static Object getFramework() {
		return _moduleFramework.getFramework();
	}

	public static String getState(long bundleId) throws PortalException {
		return _moduleFramework.getState(bundleId);
	}

	public static void registerContext(Object context) {
		_moduleFramework.registerContext(context);
	}

	public static void setBundleStartLevel(long bundleId, int startLevel)
		throws PortalException {

		_moduleFramework.setBundleStartLevel(bundleId, startLevel);
	}

	public static void setModuleFramework(ModuleFramework moduleFramework) {
		_moduleFramework = moduleFramework;

		_moduleFrameworkAdapterHelper.exec(
			"setModuleFramework", new Class[] {ModuleFramework.class},
			_moduleFramework);
	}

	public static void startBundle(long bundleId) throws PortalException {
		_moduleFramework.startBundle(bundleId);
	}

	public static void startBundle(long bundleId, int options)
		throws PortalException {

		_moduleFramework.startBundle(bundleId, options);
	}

	public static void startFramework() throws Exception {
		ClassLoader classLoader = ClassLoaderUtil.getContextClassLoader();

		ClassLoaderUtil.setContextClassLoader(
			ModuleFrameworkAdapterHelper.getClassLoader());

		try {
			_moduleFramework.startFramework();
		}
		finally {
			ClassLoaderUtil.setContextClassLoader(classLoader);
		}
	}

	public static void startRuntime() throws Exception {
		_moduleFramework.startRuntime();
	}

	public static void stopBundle(long bundleId) throws PortalException {
		_moduleFramework.stopBundle(bundleId);
	}

	public static void stopBundle(long bundleId, int options)
		throws PortalException {

		_moduleFramework.stopBundle(bundleId, options);
	}

	public static void stopFramework() throws Exception {
		_moduleFramework.stopFramework();
	}

	public static void stopRuntime() throws Exception {
		_moduleFramework.stopRuntime();
	}

	public static void uninstallBundle(long bundleId) throws PortalException {
		_moduleFramework.uninstallBundle(bundleId);
	}

	public static void updateBundle(long bundleId) throws PortalException {
		_moduleFramework.updateBundle(bundleId);
	}

	public static void updateBundle(long bundleId, InputStream inputStream)
		throws PortalException {

		_moduleFramework.updateBundle(bundleId, inputStream);
	}

	private static ModuleFramework _moduleFramework;
	private static ModuleFrameworkAdapterHelper _moduleFrameworkAdapterHelper =
		new ModuleFrameworkAdapterHelper(
			"com.liferay.osgi.bootstrap.ModuleFrameworkUtil");

	static {
		_moduleFramework =
			(ModuleFramework)_moduleFrameworkAdapterHelper.execute(
				"getModuleFramework");
	}

}