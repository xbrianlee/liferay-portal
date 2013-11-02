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

package com.liferay.portlet.dynamicdatamapping.action;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureServiceUtil;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Bruno Basto
 * @author Eduardo Lundgren
 */
public class ActionUtil {

	public static void getStructure(HttpServletRequest request)
		throws Exception {

		long classNameId = ParamUtil.getLong(request, "classNameId");
		long classPK = ParamUtil.getLong(request, "classPK");

		DDMStructure structure = null;

		long structureClassNameId = PortalUtil.getClassNameId(
			DDMStructure.class);

		if ((structureClassNameId == classNameId) && (classPK > 0)) {
			structure = DDMStructureServiceUtil.getStructure(classPK);
		}

		request.setAttribute(WebKeys.DYNAMIC_DATA_MAPPING_STRUCTURE, structure);
	}

	public static void getStructure(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getStructure(request);
	}

	public static void getTemplate(HttpServletRequest request)
		throws Exception {

		long templateId = ParamUtil.getLong(request, "templateId");

		DDMTemplate template = null;

		if (templateId > 0) {
			template = DDMTemplateLocalServiceUtil.getDDMTemplate(templateId);
		}

		request.setAttribute(WebKeys.DYNAMIC_DATA_MAPPING_TEMPLATE, template);
	}

	public static void getTemplate(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getTemplate(request);
	}

}