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

import com.liferay.portal.kernel.io.WriterOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.UnsyncPrintWriterPool;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.jsp.PageContext;

/**
 * @author Shuyang Zhou
 */
public class PipingServletResponse extends HttpServletResponseWrapper {

	public PipingServletResponse(
		HttpServletResponse response, OutputStream outputStream) {

		super(response);

		if (outputStream == null) {
			throw new NullPointerException("Output stream is null");
		}

		_servletOutputStream = new ServletOutputStreamAdapter(outputStream);
	}

	public PipingServletResponse(
		HttpServletResponse response, PrintWriter printWriter) {

		super(response);

		if (printWriter == null) {
			throw new NullPointerException("Print writer is null");
		}

		_printWriter = printWriter;
	}

	public PipingServletResponse(
		HttpServletResponse response, ServletOutputStream servletOutputStream) {

		super(response);

		if (servletOutputStream == null) {
			throw new NullPointerException("Servlet output stream is null");
		}

		_servletOutputStream = servletOutputStream;
	}

	public PipingServletResponse(HttpServletResponse response, Writer writer) {
		super(response);

		if (writer == null) {
			throw new NullPointerException("Writer is null");
		}

		_printWriter = UnsyncPrintWriterPool.borrow(writer);
	}

	public PipingServletResponse(PageContext pageContext) {
		this(
			(HttpServletResponse)pageContext.getResponse(),
			pageContext.getOut());
	}

	@Override
	public ServletOutputStream getOutputStream() {
		if (_servletOutputStream == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Getting an output stream when a writer is available is " +
						"not recommended because it is slow");
			}

			_servletOutputStream = new ServletOutputStreamAdapter(
				new WriterOutputStream(
					_printWriter, getCharacterEncoding(), true));
		}

		return _servletOutputStream;
	}

	@Override
	public PrintWriter getWriter() {
		if (_printWriter == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Getting a writer when an output stream is available is " +
						"not recommended because it is slow");
			}

			_printWriter = UnsyncPrintWriterPool.borrow(
				_servletOutputStream, getCharacterEncoding());
		}

		return _printWriter;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PipingServletResponse.class);

	private PrintWriter _printWriter;
	private ServletOutputStream _servletOutputStream;

}