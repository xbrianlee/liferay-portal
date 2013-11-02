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
import com.liferay.portal.kernel.util.StreamUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Michael C. Han
 */
public abstract class BaseInputStreamAntivirusScanner
	implements AntivirusScanner {

	@Override
	public boolean isActive() {
		return _ACTIVE;
	}

	@Override
	public void scan(File file)
		throws AntivirusScannerException, SystemException {

		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream(file);

			scan(inputStream);
		}
		catch (FileNotFoundException fnfe) {
			throw new SystemException("Unable to scan file", fnfe);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	private static final boolean _ACTIVE = true;

}