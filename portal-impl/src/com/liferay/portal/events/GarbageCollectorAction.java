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

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.SessionAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.text.NumberFormat;

import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public class GarbageCollectorAction extends SessionAction {

	@Override
	public void run(HttpSession session) {
		Runtime runtime = Runtime.getRuntime();

		NumberFormat nf = NumberFormat.getInstance();

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Before:\t\t" +
					nf.format(runtime.freeMemory()) + "\t" +
						nf.format(runtime.totalMemory()) + "\t" +
							nf.format(runtime.maxMemory()));
		}

		System.gc();

		if (_log.isDebugEnabled()) {
			_log.debug(
				"After:\t\t" +
					nf.format(runtime.freeMemory()) + "\t" +
						nf.format(runtime.totalMemory()) + "\t" +
							nf.format(runtime.maxMemory()));
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		GarbageCollectorAction.class);

}