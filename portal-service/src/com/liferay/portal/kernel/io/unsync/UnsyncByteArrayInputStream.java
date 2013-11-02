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

import java.io.InputStream;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-6648.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class UnsyncByteArrayInputStream extends InputStream {

	public UnsyncByteArrayInputStream(byte[] buffer) {
		this.buffer = buffer;
		this.index = 0;
		this.capacity = buffer.length;
	}

	public UnsyncByteArrayInputStream(byte[] buffer, int offset, int length) {
		this.buffer = buffer;
		this.index = offset;
		this.capacity = Math.min(buffer.length, offset + length);
		this.markIndex = offset;
	}

	@Override
	public int available() {
		return capacity - index;
	}

	@Override
	public void mark(int readAheadLimit) {
		markIndex = index;
	}

	@Override
	public boolean markSupported() {
		return true;
	}

	@Override
	public int read() {
		if (index < capacity) {
			return buffer[index++] & 0xff;
		}
		else {
			return -1;
		}
	}

	@Override
	public int read(byte[] bytes) {
		return read(bytes, 0, bytes.length);
	}

	@Override
	public int read(byte[] bytes, int offset, int length) {
		if (length <= 0) {
			return 0;
		}

		if (index >= capacity) {
			return -1;
		}

		int read = length;

		if ((index + read) > capacity) {
			read = capacity - index;
		}

		System.arraycopy(buffer, index, bytes, offset, read);

		index += read;

		return read;
	}

	@Override
	public void reset() {
		index = markIndex;
	}

	@Override
	public long skip(long skip) {
		if (skip < 0) {
			return 0;
		}

		if ((skip + index) > capacity) {
			skip = capacity - index;
		}

		index += skip;

		return skip;
	}

	protected byte[] buffer;
	protected int capacity;
	protected int index;
	protected int markIndex;

}