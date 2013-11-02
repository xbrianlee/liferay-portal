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

package com.liferay.portlet.journal.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

/**
 * @author Marcellus Tavares
 * @author Bruno Basto
 */
public class JournalConverterUtil {

	public static String getContent(DDMStructure ddmStructure, Fields ddmFields)
		throws Exception {

		return getJournalConverter().getContent(ddmStructure, ddmFields);
	}

	public static Fields getDDMFields(DDMStructure ddmStructure, String content)
		throws Exception {

		return getJournalConverter().getDDMFields(ddmStructure, content);
	}

	public static String getDDMXSD(String journalXSD) throws Exception {
		return getJournalConverter().getDDMXSD(journalXSD);
	}

	public static JournalConverter getJournalConverter() {
		PortalRuntimePermission.checkGetBeanProperty(
			JournalConverterUtil.class);

		return _journalConverter;
	}

	public static String getJournalXSD(String ddmXSD) throws Exception {
		return getJournalConverter().getJournalXSD(ddmXSD);
	}

	public void setJournalConverter(JournalConverter journalConverter) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_journalConverter = journalConverter;
	}

	private static JournalConverter _journalConverter;

}