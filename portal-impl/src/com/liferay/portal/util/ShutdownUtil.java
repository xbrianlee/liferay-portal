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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class ShutdownUtil {

	public static void cancel() {
		_instance._cancel();
	}

	public static long getInProcess() {
		return _instance._getInProcess();
	}

	public static String getMessage() {
		return _instance._getMessage();
	}

	public static boolean isInProcess() {
		return _instance._isInProcess();
	}

	public static boolean isShutdown() {
		return _instance._isShutdown();
	}

	public static void shutdown(long milliseconds) {
		shutdown(milliseconds, StringPool.BLANK);
	}

	public static void shutdown(long milliseconds, String message) {
		_instance._shutdown(milliseconds, message);
	}

	private ShutdownUtil() {
	}

	private void _cancel() {
		_date = null;
		_message = null;
	}

	private long _getInProcess() {
		long milliseconds = 0;

		if (_date != null) {
			milliseconds = _date.getTime() - System.currentTimeMillis();
		}

		return milliseconds;
	}

	private String _getMessage() {
		return _message;
	}

	private boolean _isInProcess() {
		if (_date == null) {
			return false;
		}

		if (_date.after(new Date())) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean _isShutdown() {
		if (_date == null) {
			return false;
		}

		if (_date.before(new Date())) {
			return true;
		}
		else {
			return false;
		}
	}

	private void _shutdown(long milliseconds, String message) {
		_date = new Date(System.currentTimeMillis() + milliseconds);
		_message = message;
	}

	private static ShutdownUtil _instance = new ShutdownUtil();

	private Date _date;
	private String _message;

}