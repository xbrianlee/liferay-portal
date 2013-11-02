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

package com.liferay.portlet.documentlibrary.antivirus;

import com.liferay.portal.kernel.exception.SystemException;

import java.io.File;
import java.io.IOException;

/**
 * @author Michael C. Han
 */
public class ClamAntivirusScannerImpl extends BaseFileAntivirusScanner {

	@Override
	public void scan(File file)
		throws AntivirusScannerException, SystemException {

		ProcessBuilder processBuilder = new ProcessBuilder(
			"clamscan", "--stdout", "--no-summary", file.getAbsolutePath());

		processBuilder.redirectErrorStream(true);

		Process process = null;

		try {
			process = processBuilder.start();

			process.waitFor();

			int exitValue = process.exitValue();

			if (exitValue == 1) {
				throw new AntivirusScannerException(
					"Virus detected in " + file.getAbsolutePath());
			}
			else if (exitValue >= 2) {
				throw new AntivirusScannerException(
					"Unable to scan file due to inability to execute " +
						"antivirus process");
			}
		}
		catch (IOException ioe) {
			throw new SystemException("Unable to scan file", ioe);
		}
		catch (InterruptedException ie) {
			throw new SystemException("Unable to scan file", ie);
		}
		finally {
			if (process != null) {
				process.destroy();
			}
		}
	}

}