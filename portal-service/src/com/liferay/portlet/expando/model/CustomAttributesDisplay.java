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

package com.liferay.portlet.expando.model;

import com.liferay.portal.theme.ThemeDisplay;

/**
 * @author Jorge Ferrer
 */
public interface CustomAttributesDisplay {

	public String getClassName();

	public String getIconPath(ThemeDisplay themeDisplay);

	public String getPortletId();

	public void setClassNameId(long classNameId);

	public void setPortletId(String portletId);

}