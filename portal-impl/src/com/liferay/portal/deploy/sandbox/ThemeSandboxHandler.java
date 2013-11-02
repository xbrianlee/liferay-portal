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

package com.liferay.portal.deploy.sandbox;

import com.liferay.portal.kernel.deploy.Deployer;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.ant.CopyTask;

import java.io.File;

/**
 * @author Igor Spasic
 * @author Brian Wing Shun Chan
 */
public class ThemeSandboxHandler extends BaseSandboxHandler {

	public ThemeSandboxHandler(Deployer deployer) {
		super(deployer);
	}

	@Override
	protected void clonePlugin(File dir, PluginPackage pluginPackage)
		throws Exception {

		String portalWebDir = PortalUtil.getPortalWebDir();

		CopyTask.copyDirectory(
			new File(portalWebDir, "html/themes/classic"), dir, null,
			"/_diffs/**", true, true);
	}

	@Override
	protected String getPluginType() {
		return _PLUGIN_TYPE;
	}

	private static final String _PLUGIN_TYPE = "theme";

}