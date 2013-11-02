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

package com.liferay.osgi.bootstrap;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.module.framework.ModuleFramework;

import java.io.InputStream;

import java.net.URL;

import java.util.List;
import java.util.Map;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class ModuleFrameworkUtil {

	public static Object addBundle(String location) throws PortalException {
		return getModuleFramework().addBundle(location);
	}

	public static Object addBundle(String location, InputStream inputStream)
		throws PortalException {

		return getModuleFramework().addBundle(location, inputStream);
	}

	public static Map<String, List<URL>> getExtraPackageMap() {
		return getModuleFramework().getExtraPackageMap();
	}

	public static List<URL> getExtraPackageURLs() {
		return getModuleFramework().getExtraPackageURLs();
	}

	public static Object getFramework() {
		return getModuleFramework().getFramework();
	}

	public static ModuleFramework getModuleFramework() {
		if (_moduleFramework == null) {

			// This class cannot be injected by Spring since it is used before
			// Spring is initialized. However, this class can be injected for
			// testing purposes.

			_moduleFramework = new ModuleFrameworkImpl();
		}

		return _moduleFramework;
	}

	public static String getState(long bundleId) throws PortalException {
		return getModuleFramework().getState(bundleId);
	}

	public static void registerContext(Object context) {
		getModuleFramework().registerContext(context);
	}

	public static void setBundleStartLevel(long bundleId, int startLevel)
		throws PortalException {

		getModuleFramework().setBundleStartLevel(bundleId, startLevel);
	}

	public static void setModuleFramework(ModuleFramework moduleFramework) {
		_moduleFramework = moduleFramework;
	}

	public static void startBundle(long bundleId) throws PortalException {
		getModuleFramework().startBundle(bundleId);
	}

	public static void startBundle(long bundleId, int options)
		throws PortalException {

		getModuleFramework().startBundle(bundleId, options);
	}

	public static void startFramework() throws Exception {
		getModuleFramework().startFramework();
	}

	public static void startRuntime() throws Exception {
		getModuleFramework().startRuntime();
	}

	public static void stopBundle(long bundleId) throws PortalException {
		getModuleFramework().stopBundle(bundleId);
	}

	public static void stopBundle(long bundleId, int options)
		throws PortalException {

		getModuleFramework().stopBundle(bundleId, options);
	}

	public static void stopFramework() throws Exception {
		getModuleFramework().stopFramework();
	}

	public static void stopRuntime() throws Exception {
		getModuleFramework().stopRuntime();
	}

	public static void uninstallBundle(long bundleId) throws PortalException {
		getModuleFramework().uninstallBundle(bundleId);
	}

	public static void updateBundle(long bundleId) throws PortalException {
		getModuleFramework().updateBundle(bundleId);
	}

	public static void updateBundle(long bundleId, InputStream inputStream)
		throws PortalException {

		getModuleFramework().updateBundle(bundleId, inputStream);
	}

	private static ModuleFramework _moduleFramework;

}