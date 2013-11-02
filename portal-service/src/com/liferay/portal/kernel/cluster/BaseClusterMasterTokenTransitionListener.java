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

package com.liferay.portal.kernel.cluster;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Michael C. Han
 */
public abstract class BaseClusterMasterTokenTransitionListener
	implements ClusterMasterTokenTransitionListener {

	@Override
	public void masterTokenAcquired() {
		try {
			doMasterTokenAcquired();
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to process the token acquired event", e);
			}
		}
	}

	@Override
	public void masterTokenReleased() {
		try {
			doMasterTokenReleased();
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to process the token released event", e);
			}
		}
	}

	protected abstract void doMasterTokenAcquired() throws Exception;

	protected abstract void doMasterTokenReleased() throws Exception;

	private static Log _log = LogFactoryUtil.getLog(
		BaseClusterMasterTokenTransitionListener.class);

}