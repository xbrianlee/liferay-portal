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

/**
 * @author Brian Wing Shun Chan
 */
public class ThreadSafeAutoDeployer implements AutoDeployer {

	public ThreadSafeAutoDeployer(AutoDeployer autoDeployer) {
		_autoDeployer = autoDeployer;
	}

	@Override
	public int autoDeploy(AutoDeploymentContext autoDeploymentContext)
		throws AutoDeployException {

		AutoDeployer cloneAutoDeployer = _autoDeployer.cloneAutoDeployer();

		return cloneAutoDeployer.autoDeploy(autoDeploymentContext);
	}

	@Override
	public AutoDeployer cloneAutoDeployer() {
		throw new UnsupportedOperationException();
	}

	private AutoDeployer _autoDeployer;

}