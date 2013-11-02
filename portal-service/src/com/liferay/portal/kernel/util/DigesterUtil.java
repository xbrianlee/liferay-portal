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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.InputStream;

import java.nio.ByteBuffer;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Connor McKay
 */
public class DigesterUtil {

	public static String digest(ByteBuffer byteBuffer) {
		return getDigester().digest(byteBuffer);
	}

	public static String digest(InputStream inputStream) {
		return getDigester().digest(inputStream);
	}

	public static String digest(String text) {
		return getDigester().digest(text);
	}

	public static String digest(String algorithm, ByteBuffer byteBuffer) {
		return getDigester().digest(algorithm, byteBuffer);
	}

	public static String digest(String algorithm, InputStream inputStream) {
		return getDigester().digest(algorithm, inputStream);
	}

	public static String digest(String algorithm, String... text) {
		return getDigester().digest(algorithm, text);
	}

	public static String digestBase64(ByteBuffer byteBuffer) {
		return getDigester().digestBase64(byteBuffer);
	}

	public static String digestBase64(InputStream inputStream) {
		return getDigester().digestBase64(inputStream);
	}

	public static String digestBase64(String text) {
		return getDigester().digestBase64(text);
	}

	public static String digestBase64(String algorithm, ByteBuffer byteBuffer) {
		return getDigester().digestBase64(algorithm, byteBuffer);
	}

	public static String digestBase64(
		String algorithm, InputStream inputStream) {

		return getDigester().digestBase64(algorithm, inputStream);
	}

	public static String digestBase64(String algorithm, String... text) {
		return getDigester().digestBase64(algorithm, text);
	}

	public static String digestHex(ByteBuffer byteBuffer) {
		return getDigester().digestHex(byteBuffer);
	}

	public static String digestHex(InputStream inputStream) {
		return getDigester().digestHex(inputStream);
	}

	public static String digestHex(String text) {
		return getDigester().digestHex(text);
	}

	public static String digestHex(String algorithm, ByteBuffer byteBuffer) {
		return getDigester().digestHex(algorithm, byteBuffer);
	}

	public static String digestHex(String algorithm, InputStream inputStream) {
		return getDigester().digestHex(algorithm, inputStream);
	}

	public static String digestHex(String algorithm, String... text) {
		return getDigester().digestHex(algorithm, text);
	}

	public static byte[] digestRaw(ByteBuffer byteBuffer) {
		return getDigester().digestRaw(byteBuffer);
	}

	public static byte[] digestRaw(String text) {
		return getDigester().digestRaw(text);
	}

	public static byte[] digestRaw(String algorithm, ByteBuffer byteBuffer) {
		return getDigester().digestRaw(algorithm, byteBuffer);
	}

	public static byte[] digestRaw(String algorithm, InputStream inputStream) {
		return getDigester().digestRaw(algorithm, inputStream);
	}

	public static byte[] digestRaw(String algorithm, String... text) {
		return getDigester().digestRaw(algorithm, text);
	}

	public static Digester getDigester() {
		PortalRuntimePermission.checkGetBeanProperty(DigesterUtil.class);

		return _digester;
	}

	public void setDigester(Digester digester) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_digester = digester;
	}

	private static Digester _digester;

}