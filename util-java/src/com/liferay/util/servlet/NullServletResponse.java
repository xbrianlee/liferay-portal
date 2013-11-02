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

import com.liferay.portal.kernel.util.UnsyncPrintWriterPool;

import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author Brian Wing Shun Chan
 */
public class NullServletResponse extends HttpServletResponseWrapper {

	public NullServletResponse(HttpServletResponse response) {
		super(response);

		_servletOutputStream = new NullServletOutputStream();
		_printWriter = UnsyncPrintWriterPool.borrow(
			_servletOutputStream, getCharacterEncoding());
	}

	@Override
	public ServletOutputStream getOutputStream() {
		return _servletOutputStream;
	}

	@Override
	public PrintWriter getWriter() {
		return _printWriter;
	}

	/*public void sendError(int status) throws IOException {
	}

	public void sendError(int status, String msg) throws IOException {
	}

	public void sendRedirect(String location) throws IOException {
	}*/

	private PrintWriter _printWriter;
	private ServletOutputStream _servletOutputStream;

}