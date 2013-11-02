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

package com.liferay.portal.kernel.jsonwebservice;

import com.liferay.portal.kernel.util.MethodParameter;

import java.lang.reflect.Method;

/**
 * @author Igor Spasic
 */
public interface JSONWebServiceActionMapping {

	public Class<?> getActionClass();

	public Method getActionMethod();

	public Object getActionObject();

	public String getContextPath();

	public String getMethod();

	public MethodParameter[] getMethodParameters();

	public String getPath();

	public String getSignature();

}