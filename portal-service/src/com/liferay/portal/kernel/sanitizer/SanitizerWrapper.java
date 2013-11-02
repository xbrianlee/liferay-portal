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

import java.io.InputStream;
import java.io.OutputStream;

import java.util.Map;

/**
 * @author Zsolt Balogh
 * @author Brian Wing Shun Chan
 */
public class SanitizerWrapper implements Sanitizer {

	public SanitizerWrapper(Sanitizer sanitizer) {
		_originalSanitizer = sanitizer;
		_sanitizer = sanitizer;
	}

	@Override
	public byte[] sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String[] modes, byte[] bytes,
			Map<String, Object> options)
		throws SanitizerException {

		return _sanitizer.sanitize(
			companyId, groupId, userId, className, classPK, contentType, modes,
			bytes, options);
	}

	@Override
	public void sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String[] modes,
			InputStream inputStream, OutputStream outputStream,
			Map<String, Object> options)
		throws SanitizerException {

		_sanitizer.sanitize(
			companyId, groupId, userId, className, classPK, contentType, modes,
			inputStream, outputStream, options);
	}

	@Override
	public String sanitize(
			long companyId, long groupId, long userId, String className,
			long classPK, String contentType, String[] modes, String s,
			Map<String, Object> options)
		throws SanitizerException {

		return _sanitizer.sanitize(
			companyId, groupId, userId, className, classPK, contentType, modes,
			s, options);
	}

	public void setSanitizer(Sanitizer sanitizer) {
		if (sanitizer == null) {
			_sanitizer = _originalSanitizer;
		}
		else {
			_sanitizer = sanitizer;
		}
	}

	private Sanitizer _originalSanitizer;
	private Sanitizer _sanitizer;

}