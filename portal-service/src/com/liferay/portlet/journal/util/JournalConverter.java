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

import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

/**
 * @author Marcellus Tavares
 * @author Bruno Basto
 */
public interface JournalConverter {

	public String getContent(DDMStructure ddmStructure, Fields ddmFields)
		throws Exception;

	public Fields getDDMFields(DDMStructure ddmStructure, String content)
		throws Exception;

	public String getDDMXSD(String journalXSD) throws Exception;

	public String getJournalXSD(String ddmXSD) throws Exception;

}