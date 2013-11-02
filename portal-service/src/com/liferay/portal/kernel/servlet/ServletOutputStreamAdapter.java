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

package com.liferay.portal.kernel.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

/**
 * @author Shuyang Zhou
 */
public class ServletOutputStreamAdapter extends ServletOutputStream {

	public ServletOutputStreamAdapter(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void close() throws IOException {
		try {
			flush();
		}
		catch (IOException ioe) {
		}

		outputStream.close();
	}

	@Override
	public void flush() throws IOException {
		outputStream.flush();
	}

	@Override
	public void write(byte[] bytes) throws IOException {
		outputStream.write(bytes, 0, bytes.length);
	}

	@Override
	public void write(byte[] bytes, int offset, int length) throws IOException {
		outputStream.write(bytes, offset, length);
	}

	@Override
	public void write(int b) throws IOException {
		outputStream.write(b);
	}

	protected OutputStream outputStream;

}