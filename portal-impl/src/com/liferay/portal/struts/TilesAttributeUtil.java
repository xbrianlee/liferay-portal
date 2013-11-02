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

package com.liferay.portal.struts;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.struts.taglib.tiles.ComponentConstants;
import org.apache.struts.tiles.ComponentContext;

/**
 * @author Shuyang Zhou
 */
public class TilesAttributeUtil {

	public static Object getTilesAttribute(
		PageContext pageContext, String tilesAttributeName) {

		ServletRequest servletRequest = pageContext.getRequest();

		String value = servletRequest.getParameter(tilesAttributeName);

		if (value != null) {
			return value;
		}

		ComponentContext componentContext =
			(ComponentContext)pageContext.getAttribute(
				ComponentConstants.COMPONENT_CONTEXT,
				PageContext.REQUEST_SCOPE);

		if (componentContext == null) {
			return null;
		}

		return componentContext.getAttribute(tilesAttributeName);
	}

	public static void removeComponentContext(PageContext pageContext) {
		pageContext.removeAttribute(ComponentConstants.COMPONENT_CONTEXT);
	}

}