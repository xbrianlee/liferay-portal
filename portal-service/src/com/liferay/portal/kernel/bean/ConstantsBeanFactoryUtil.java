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

package com.liferay.portal.kernel.bean;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Shuyang Zhou
 */
public class ConstantsBeanFactoryUtil {

	public static Object getConstantsBean(Class<?> constantsClass) {
		return _constantsBeanFactory.getConstantsBean(constantsClass);
	}

	public void setConstantsBeanFactory(
		ConstantsBeanFactory constantsBeanFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_constantsBeanFactory = constantsBeanFactory;
	}

	private static ConstantsBeanFactory _constantsBeanFactory;

}