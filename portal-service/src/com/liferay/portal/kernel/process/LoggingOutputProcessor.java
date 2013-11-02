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

package com.liferay.portal.kernel.process;

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Shuyang Zhou
 */
public class LoggingOutputProcessor implements OutputProcessor<Void, Void> {

	@Override
	public Void processStdErr(InputStream stdErrInputStream)
		throws ProcessException {

		_processOut(true, stdErrInputStream);

		return null;
	}

	@Override
	public Void processStdOut(InputStream stdOutInputStream)
		throws ProcessException {

		_processOut(false, stdOutInputStream);

		return null;
	}

	private void _processOut(boolean stdErr, InputStream inputStream)
		throws ProcessException {

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new InputStreamReader(inputStream));

		String line = null;

		try {
			while ((line = unsyncBufferedReader.readLine()) != null) {
				if (stdErr && _log.isErrorEnabled()) {
					_log.error(line);
				}
				else if (!stdErr && _log.isInfoEnabled()) {
					_log.info(line);
				}
			}
		}
		catch (IOException ioe) {
			throw new ProcessException(ioe);
		}
		finally {
			try {
				unsyncBufferedReader.close();
			}
			catch (IOException ioe) {
				throw new ProcessException(ioe);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LoggingOutputProcessor.class);

}