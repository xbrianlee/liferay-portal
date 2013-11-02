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

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntryTypeConstants;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;

import java.util.List;
import java.util.Locale;

/**
 * @author Alexander Chow
 * @author Mate Thurzo
 */
public class DLFileEntryTypeImpl extends DLFileEntryTypeBaseImpl {

	public DLFileEntryTypeImpl() {
	}

	@Override
	public List<DDMStructure> getDDMStructures() throws SystemException {
		return DDMStructureLocalServiceUtil.getDLFileEntryTypeStructures(
			getFileEntryTypeId());
	}

	@Override
	public String getName(Locale locale) {
		String name = super.getName(locale);

		if (getFileEntryTypeId() ==
				DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT) {

			name = LanguageUtil.get(locale, name);
		}

		return name;
	}

	@Override
	public boolean isExportable() {
		if (getFileEntryTypeId() ==
				DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_BASIC_DOCUMENT) {

			return false;
		}

		return true;
	}

}