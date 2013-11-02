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

import java.io.Serializable;

import java.util.Map;

/**
 * @author Michael C. Han
 * @author Gavin Wan
 */
public class ReportRequest implements Serializable {

	public ReportRequest(
		ReportRequestContext reportRequestContext,
		ReportDesignRetriever reportDesignRetriever,
		Map<String, String> reportParameters, String reportFormat) {

		_reportRequestContext = reportRequestContext;
		_reportDesignRetriever = reportDesignRetriever;
		_reportParameters = reportParameters;
		_reportFormat = ReportFormat.parse(reportFormat);
	}

	public ReportDesignRetriever getReportDesignRetriever() {
		return _reportDesignRetriever;
	}

	public ReportFormat getReportFormat() {
		return _reportFormat;
	}

	public Map<String, String> getReportParameters() {
		return _reportParameters;
	}

	public ReportRequestContext getReportRequestContext() {
		return _reportRequestContext;
	}

	public void setReportDesignRetriever(
		ReportDesignRetriever reportDesignRetriever) {

		_reportDesignRetriever = reportDesignRetriever;
	}

	public void setReportFormat(ReportFormat reportFormat) {
		_reportFormat = reportFormat;
	}

	public void setReportParameters(Map<String, String> reportParameters) {
		_reportParameters.putAll(reportParameters);
	}

	public void setReportRequestContext(
		ReportRequestContext reportRequestContext) {

		_reportRequestContext = reportRequestContext;
	}

	private ReportDesignRetriever _reportDesignRetriever;
	private ReportFormat _reportFormat;
	private Map<String, String> _reportParameters;
	private ReportRequestContext _reportRequestContext;

}