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

package com.liferay.portal.kernel.util;

import java.io.File;
import java.io.InputStream;

/**
 * @author Bruno Farache
 */
public class DocumentConversionUtil {

	public static File convert(
			String id, InputStream inputStream, String sourceExtension,
			String targetExtension)
		throws Exception {

		Object returnObj = PortalClassInvoker.invoke(
			false, _convertMethodKey, id, inputStream, sourceExtension,
			targetExtension);

		if (returnObj != null) {
			return (File)returnObj;
		}
		else {
			return null;
		}
	}

	public static String[] getConversions(String extension) throws Exception {
		Object returnObj = PortalClassInvoker.invoke(
			false, _getConversionsMethodKey, extension);

		if (returnObj != null) {
			return (String[])returnObj;
		}
		else {
			return null;
		}
	}

	private static final String _CLASS_NAME =
		"com.liferay.portlet.documentlibrary.util.DocumentConversionUtil";

	private static MethodKey _convertMethodKey = new MethodKey(
		ClassResolverUtil.resolveByPortalClassLoader(_CLASS_NAME), "convert",
		String.class, InputStream.class, String.class, String.class);
	private static MethodKey _getConversionsMethodKey = new MethodKey(
		ClassResolverUtil.resolveByPortalClassLoader(_CLASS_NAME),
		"getConversions", String.class);

}