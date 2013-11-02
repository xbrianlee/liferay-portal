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

package com.liferay.portal.kernel.portlet;

import javax.portlet.PortletSession;

import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public interface LiferayPortletSession extends PortletSession {

	public static final String LAYOUT_SEPARATOR = "_LAYOUT_";

	public static final String PORTLET_SCOPE_NAMESPACE = "javax.portlet.p.";

	public void setHttpSession(HttpSession session);

}