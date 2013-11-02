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

package com.liferay.portal.kernel.deploy;

import com.liferay.portal.kernel.plugin.PluginPackage;

import java.io.File;

import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public interface Deployer {

	public void addExtJar(List<String> jars, String resource)
		throws Exception;

	public void addRequiredJar(List<String> jars, String resource)
		throws Exception;

	public void checkArguments();

	public void copyDependencyXml(String fileName, String targetDir)
		throws Exception;

	public void copyDependencyXml(
			String fileName, String targetDir, Map<String, String> filterMap)
		throws Exception;

	public void copyDependencyXml(
			String fileName, String targetDir, Map<String, String> filterMap,
			boolean overwrite)
		throws Exception;

	public void copyJars(File srcFile, PluginPackage pluginPackage)
		throws Exception;

	public void copyProperties(File srcFile, PluginPackage pluginPackage)
		throws Exception;

	public void copyTlds(File srcFile, PluginPackage pluginPackage)
		throws Exception;

	public void copyXmls(
			File srcFile, String displayName, PluginPackage pluginPackage)
		throws Exception;

	public Map<String, String> processPluginPackageProperties(
			File srcFile, String displayName, PluginPackage pluginPackage)
		throws Exception;

	public PluginPackage readPluginPackage(File file);

	public void setAppServerType(String appServerType);

	public void setAuiTaglibDTD(String auiTaglibDTD);

	public void setBaseDir(String baseDir);

	public void setDestDir(String destDir);

	public void setFilePattern(String filePattern);

	public void setJars(List<String> jars);

	public void setJbossPrefix(String jbossPrefix);

	public void setPortletExtTaglibDTD(String portletExtTaglibDTD);

	public void setPortletTaglibDTD(String portletTaglibDTD);

	public void setSecurityTaglibDTD(String securityTaglibDTD);

	public void setThemeTaglibDTD(String themeTaglibDTD);

	public void setTomcatLibDir(String tomcatLibDir);

	public void setUiTaglibDTD(String uiTaglibDTD);

	public void setUnpackWar(boolean unpackWar);

	public void setUtilTaglibDTD(String utilTaglibDTD);

	public void setWars(List<String> wars);

	public void updateWebXml(
			File webXml, File srcFile, String displayName,
			PluginPackage pluginPackage)
		throws Exception;

}