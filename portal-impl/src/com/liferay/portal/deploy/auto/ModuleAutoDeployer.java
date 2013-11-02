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
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.tools.deploy.BaseDeployer;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * @author Miguel Pastor
 */
public class ModuleAutoDeployer extends BaseDeployer {

	@Override
	public int deployFile(AutoDeploymentContext autoDeploymentContext)
		throws Exception {

		String destDir = PropsValues.MODULE_FRAMEWORK_AUTO_DEPLOY_DIRS[0];

		if (!FileUtil.exists(destDir)) {
			FileUtil.mkdirs(destDir);
		}

		try {
			FileUtils.copyFileToDirectory(
				autoDeploymentContext.getFile(), new File(destDir));
		}
		catch (IOException ioe) {
			throw new AutoDeployException(ioe);
		}

		return AutoDeployer.CODE_DEFAULT;
	}

}