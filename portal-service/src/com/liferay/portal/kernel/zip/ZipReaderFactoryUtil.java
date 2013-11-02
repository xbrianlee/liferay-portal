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

package com.liferay.portal.kernel.zip;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Raymond Augé
 */
public class ZipReaderFactoryUtil {

	public static ZipReader getZipReader(File file) {
		return getZipReaderFactory().getZipReader(file);
	}

	public static ZipReader getZipReader(InputStream inputStream)
		throws IOException {

		return getZipReaderFactory().getZipReader(inputStream);
	}

	public static ZipReaderFactory getZipReaderFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			ZipReaderFactoryUtil.class);

		return _zipReaderFactory;
	}

	public void setZipReaderFactory(ZipReaderFactory zipReaderFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_zipReaderFactory = zipReaderFactory;
	}

	private static ZipReaderFactory _zipReaderFactory;

}