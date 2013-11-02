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

package com.liferay.portal.kernel.bi.reporting;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;

import java.io.OutputStream;
import java.io.Serializable;

/**
 * @author Michael C. Han
 */
public class ByteArrayReportResultContainer
	implements ReportResultContainer, Serializable {

	public static final int DEFAULT_INITIAL_CAPCITY = 15360;

	public ByteArrayReportResultContainer() {
		this(null, DEFAULT_INITIAL_CAPCITY);
	}

	public ByteArrayReportResultContainer(String reportName) {
		this(reportName, DEFAULT_INITIAL_CAPCITY);
	}

	public ByteArrayReportResultContainer(
		String reportName, int initialCapacity) {

		_reportName = reportName;
		_initialCapacity = initialCapacity;
	}

	@Override
	public ReportResultContainer clone(String reportName) {
		return new ByteArrayReportResultContainer(reportName, _initialCapacity);
	}

	@Override
	public OutputStream getOutputStream() {
		if (_unsyncByteArrayOutputStream == null) {
			_unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream(
				_initialCapacity);
		}

		return _unsyncByteArrayOutputStream;
	}

	@Override
	public ReportGenerationException getReportGenerationException() {
		return _reportGenerationException;
	}

	@Override
	public String getReportName() {
		return _reportName;
	}

	@Override
	public byte[] getResults() {
		return _unsyncByteArrayOutputStream.toByteArray();
	}

	@Override
	public boolean hasError() {
		if (_reportGenerationException != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void setReportGenerationException(
		ReportGenerationException reportGenerationException) {

		_reportGenerationException = reportGenerationException;
	}

	private int _initialCapacity;
	private ReportGenerationException _reportGenerationException;
	private String _reportName;
	private UnsyncByteArrayOutputStream _unsyncByteArrayOutputStream;

}