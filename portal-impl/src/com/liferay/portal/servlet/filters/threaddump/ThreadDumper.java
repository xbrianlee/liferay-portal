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

package com.liferay.portal.servlet.filters.threaddump;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ThreadUtil;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class ThreadDumper implements Runnable {

	public boolean isExecuted() {
		return _executed;
	}

	@Override
	public void run() {
		if (_log.isInfoEnabled()) {
			_log.info(ThreadUtil.threadDump());
		}

		_executed = true;
	}

	private static Log _log = LogFactoryUtil.getLog(ThreadDumper.class);

	private boolean _executed;

}