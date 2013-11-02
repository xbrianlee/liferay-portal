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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Juan Fernández
 */
public class DDMTemplateHelperUtil {

	public static DDMStructure fetchStructure(DDMTemplate ddmTemplate) {
		return getDDMTemplateHelper().fetchStructure(ddmTemplate);
	}

	public static String getAutocompleteJSON(
			HttpServletRequest request, String language)
		throws Exception {

		return getDDMTemplateHelper().getAutocompleteJSON(request, language);
	}

	public static DDMTemplateHelper getDDMTemplateHelper() {
		PortalRuntimePermission.checkGetBeanProperty(
			DDMTemplateHelperUtil.class);

		return _ddmTemplateHelper;
	}

	public static boolean isAutocompleteEnabled(String language) {
		return getDDMTemplateHelper().isAutocompleteEnabled(language);
	}

	public void setDDMTemplateHelper(DDMTemplateHelper ddmTemplateHelper) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ddmTemplateHelper = ddmTemplateHelper;
	}

	private static DDMTemplateHelper _ddmTemplateHelper;

}