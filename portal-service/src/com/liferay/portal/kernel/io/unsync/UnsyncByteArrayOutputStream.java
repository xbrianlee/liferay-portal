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

package com.liferay.portal.kernel.io.unsync;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import java.nio.ByteBuffer;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-6648.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class UnsyncByteArrayOutputStream extends OutputStream {

	public UnsyncByteArrayOutputStream() {
		this(32);
	}

	public UnsyncByteArrayOutputStream(int size) {
		buffer = new byte[size];
	}

	public void reset() {
		index = 0;
	}

	public int size() {
		return index;
	}

	public byte[] toByteArray() {
		byte[] newBuffer = new byte[index];

		System.arraycopy(buffer, 0, newBuffer, 0, index);

		return newBuffer;
	}

	@Override
	public String toString() {
		return new String(buffer, 0, index);
	}

	public String toString(String charsetName)
		throws UnsupportedEncodingException {

		return new String(buffer, 0, index, charsetName);
	}

	public byte[] unsafeGetByteArray() {
		return buffer;
	}

	public ByteBuffer unsafeGetByteBuffer() {
		return ByteBuffer.wrap(buffer, 0, index);
	}

	@Override
	public void write(byte[] bytes) {
		write(bytes, 0, bytes.length);
	}

	@Override
	public void write(byte[] bytes, int offset, int length) {
		if (length <= 0) {
			return;
		}

		int newIndex = index + length;

		if (newIndex > buffer.length) {
			int newBufferSize = Math.max(buffer.length << 1, newIndex);

			byte[] newBuffer = new byte[newBufferSize];

			System.arraycopy(buffer, 0, newBuffer, 0, index);

			buffer = newBuffer;
		}

		System.arraycopy(bytes, offset, buffer, index, length);

		index = newIndex;
	}

	@Override
	public void write(int b) {
		int newIndex = index + 1;

		if (newIndex > buffer.length) {
			int newBufferSize = Math.max(buffer.length << 1, newIndex);

			byte[] newBuffer = new byte[newBufferSize];

			System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);

			buffer = newBuffer;
		}

		buffer[index] = (byte)b;

		index = newIndex;
	}

	public void writeTo(OutputStream outputStream) throws IOException {
		outputStream.write(buffer, 0, index);
	}

	protected byte[] buffer;
	protected int index;

}