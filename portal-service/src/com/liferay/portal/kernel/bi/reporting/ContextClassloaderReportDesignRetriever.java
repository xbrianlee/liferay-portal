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

import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;

/**
 * @author Michael C. Han
 */
public class ContextClassloaderReportDesignRetriever
	implements ReportDesignRetriever, Serializable {

	public ContextClassloaderReportDesignRetriever(String reportName) {
		_reportName = reportName;
	}

	@Override
	public InputStream getInputStream() {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		return contextClassLoader.getResourceAsStream(_reportName);
	}

	@Override
	public Date getModifiedDate() {
		return new Date();
	}

	@Override
	public String getReportName() {
		return _reportName;
	}

	private String _reportName;

}