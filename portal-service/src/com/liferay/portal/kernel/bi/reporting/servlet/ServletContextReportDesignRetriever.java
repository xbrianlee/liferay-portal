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

package com.liferay.portal.kernel.bi.reporting.servlet;

import com.liferay.portal.kernel.bi.reporting.ReportDesignRetriever;

import java.io.InputStream;

import java.util.Date;

import javax.servlet.ServletContext;

/**
 * @author Michael C. Han
 */
public class ServletContextReportDesignRetriever
	implements ReportDesignRetriever {

	public ServletContextReportDesignRetriever(
		ServletContext servletContext, String reportName, String prefix,
		String postfix) {

		_servletContext = servletContext;
		_reportName = reportName;
		_prefix = prefix;
		_postfix = postfix;
	}

	@Override
	public InputStream getInputStream() {
		return _servletContext.getResourceAsStream(
			_prefix + _reportName + _postfix);
	}

	@Override
	public Date getModifiedDate() {
		return new Date();
	}

	@Override
	public String getReportName() {
		return _reportName;
	}

	private String _postfix;
	private String _prefix;
	private String _reportName;
	private ServletContext _servletContext;

}