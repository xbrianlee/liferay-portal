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

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Michael C. Han
 */
public interface ReportResultContainer {

	public ReportResultContainer clone(String reportName);

	public OutputStream getOutputStream() throws IOException;

	public ReportGenerationException getReportGenerationException();

	public String getReportName();

	public byte[] getResults();

	public boolean hasError();

	public void setReportGenerationException(
		ReportGenerationException reportGenerationException);

}