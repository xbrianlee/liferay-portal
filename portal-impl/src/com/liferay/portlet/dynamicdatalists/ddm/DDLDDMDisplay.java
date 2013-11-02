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

package com.liferay.portlet.dynamicdatalists.ddm;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.service.permission.DDLPermission;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.util.BaseDDMDisplay;

import java.util.Locale;

/**
 * @author Eduardo Garcia
 */
public class DDLDDMDisplay extends BaseDDMDisplay {

	@Override
	public String getPortletId() {
		return PortletKeys.DYNAMIC_DATA_LISTS;
	}

	@Override
	public String getResourceName() {
		return DDLPermission.RESOURCE_NAME;
	}

	@Override
	public String getStorageType() {
		return PropsValues.DYNAMIC_DATA_LISTS_STORAGE_TYPE;
	}

	@Override
	public String getStructureName(Locale locale) {
		return LanguageUtil.get(locale, "data-definition");
	}

	@Override
	public String getStructureType() {
		return DDLRecordSet.class.getName();
	}

	@Override
	public long getTemplateHandlerClassNameId(
		DDMTemplate template, long classNameId) {

		return PortalUtil.getClassNameId(DDLRecordSet.class);
	}

}