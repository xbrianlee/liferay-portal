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
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.Locale;

/**
 * @author Alexander Chow
 */
public interface DDMIndexer {

	public static final String DDM_FIELD_NAMESPACE = "ddm";

	public void addAttributes(
		Document document, DDMStructure ddmStructure, Fields fields);

	public String encodeName(long ddmStructureId, String fieldName);

	public String encodeName(
		long ddmStructureId, String fieldName, Locale locale);

	public String extractIndexableAttributes(
		DDMStructure ddmStructure, Fields fields, Locale locale);

}