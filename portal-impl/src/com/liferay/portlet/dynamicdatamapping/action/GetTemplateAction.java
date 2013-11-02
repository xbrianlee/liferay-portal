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

import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Juan Fernández
 */
public class GetTemplateAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		try {
			long templateId = ParamUtil.getLong(request, "templateId");

			DDMTemplate template = DDMTemplateServiceUtil.getTemplate(
				templateId);

			String script = template.getScript();

			String contentType = null;

			String type = template.getType();
			String language = GetterUtil.getString(
				template.getLanguage(), TemplateConstants.LANG_TYPE_VM);

			if (type.equals(DDMTemplateConstants.TEMPLATE_TYPE_FORM) ||
				language.equals(TemplateConstants.LANG_TYPE_XSL)) {

				contentType = ContentTypes.TEXT_XML_UTF8;
			}
			else {
				contentType = ContentTypes.TEXT_PLAIN_UTF8;
			}

			ServletResponseUtil.sendFile(
				request, response, null, script.getBytes(), contentType);

			return null;
		}
		catch (Exception e) {
			PortalUtil.sendError(e, request, response);

			return null;
		}
	}

}