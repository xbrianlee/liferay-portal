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
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.File;
import java.io.InputStream;

/**
 * @author Michael C. Han
 * @author Raymond Augé
 */
public class AntivirusScannerUtil {

	public static AntivirusScanner getAntivirusScanner() {
		PortalRuntimePermission.checkGetBeanProperty(
			AntivirusScannerUtil.class);

		return _antivirusScanner;
	}

	public static boolean isActive() {
		AntivirusScanner antivirusScanner = getAntivirusScanner();

		if (antivirusScanner == null) {
			return false;
		}

		return antivirusScanner.isActive();
	}

	public static void scan(byte[] bytes)
		throws AntivirusScannerException, SystemException {

		if (isActive()) {
			getAntivirusScanner().scan(bytes);
		}
	}

	public static void scan(File file)
		throws AntivirusScannerException, SystemException {

		if (isActive()) {
			getAntivirusScanner().scan(file);
		}
	}

	public static void scan(InputStream inputStream)
		throws AntivirusScannerException, SystemException {

		if (isActive()) {
			getAntivirusScanner().scan(inputStream);
		}
	}

	public void setAntivirusScanner(AntivirusScanner antiVirusScanner) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_antivirusScanner = antiVirusScanner;
	}

	private static AntivirusScanner _antivirusScanner;

}