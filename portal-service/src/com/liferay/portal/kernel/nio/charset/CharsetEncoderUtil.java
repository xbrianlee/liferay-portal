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

package com.liferay.portal.kernel.nio.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;

/**
 * @author Shuyang Zhou
 */
public class CharsetEncoderUtil {

	public static ByteBuffer encode(
		String charsetName, char[] chars, int offset, int length) {

		return encode(charsetName, CharBuffer.wrap(chars, offset, length));
	}

	public static ByteBuffer encode(String charsetName, CharBuffer charBuffer) {
		try {
			CharsetEncoder charsetEncoder = getCharsetEncoder(charsetName);

			return charsetEncoder.encode(charBuffer);
		}
		catch (CharacterCodingException cce) {
			throw new Error(cce);
		}
	}

	public static ByteBuffer encode(String charsetName, String string) {
		return encode(charsetName, CharBuffer.wrap(string));
	}

	public static CharsetEncoder getCharsetEncoder(String charsetName) {
		Charset charset = Charset.forName(charsetName);

		CharsetEncoder charsetEncoder = charset.newEncoder();

		charsetEncoder.onMalformedInput(CodingErrorAction.REPLACE);
		charsetEncoder.onUnmappableCharacter(CodingErrorAction.REPLACE);

		return charsetEncoder;
	}

}