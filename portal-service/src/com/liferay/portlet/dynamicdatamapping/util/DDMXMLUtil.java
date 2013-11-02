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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.List;
import java.util.Locale;

/**
 * @author Bruno Basto
 * @author Brian Wing Shun Chan
 */
public class DDMXMLUtil {

	public static String formatXML(Document document) throws SystemException {
		return getDDMXML().formatXML(document);
	}

	public static String formatXML(String xml) throws SystemException {
		return getDDMXML().formatXML(xml);
	}

	public static DDMXML getDDMXML() {
		PortalRuntimePermission.checkGetBeanProperty(DDMXMLUtil.class);

		return _ddmXML;
	}

	public static Fields getFields(DDMStructure structure, String xml)
		throws PortalException, SystemException {

		return getDDMXML().getFields(structure, xml);
	}

	public static Fields getFields(
			DDMStructure structure, XPath xPath, String xml,
			List<String> fieldNames)
		throws PortalException, SystemException {

		return getDDMXML().getFields(structure, xPath, xml, fieldNames);
	}

	public static String getXML(Document document, Fields fields)
		throws SystemException {

		return getDDMXML().getXML(document, fields);
	}

	public static String getXML(Fields fields) throws SystemException {
		return getDDMXML().getXML(fields);
	}

	public static String updateXMLDefaultLocale(
			String xml, Locale contentDefaultLocale,
			Locale contentNewDefaultLocale)
		throws SystemException {

		return getDDMXML().updateXMLDefaultLocale(
			xml, contentDefaultLocale, contentNewDefaultLocale);
	}

	public static String validateXML(String xml) throws PortalException {
		return getDDMXML().validateXML(xml);
	}

	public void setDDMXML(DDMXML ddmXML) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ddmXML = ddmXML;
	}

	private static DDMXML _ddmXML;

}