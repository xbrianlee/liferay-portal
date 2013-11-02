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

package com.liferay.portal.kernel.deploy.auto;

import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 */
public interface AutoDeployer {

	public static final int CODE_DEFAULT = 1;

	public static final int CODE_NOT_APPLICABLE = 0;

	public static final int CODE_SKIP_NEWER_VERSION = 2;

	public int autoDeploy(AutoDeploymentContext autoDeploymentContext)
		throws AutoDeployException;

	public AutoDeployer cloneAutoDeployer() throws AutoDeployException;

}