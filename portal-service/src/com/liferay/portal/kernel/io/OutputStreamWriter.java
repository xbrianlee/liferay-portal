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

package com.liferay.portal.kernel.io;

import com.liferay.portal.kernel.nio.charset.CharsetEncoderUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;

/**
 * @author Shuyang Zhou
 */
public class OutputStreamWriter extends Writer {

	public OutputStreamWriter(OutputStream outputStream) {
		this(outputStream, StringPool.DEFAULT_CHARSET_NAME);
	}

	public OutputStreamWriter(OutputStream outputStream, String charsetName) {
		if (charsetName == null) {
			charsetName = StringPool.DEFAULT_CHARSET_NAME;
		}

		_outputStream = outputStream;
		_charsetName = charsetName;
		_charsetEncoder = CharsetEncoderUtil.getCharsetEncoder(charsetName);
	}

	@Override
	public void close() throws IOException {
		_outputStream.close();
	}

	@Override
	public void flush() throws IOException {
		_outputStream.flush();
	}

	public String getEncoding() {
		return _charsetName;
	}

	@Override
	public void write(char[] chars, int offset, int length) throws IOException {
		ByteBuffer byteBuffer = _charsetEncoder.encode(
			CharBuffer.wrap(chars, offset, length));

		_outputStream.write(byteBuffer.array(), 0, byteBuffer.limit());
	}

	@Override
	public void write(int c) throws IOException {
		ByteBuffer byteBuffer = _charsetEncoder.encode(
			CharBuffer.wrap(new char[] {(char)c}));

		_outputStream.write(byteBuffer.array(), 0, byteBuffer.limit());
	}

	@Override
	public void write(String string, int offset, int length)
		throws IOException {

		ByteBuffer byteBuffer = _charsetEncoder.encode(
			CharBuffer.wrap(string, offset, offset + length));

		_outputStream.write(byteBuffer.array(), 0, byteBuffer.limit());
	}

	private CharsetEncoder _charsetEncoder;
	private String _charsetName;
	private OutputStream _outputStream;

}