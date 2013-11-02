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

package com.liferay.portal.servlet.taglib.ui;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.PortalIncludeUtil;
import com.liferay.portal.security.permission.ResourceActionsUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author     Brian Chan
 * @deprecated As of 6.2.0, replaced by {@link
 *             com.liferay.taglib.ui.InputPermissionsTag}
 */
public class InputPermissionsTagUtil {

	public static void doEndTag(
			String page, String formName, String modelName,
			PageContext pageContext)
		throws JspException {

		try {
			HttpServletRequest request =
				(HttpServletRequest)pageContext.getRequest();

			request.setAttribute(
				"liferay-ui:input-permissions:formName", formName);

			if (modelName != null) {
				List<String> supportedActions =
					ResourceActionsUtil.getModelResourceActions(modelName);
				List<String> groupDefaultActions =
					ResourceActionsUtil.getModelResourceGroupDefaultActions(
						modelName);
				List<String> guestDefaultActions =
					ResourceActionsUtil.getModelResourceGuestDefaultActions(
						modelName);
				List<String> guestUnsupportedActions =
					ResourceActionsUtil.getModelResourceGuestUnsupportedActions(
						modelName);

				request.setAttribute(
					"liferay-ui:input-permissions:modelName", modelName);
				request.setAttribute(
					"liferay-ui:input-permissions:supportedActions",
					supportedActions);
				request.setAttribute(
					"liferay-ui:input-permissions:groupDefaultActions",
					groupDefaultActions);
				request.setAttribute(
					"liferay-ui:input-permissions:guestDefaultActions",
					guestDefaultActions);
				request.setAttribute(
					"liferay-ui:input-permissions:guestUnsupportedActions",
					guestUnsupportedActions);
			}

			PortalIncludeUtil.include(pageContext, page);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new JspException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		InputPermissionsTagUtil.class);

}