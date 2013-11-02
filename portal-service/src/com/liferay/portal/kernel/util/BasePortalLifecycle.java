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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BasePortalLifecycle implements PortalLifecycle {

	@Override
	public void portalDestroy() {
		if (!_calledPortalDestroy) {
			PortalLifecycleUtil.removeDestroy(this);

			try {
				doPortalDestroy();
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			_calledPortalDestroy = true;
		}
	}

	@Override
	public void portalInit() {
		try {
			doPortalInit();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new IllegalStateException("Unable to initialize portal", e);
		}
	}

	public void registerPortalLifecycle() {
		PortalLifecycleUtil.register(this);
	}

	public void registerPortalLifecycle(int method) {
		PortalLifecycleUtil.register(this, method);
	}

	protected abstract void doPortalDestroy() throws Exception;

	protected abstract void doPortalInit() throws Exception;

	private static Log _log = LogFactoryUtil.getLog(BasePortalLifecycle.class);

	private boolean _calledPortalDestroy;

}