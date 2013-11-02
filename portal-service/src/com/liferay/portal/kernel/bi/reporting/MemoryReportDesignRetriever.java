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

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;

import java.io.InputStream;

import java.util.Date;

/**
 * @author Michael C. Han
 */
public class MemoryReportDesignRetriever implements ReportDesignRetriever {

	public MemoryReportDesignRetriever(
		String reportName, Date modifiedDate, byte[] bytes) {

		_reportName = reportName;
		_modifiedDate = modifiedDate;
		_bytes = bytes;
	}

	@Override
	public InputStream getInputStream() {
		return new UnsyncByteArrayInputStream(_bytes);
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public String getReportName() {
		return _reportName;
	}

	private byte[] _bytes;
	private Date _modifiedDate;
	private String _reportName;

}