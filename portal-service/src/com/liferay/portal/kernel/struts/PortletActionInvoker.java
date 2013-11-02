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

package com.liferay.portal.kernel.struts;

import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletActionInvoker {

	public static void processAction(
			String className, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		MethodKey methodKey = new MethodKey(
			ClassResolverUtil.resolveByPortalClassLoader(className),
			"processAction",
			new Class<?>[] {
				ClassResolverUtil.resolveByPortalClassLoader(
					"org.apache.struts.action.ActionMapping"),
				ClassResolverUtil.resolveByPortalClassLoader(
					"org.apache.struts.action.ActionForm"),
				PortletConfig.class, ActionRequest.class, ActionResponse.class
			});

		PortalClassInvoker.invoke(
			true, methodKey, null, null, portletConfig, actionRequest,
			actionResponse);
	}

}