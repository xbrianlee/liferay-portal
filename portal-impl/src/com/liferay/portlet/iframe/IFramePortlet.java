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

package com.liferay.portlet.iframe;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.StrutsPortlet;

import javax.portlet.PortletConfig;
import javax.portlet.PortletException;

/**
 * @author Brian Wing Shun Chan
 */
public class IFramePortlet extends StrutsPortlet {

	public static final String DEFAULT_EDIT_ACTION = "/iframe/edit";

	public static final String DEFAULT_VIEW_ACTION = "/iframe/view";

	@Override
	public void init(PortletConfig portletConfig) throws PortletException {
		super.init(portletConfig);

		if (Validator.isNull(editAction)) {
			editAction = DEFAULT_EDIT_ACTION;
		}

		if (Validator.isNull(viewAction)) {
			viewAction = DEFAULT_VIEW_ACTION;
		}
	}

}