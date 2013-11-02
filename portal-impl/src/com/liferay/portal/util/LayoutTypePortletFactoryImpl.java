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

import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.impl.LayoutTypePortletImpl;

/**
 * @author Raymond Augé
 */
@DoPrivileged
public class LayoutTypePortletFactoryImpl implements LayoutTypePortletFactory {

	@Override
	public LayoutTypePortlet create(Layout layout) {
		return new LayoutTypePortletImpl(layout);
	}

}