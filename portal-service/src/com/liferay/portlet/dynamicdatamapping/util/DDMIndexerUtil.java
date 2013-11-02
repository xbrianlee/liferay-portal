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

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.Locale;

/**
 * @author Alexander Chow
 */
public class DDMIndexerUtil {

	public static void addAttributes(
		Document document, DDMStructure ddmStructure, Fields fields) {

		getDDMIndexer().addAttributes(document, ddmStructure, fields);
	}

	public static String encodeName(long ddmStructureId, String fieldName) {
		return getDDMIndexer().encodeName(ddmStructureId, fieldName);
	}

	public static String encodeName(
		long ddmStructureId, String fieldName, Locale locale) {

		return getDDMIndexer().encodeName(ddmStructureId, fieldName, locale);
	}

	public static String extractAttributes(
		DDMStructure ddmStructure, Fields fields, Locale locale) {

		return getDDMIndexer().extractIndexableAttributes(
			ddmStructure, fields, locale);
	}

	public static DDMIndexer getDDMIndexer() {
		PortalRuntimePermission.checkGetBeanProperty(DDMIndexerUtil.class);

		return _ddmIndexer;
	}

	public void setDDMIndexer(DDMIndexer ddmIndexer) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ddmIndexer = ddmIndexer;
	}

	private static DDMIndexer _ddmIndexer;

}