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

package com.liferay.util.servlet;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.servlet.ServletOutputStreamAdapter;
import com.liferay.portal.kernel.util.UnsyncPrintWriterPool;

import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author Brian Wing Shun Chan
 */
public class GenericServletResponse extends HttpServletResponseWrapper {

	public GenericServletResponse(HttpServletResponse response) {
		super(response);

		_ubaos = new UnsyncByteArrayOutputStream();
	}

	public int getContentLength() {
		return _contentLength;
	}

	@Override
	public String getContentType() {
		return _contentType;
	}

	public byte[] getData() {
		return _ubaos.toByteArray();
	}

	@Override
	public ServletOutputStream getOutputStream() {
		return new ServletOutputStreamAdapter(_ubaos);
	}

	@Override
	public PrintWriter getWriter() {
		return UnsyncPrintWriterPool.borrow(
			getOutputStream(), getCharacterEncoding());
	}

	@Override
	public void setContentLength(int length) {
		super.setContentLength(length);

		_contentLength = length;
	}

	@Override
	public void setContentType(String type) {
		super.setContentType(type);

		_contentType = type;
	}

	private int _contentLength;
	private String _contentType;
	private UnsyncByteArrayOutputStream _ubaos;

}