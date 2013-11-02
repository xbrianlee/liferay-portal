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

package com.liferay.portal.backgroundtask;

import com.liferay.portal.kernel.cluster.BaseClusterMasterTokenTransitionListener;
import com.liferay.portal.service.BackgroundTaskLocalServiceUtil;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskClusterMasterTokenTransitionListener
	extends BaseClusterMasterTokenTransitionListener {

	@Override
	protected void doMasterTokenAcquired() throws Exception {
		BackgroundTaskLocalServiceUtil.cleanUpBackgroundTasks();
	}

	@Override
	protected void doMasterTokenReleased() throws Exception {
	}

}