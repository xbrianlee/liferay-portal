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

import java.io.InputStream;

import java.net.URL;

import java.util.List;
import java.util.Map;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public interface ModuleFramework {

	public Object addBundle(String location) throws PortalException;

	public Object addBundle(String location, InputStream inputStream)
		throws PortalException;

	public Map<String, List<URL>> getExtraPackageMap();

	public List<URL> getExtraPackageURLs();

	public Object getFramework();

	public String getState(long bundleId) throws PortalException;

	public void registerContext(Object context);

	public void setBundleStartLevel(long bundleId, int startLevel)
		throws PortalException;

	public void startBundle(long bundleId) throws PortalException;

	public void startBundle(long bundleId, int options) throws PortalException;

	public void startFramework() throws Exception;

	public void startRuntime() throws Exception;

	public void stopBundle(long bundleId) throws PortalException;

	public void stopBundle(long bundleId, int options) throws PortalException;

	public void stopFramework() throws Exception;

	public void stopRuntime() throws Exception;

	public void uninstallBundle(long bundleId) throws PortalException;

	public void updateBundle(long bundleId) throws PortalException;

	public void updateBundle(long bundleId, InputStream inputStream)
		throws PortalException;

}