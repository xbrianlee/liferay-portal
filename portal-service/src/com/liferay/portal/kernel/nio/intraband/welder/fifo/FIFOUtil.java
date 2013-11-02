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

package com.liferay.portal.kernel.nio.intraband.welder.fifo;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.File;

/**
 * @author Shuyang Zhou
 */
public class FIFOUtil {

	public static void createFIFO(File fifoFile) throws Exception {
		ProcessBuilder processBuilder = new ProcessBuilder(
			"mkfifo", fifoFile.getAbsolutePath());

		Process mkfifoProcess = null;

		try {
			mkfifoProcess = processBuilder.start();

			int result = mkfifoProcess.waitFor();

			if (result != 0) {
				throw new Exception(
					"Unable to create FIFO with command \"mkfifo\", " +
						"external process returned " + result);
			}
		}
		finally {
			if (mkfifoProcess != null) {
				mkfifoProcess.destroy();
			}
		}
	}

	public static boolean isFIFOSupported() {
		return _fifoSupported;
	}

	private static Log _log = LogFactoryUtil.getLog(FIFOUtil.class);

	private static boolean _fifoSupported;

	static {
		try {
			File tempFIFOFile = new File(
				System.getProperty("java.io.tmpdir"),
				"temp-fifo-" + System.currentTimeMillis());

			try {
				createFIFO(tempFIFOFile);
			}
			finally {
				if (!tempFIFOFile.delete()) {
					if (tempFIFOFile.exists()) {
						tempFIFOFile.deleteOnExit();
					}
				}
			}

			_fifoSupported = true;
		}
		catch (Throwable t) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to detect FIFO support", t);
			}

			_fifoSupported = false;
		}
	}

}