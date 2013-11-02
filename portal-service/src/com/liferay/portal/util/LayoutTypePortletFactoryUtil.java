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

package com.liferay.portal.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortlet;

/**
 * @author Raymond Augé
 */
public class LayoutTypePortletFactoryUtil {

	public static LayoutTypePortlet create(Layout layout) {
		return getLayoutTypePortletFactory().create(layout);
	}

	public static LayoutTypePortletFactory getLayoutTypePortletFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			LayoutTypePortletFactoryUtil.class);

		return _layoutTypePortletFactory;
	}

	public void setLayoutTypePortletFactory(
		LayoutTypePortletFactory layoutTypePortletFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_layoutTypePortletFactory = layoutTypePortletFactory;
	}

	private static LayoutTypePortletFactory _layoutTypePortletFactory;

}