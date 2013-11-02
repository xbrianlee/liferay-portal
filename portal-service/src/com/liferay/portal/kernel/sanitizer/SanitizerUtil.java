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

package com.liferay.portal.kernel.sanitizer;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.InputStream;
import java.io.OutputStream;

import java.util.Map;

/**
 * @author Zsolt Balogh
 * @author Brian Wing Shun Chan
 */
public class SanitizerUtil {

	public static Sanitizer getSanitizer() {
		PortalRuntimePermission.checkGetBeanProperty(SanitizerUtil.class);

		return _sanitizer;
	}

	public static byte[] sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, byte[] bytes)
		throws SanitizerException {

		return sanitize(
			companyId, groupId, userId, className, classPK, contentType,
			Sanitizer.MODE_ALL, bytes, null);
	}

	public static void sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, InputStream inputStream,
			OutputStream outputStream)
		throws SanitizerException {

		sanitize(
			companyId, groupId, userId, className, classPK, contentType,
			Sanitizer.MODE_ALL, inputStream, outputStream, null);
	}

	public static String sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String s)
		throws SanitizerException {

		return sanitize(
			companyId, groupId, userId, className, classPK, contentType,
			Sanitizer.MODE_ALL, s, null);
	}

	public static byte[] sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String mode, byte[] bytes,
			Map<String, Object> options)
		throws SanitizerException {

		return sanitize(
			companyId, groupId, userId, className, classPK, contentType,
			new String[] {mode}, bytes, options);
	}

	public static void sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String mode,
			InputStream inputStream, OutputStream outputStream,
			Map<String, Object> options)
		throws SanitizerException {

		sanitize(
			companyId, groupId, userId, className, classPK, contentType,
			new String[] {mode}, inputStream, outputStream, options);
	}

	public static String sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String mode, String s,
			Map<String, Object> options)
		throws SanitizerException {

		return sanitize(
			companyId, groupId, userId, className, classPK, contentType,
			new String[] {mode}, s, options);
	}

	public static byte[] sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String[] modes, byte[] bytes,
			Map<String, Object> options)
		throws SanitizerException {

		return getSanitizer().sanitize(
			companyId, groupId, userId, className, classPK, contentType, modes,
			bytes, options);
	}

	public static void sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String[] modes,
			InputStream inputStream, OutputStream outputStream,
			Map<String, Object> options)
		throws SanitizerException {

		getSanitizer().sanitize(
			companyId, groupId, userId, className, classPK, contentType, modes,
			inputStream, outputStream, options);
	}

	public static String sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String[] modes, String s,
			Map<String, Object> options)
		throws SanitizerException {

		return getSanitizer().sanitize(
			companyId, groupId, userId, className, classPK, contentType, modes,
			s, options);
	}

	public void setSanitizer(Sanitizer sanitizer) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_sanitizer = sanitizer;
	}

	private static Sanitizer _sanitizer;

}