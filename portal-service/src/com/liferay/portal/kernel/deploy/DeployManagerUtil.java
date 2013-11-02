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

import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;
import java.util.Properties;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class DeployManagerUtil {

	public static void deploy(AutoDeploymentContext autoDeploymentContext)
		throws Exception {

		getDeployManager().deploy(autoDeploymentContext);
	}

	public static String getDeployDir() throws Exception {
		return getDeployManager().getDeployDir();
	}

	public static DeployManager getDeployManager() {
		PortalRuntimePermission.checkGetBeanProperty(DeployManagerUtil.class);

		return _deployManager;
	}

	public static String getInstalledDir() throws Exception {
		return getDeployManager().getInstalledDir();
	}

	public static PluginPackage getInstalledPluginPackage(String context) {
		return getDeployManager().getInstalledPluginPackage(context);
	}

	public static List<PluginPackage> getInstalledPluginPackages() {
		return getDeployManager().getInstalledPluginPackages();
	}

	public static List<String[]> getLevelsRequiredDeploymentContexts() {
		return getDeployManager().getLevelsRequiredDeploymentContexts();
	}

	public static List<String[]> getLevelsRequiredDeploymentWARFileNames() {
		return getDeployManager().getLevelsRequiredDeploymentWARFileNames();
	}

	public static boolean isDeployed(String context) {
		return getDeployManager().isDeployed(context);
	}

	public static boolean isRequiredDeploymentContext(String context) {
		return getDeployManager().isRequiredDeploymentContext(context);
	}

	public static PluginPackage readPluginPackageProperties(
		String displayName, Properties properties) {

		return getDeployManager().readPluginPackageProperties(
			displayName, properties);
	}

	public static PluginPackage readPluginPackageXml(String xml)
		throws Exception {

		return getDeployManager().readPluginPackageXml(xml);
	}

	public static void redeploy(String context) throws Exception {
		getDeployManager().redeploy(context);
	}

	public static void reset() {
		PortalRuntimePermission.checkSetBeanProperty(DeployManagerUtil.class);

		_deployManager = null;
	}

	public static void undeploy(String context) throws Exception {
		getDeployManager().undeploy(context);
	}

	public void setDeployManager(DeployManager deployManager) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_deployManager = deployManager;
	}

	private static DeployManager _deployManager;

}