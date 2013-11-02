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

package com.liferay.portal.kernel.process.log;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

/**
 * @author Shuyang Zhou
 */
public class LoggingProcessCallable implements ProcessCallable<String> {

	public LoggingProcessCallable(byte[] bytes) {
		this(bytes, false);
	}

	public LoggingProcessCallable(byte[] bytes, boolean error) {
		_bytes = bytes;
		_error = error;
	}

	@Override
	public String call() {
		try {
			if (_error) {
				System.err.write(_bytes);
			}
			else {
				System.out.write(_bytes);
			}
		}
		catch (IOException ioe) {
			_log.error(
				"Unable to output log message: " + new String(_bytes), ioe);
		}

		return StringPool.BLANK;
	}

	private static final long serialVersionUID = 1L;

	private static Log _log = LogFactoryUtil.getLog(
		LoggingProcessCallable.class);

	private final byte[] _bytes;
	private final boolean _error;

}