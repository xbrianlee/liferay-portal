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

/**
 * @author Michael C. Han
 */
public enum ReportFormat {

	CSV("csv"), HTML("html"), PDF("pdf"), RTF("rtf"), TXT("txt"), XLS("xls"),
	XML("xml");

	public static ReportFormat parse(String value) {
		if (CSV.getValue().equals(value)) {
			return CSV;
		}
		else if (HTML.getValue().equals(value)) {
			return HTML;
		}
		else if (PDF.getValue().equals(value)) {
			return PDF;
		}
		else if (RTF.getValue().equals(value)) {
			return RTF;
		}
		else if (TXT.getValue().equals(value)) {
			return TXT;
		}
		else if (XLS.getValue().equals(value)) {
			return XLS;
		}
		else if (XML.getValue().equals(value)) {
			return XML;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private ReportFormat(String value) {
		_value = value;
	}

	private String _value;

}