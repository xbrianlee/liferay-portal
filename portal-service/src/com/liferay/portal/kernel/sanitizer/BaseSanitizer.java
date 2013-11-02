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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Map;

/**
 * @author Zsolt Balogh
 * @author Brian Wing Shun Chan
 */
public abstract class BaseSanitizer implements Sanitizer {

	@Override
	public byte[] sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String[] modes, byte[] bytes,
			Map<String, Object> options)
		throws SanitizerException {

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		sanitize(
			companyId, groupId, userId, className, classPK, contentType, modes,
			new ByteArrayInputStream(bytes), byteArrayOutputStream, options);

		return byteArrayOutputStream.toByteArray();
	}

	@Override
	public abstract void sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String[] modes,
			InputStream inputStream, OutputStream outputStream,
			Map<String, Object> options)
		throws SanitizerException;

	@Override
	public String sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String[] modes, String s,
			Map<String, Object> options)
		throws SanitizerException {

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		sanitize(
			companyId, groupId, userId, className, classPK, contentType, modes,
			new ByteArrayInputStream(s.getBytes()), byteArrayOutputStream,
			options);

		return byteArrayOutputStream.toString();
	}

}