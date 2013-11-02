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

import java.lang.reflect.Method;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Spasic
 */
public interface JSONWebServiceActionsManager {

	public Set<String> getContextPaths();

	public JSONWebServiceAction getJSONWebServiceAction(
		HttpServletRequest request);

	public JSONWebServiceAction getJSONWebServiceAction(
		HttpServletRequest request, String path, String method,
		Map<String, Object> parameters);

	public JSONWebServiceActionMapping getJSONWebServiceActionMapping(
		String signature);

	public List<JSONWebServiceActionMapping> getJSONWebServiceActionMappings(
		String contextPath);

	public int getJSONWebServiceActionsCount(String contextPath);

	public void registerJSONWebServiceAction(
		String contextPath, Class<?> actionClass, Method actionMethod,
		String path, String method);

	public void registerJSONWebServiceAction(
		String contextPath, Object actionObject, Class<?> actionClass,
		Method actionMethod, String path, String method);

	public int registerServletContext(ServletContext servletContext);

	public int registerServletContext(String contextPath);

	public int unregisterJSONWebServiceActions(Object actionObject);

	public int unregisterJSONWebServiceActions(String contextPath);

	public int unregisterServletContext(ServletContext servletContext);

}