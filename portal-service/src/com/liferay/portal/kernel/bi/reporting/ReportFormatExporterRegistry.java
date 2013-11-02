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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michael C. Han
 */
public class ReportFormatExporterRegistry {

	public ReportFormatExporter getReportFormatExporter(
		ReportFormat reportFormat) {

		ReportFormatExporter reportFormatExporter = _reportFormatExporters.get(
			reportFormat);

		if (reportFormatExporter == null) {
			throw new IllegalArgumentException(
				"No report format exporter found for " + reportFormat);
		}

		return reportFormatExporter;
	}

	public void setReportFormatExporters(
		Map<String, ReportFormatExporter> reportFormatExporters) {

		for (Map.Entry<String, ReportFormatExporter> entry :
				reportFormatExporters.entrySet()) {

			ReportFormat reportFormat = ReportFormat.parse(entry.getKey());
			ReportFormatExporter reportFormatExporter = entry.getValue();

			_reportFormatExporters.put(reportFormat, reportFormatExporter);
		}
	}

	private Map<ReportFormat, ReportFormatExporter> _reportFormatExporters =
		new ConcurrentHashMap<ReportFormat, ReportFormatExporter>();

}