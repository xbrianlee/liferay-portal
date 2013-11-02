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

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-6648.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class UnsyncBufferedOutputStream extends UnsyncFilterOutputStream {

	public UnsyncBufferedOutputStream(OutputStream outputStream) {
		this(outputStream, _DEFAULT_BUFFER_SIZE);
	}

	public UnsyncBufferedOutputStream(OutputStream outputStream, int size) {
		super(outputStream);

		if (size <= 0) {
			throw new IllegalArgumentException("Size is less than 0");
		}

		buffer = new byte[size];
	}

	@Override
	public void flush() throws IOException {
		if (count > 0) {
			outputStream.write(buffer, 0, count);

			count = 0;
		}

		outputStream.flush();
	}

	@Override
	public void write(byte[] bytes) throws IOException {
		write(bytes, 0, bytes.length);
	}

	@Override
	public void write(byte[] bytes, int offset, int length) throws IOException {
		if (length >= buffer.length) {
			if (count > 0) {
				outputStream.write(buffer, 0, count);

				count = 0;
			}

			outputStream.write(bytes, offset, length);

			return;
		}

		if ((count > 0) && (length > (buffer.length - count))) {
			outputStream.write(buffer, 0, count);

			count = 0;
		}

		System.arraycopy(bytes, offset, buffer, count, length);

		count += length;
	}

	@Override
	public void write(int b) throws IOException {
		if (count >= buffer.length) {
			outputStream.write(buffer, 0, count);

			count = 0;
		}

		buffer[count++] = (byte)b;
	}

	protected byte[] buffer;
	protected int count;

	private static final int _DEFAULT_BUFFER_SIZE = 8192;

}