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

package com.liferay.taglib.aui;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.taglib.aui.base.BaseWorkflowStatusTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class WorkflowStatusTag extends BaseWorkflowStatusTag {

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		super.setAttributes(request);

		Object bean = getBean();

		if (bean == null) {
			bean = pageContext.getAttribute("aui:model-context:bean");
		}

		String helpMessage = getHelpMessage();

		if (Validator.isNull(helpMessage) &&
			(getStatus() == WorkflowConstants.STATUS_APPROVED) &&
			Validator.isNotNull(getVersion())) {

			helpMessage = _HELP_MESSAGE_DEFAULT;
		}

		Class<?> model = getModel();

		if (model == null) {
			model = (Class<?>)pageContext.getAttribute(
				"aui:model-context:model");
		}

		setNamespacedAttribute(request, "bean", bean);
		setNamespacedAttribute(request, "helpMessage", helpMessage);
		setNamespacedAttribute(request, "model", model);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final String _HELP_MESSAGE_DEFAULT =
		"a-new-version-will-be-created-automatically-if-this-content-is-" +
			"modified";

}