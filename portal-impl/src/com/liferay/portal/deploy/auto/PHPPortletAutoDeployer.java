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

package com.liferay.portal.deploy.auto;

import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.plugin.PluginPackage;

import java.io.File;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jorge Ferrer
 */
public class PHPPortletAutoDeployer extends PortletAutoDeployer {

	public PHPPortletAutoDeployer() throws AutoDeployException {
		try {
			String[] phpJars = {"resin.jar", "script-10.jar"};

			for (int i = 0; i < phpJars.length; i++) {
				String phpJar = phpJars[i];

				jars.add(downloadJar(phpJar));
			}

			addRequiredJar(jars, "portals-bridges.jar");
		}
		catch (Exception e) {
			throw new AutoDeployException(e);
		}
	}

	@Override
	public void copyXmls(
			File srcFile, String displayName, PluginPackage pluginPackage)
		throws Exception {

		super.copyXmls(srcFile, displayName, pluginPackage);

		Map<String, String> filterMap = new HashMap<String, String>();

		String pluginName = displayName;

		if (pluginPackage != null) {
			pluginName = pluginPackage.getName();
		}

		filterMap.put(
			"portlet_class", "com.liferay.util.bridges.php.PHPPortlet");
		filterMap.put("portlet_name", pluginName);
		filterMap.put("portlet_title", pluginName);
		filterMap.put("restore_current_view", "false");
		filterMap.put("friendly_url_mapper_class", "");
		filterMap.put("friendly_url_mapping", "");
		filterMap.put("friendly_url_routes", "");
		filterMap.put("init_param_name_0", "view-uri");
		filterMap.put("init_param_value_0", "/index.php");
		filterMap.put("init_param_name_1", "add-portlet-params");
		filterMap.put("init_param_value_1", "true");

		copyDependencyXml(
			"liferay-display.xml", srcFile + "/WEB-INF", filterMap);
		copyDependencyXml(
			"liferay-portlet.xml", srcFile + "/WEB-INF", filterMap);
		copyDependencyXml("portlet.xml", srcFile + "/WEB-INF", filterMap);
	}

}