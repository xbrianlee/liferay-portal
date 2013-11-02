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

package com.liferay.portal.tools.deploy;

import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Plugin;
import com.liferay.portal.util.InitUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class ThemeDeployer extends BaseDeployer {

	public static void main(String[] args) {
		InitUtil.initWithSpring();

		List<String> wars = new ArrayList<String>();
		List<String> jars = new ArrayList<String>();

		for (String arg : args) {
			if (arg.endsWith(".war")) {
				wars.add(arg);
			}
			else if (arg.endsWith(".jar")) {
				jars.add(arg);
			}
		}

		new ThemeDeployer(wars, jars);
	}

	public ThemeDeployer() {
	}

	public ThemeDeployer(List<String> wars, List<String> jars) {
		super(wars, jars);
	}

	@Override
	public void checkArguments() {
		super.checkArguments();

		if (Validator.isNull(themeTaglibDTD)) {
			throw new IllegalArgumentException(
				"The system property deployer.theme.taglib.dtd is not set");
		}

		if (Validator.isNull(utilTaglibDTD)) {
			throw new IllegalArgumentException(
				"The system property deployer.util.taglib.dtd is not set");
		}
	}

	@Override
	public String getExtraFiltersContent(double webXmlVersion, File srcFile)
		throws Exception {

		StringBundler sb = new StringBundler(3);

		String extraFiltersContent = super.getExtraFiltersContent(
			webXmlVersion, srcFile);

		sb.append(extraFiltersContent);

		// Ignore filters

		sb.append(getIgnoreFiltersContent(srcFile));

		// Speed filters

		sb.append(getSpeedFiltersContent(srcFile));

		return sb.toString();
	}

	@Override
	public String getPluginType() {
		return Plugin.TYPE_THEME;
	}

	@Override
	public Map<String, String> processPluginPackageProperties(
			File srcFile, String displayName, PluginPackage pluginPackage)
		throws Exception {

		Map<String, String> filterMap = super.processPluginPackageProperties(
			srcFile, displayName, pluginPackage);

		if (filterMap == null) {
			return null;
		}

		String moduleArtifactId = filterMap.get("module_artifact_id");

		int pos = moduleArtifactId.indexOf("-theme");

		String themeId = moduleArtifactId.substring(0, pos);

		filterMap.put("theme_id", themeId);

		String themeName = filterMap.get("plugin_name");

		filterMap.put("theme_name", themeName);

		String liferayVersions = filterMap.get("liferay_versions");

		filterMap.put(
			"theme_versions",
			StringUtil.replace(liferayVersions, "liferay-version", "version"));

		copyDependencyXml(
			"liferay-look-and-feel.xml", srcFile + "/WEB-INF", filterMap, true);

		return filterMap;
	}

}