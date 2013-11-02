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

import javax.portlet.PortletMode;

/**
 * @author Brian Wing Shun Chan
 */
public class LiferayPortletMode extends PortletMode {

	public static final PortletMode ABOUT = new PortletMode("about");

	public static final PortletMode CONFIG = new PortletMode("config");

	public static final PortletMode EDIT_DEFAULTS = new PortletMode(
		"edit_defaults");

	public static final PortletMode EDIT_GUEST = new PortletMode("edit_guest");

	public static final PortletMode PREVIEW = new PortletMode("preview");

	public static final PortletMode PRINT = new PortletMode("print");

	public LiferayPortletMode(String name) {
		super(name);
	}

}