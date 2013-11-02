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
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

/**
 * @author Shuyang Zhou
 */
public class CharsetDecoderUtil {

	public static CharBuffer decode(String charsetName, byte[] bytes) {
		return decode(charsetName, ByteBuffer.wrap(bytes));
	}

	public static CharBuffer decode(
		String charsetName, byte[] bytes, int offset, int length) {

		return decode(charsetName, ByteBuffer.wrap(bytes, offset, length));
	}

	public static CharBuffer decode(String charsetName, ByteBuffer byteBuffer) {
		try {
			CharsetDecoder charsetDecoder = getCharsetDecoder(charsetName);

			return charsetDecoder.decode(byteBuffer);
		}
		catch (CharacterCodingException cce) {
			throw new Error(cce);
		}
	}

	public static CharsetDecoder getCharsetDecoder(String charsetName) {
		Charset charset = Charset.forName(charsetName);

		CharsetDecoder charsetDecoder = charset.newDecoder();

		charsetDecoder.onMalformedInput(CodingErrorAction.REPLACE);
		charsetDecoder.onUnmappableCharacter(CodingErrorAction.REPLACE);

		return charsetDecoder;
	}

}