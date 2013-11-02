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

package com.liferay.portal.layoutconfiguration.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.layoutconfiguration.util.xml.RuntimeLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Shuyang Zhou
 */
public class RuntimePageUtil {

	public static StringBundler getProcessedTemplate(
			PageContext pageContext, String portletId,
			TemplateResource templateResource)
		throws Exception {

		return getRuntimePage().getProcessedTemplate(
			pageContext, portletId, templateResource);
	}

	public static RuntimePage getRuntimePage() {
		PortalRuntimePermission.checkGetBeanProperty(RuntimePageUtil.class);

		return _runtimePage;
	}

	public static void processCustomizationSettings(
			PageContext pageContext, TemplateResource templateResource)
		throws Exception {

		getRuntimePage().processCustomizationSettings(
			pageContext, templateResource);
	}

	public static void processTemplate(
			PageContext pageContext, String portletId,
			TemplateResource templateResource)
		throws Exception {

		getRuntimePage().processTemplate(
			pageContext, portletId, templateResource);
	}

	public static void processTemplate(
			PageContext pageContext, TemplateResource templateResource)
		throws Exception {

		getRuntimePage().processTemplate(pageContext, templateResource);
	}

	public static String processXML(
			HttpServletRequest request, HttpServletResponse response,
			String content)
		throws Exception {

		return getRuntimePage().processXML(request, response, content);
	}

	public static String processXML(
			HttpServletRequest request, String content,
			RuntimeLogic runtimeLogic)
		throws Exception {

		return getRuntimePage().processXML(request, content, runtimeLogic);
	}

	public void setRuntimePage(RuntimePage runtimePage) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_runtimePage = runtimePage;
	}

	private static RuntimePage _runtimePage;

}