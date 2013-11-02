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
import java.io.InputStream;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-6648.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class UnsyncFilterInputStream extends InputStream {

	public UnsyncFilterInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public int available() throws IOException {
		return inputStream.available();
	}

	@Override
	public void close() throws IOException {
		inputStream.close();
	}

	@Override
	public void mark(int readLimit) {
		inputStream.mark(readLimit);
	}

	@Override
	public boolean markSupported() {
		return inputStream.markSupported();
	}

	@Override
	public int read() throws IOException {
		return inputStream.read();
	}

	@Override
	public int read(byte[] bytes) throws IOException {
		return inputStream.read(bytes);
	}

	@Override
	public int read(byte[] bytes, int offset, int length) throws IOException {
		return inputStream.read(bytes, offset, length);
	}

	@Override
	public void reset() throws IOException {
		inputStream.reset();
	}

	@Override
	public long skip(long skip) throws IOException {
		return inputStream.skip(skip);
	}

	protected InputStream inputStream;

}