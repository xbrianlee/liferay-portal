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

package com.liferay.portlet.dynamicdatalists.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class DDLExporterFactory {

	public static DDLExporter getDDLExporter(DDLExportFormat exportFormat)
		throws PortalException {

		DDLExporter exporter = _exporters.get(exportFormat);

		if (exporter == null) {
			throw new PortalException("Invalid format type " + exportFormat);
		}

		return exporter;
	}

	public void setDDLExporters(Map<String, DDLExporter> exporters) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_exporters = new HashMap<DDLExportFormat, DDLExporter>();

		for (Map.Entry<String, DDLExporter> entry : exporters.entrySet()) {
			DDLExportFormat exportFormat = DDLExportFormat.parse(
				entry.getKey());

			_exporters.put(exportFormat, entry.getValue());
		}
	}

	private static Map<DDLExportFormat, DDLExporter> _exporters;

}