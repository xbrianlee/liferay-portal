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
import java.io.InputStream;

/**
 * @author Michael C. Han
 */
public interface AntivirusScanner {

	public boolean isActive();

	public void scan(byte[] bytes)
		throws AntivirusScannerException, SystemException;

	public void scan(File file)
		throws AntivirusScannerException, SystemException;

	public void scan(InputStream inputStream)
		throws AntivirusScannerException, SystemException;

}