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
import com.liferay.portal.kernel.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Michael C. Han
 */
public abstract class BaseFileAntivirusScanner implements AntivirusScanner {

	@Override
	public boolean isActive() {
		return _ACTIVE;
	}

	@Override
	public void scan(byte[] bytes)
		throws AntivirusScannerException, SystemException {

		File file = null;

		try {
			file = FileUtil.createTempFile(_ANTIVIRUS_EXTENSION);

			FileUtil.write(file, bytes);

			scan(file);
		}
		catch (IOException ioe) {
			throw new SystemException("Unable to write temporary file", ioe);
		}
		finally {
			if (file != null) {
				file.delete();
			}
		}
	}

	@Override
	public void scan(InputStream inputStream)
		throws AntivirusScannerException, SystemException {

		File file = null;

		try {
			file = FileUtil.createTempFile(_ANTIVIRUS_EXTENSION);

			FileUtil.write(file, inputStream);

			scan(file);
		}
		catch (IOException ioe) {
			throw new SystemException("Unable to write temporary file", ioe);
		}
		finally {
			if (file != null) {
				file.delete();
			}
		}
	}

	private static final boolean _ACTIVE = true;

	private static final String _ANTIVIRUS_EXTENSION = "avs";

}