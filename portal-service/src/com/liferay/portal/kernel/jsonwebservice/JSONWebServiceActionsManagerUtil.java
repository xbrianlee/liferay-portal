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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.lang.reflect.Method;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Spasic
 */
public class JSONWebServiceActionsManagerUtil {

	public static Set<String> getContextPaths() {
		return _jsonWebServiceActionsManager. getContextPaths();
	}

	public static JSONWebServiceAction getJSONWebServiceAction(
		HttpServletRequest request) {

		return getJSONWebServiceActionsManager().getJSONWebServiceAction(
			request);
	}

	public static JSONWebServiceAction getJSONWebServiceAction(
		HttpServletRequest request, String path, String method,
		Map<String, Object> parameterMap) {

		return getJSONWebServiceActionsManager().getJSONWebServiceAction(
			request, path, method, parameterMap);
	}

	public static JSONWebServiceActionMapping getJSONWebServiceActionMapping(
		String signature) {

		return getJSONWebServiceActionsManager().getJSONWebServiceActionMapping(
			signature);
	}

	public static List<JSONWebServiceActionMapping>
		getJSONWebServiceActionMappings(String contextPath) {

		PortalRuntimePermission.checkGetBeanProperty(
			JSONWebServiceActionsManagerUtil.class);

		return _jsonWebServiceActionsManager.getJSONWebServiceActionMappings(
			contextPath);
	}

	public static int getJSONWebServiceActionsCount(String contextPath) {
		return getJSONWebServiceActionsManager().getJSONWebServiceActionsCount(
			contextPath);
	}

	public static JSONWebServiceActionsManager
		getJSONWebServiceActionsManager() {

		return _jsonWebServiceActionsManager;
	}

	public static void registerJSONWebServiceAction(
		String contextPath, Class<?> actionClass, Method actionMethod,
		String path, String method) {

		getJSONWebServiceActionsManager().registerJSONWebServiceAction(
			contextPath, actionClass, actionMethod, path, method);
	}

	public static void registerJSONWebServiceAction(
		String contextPath, Object actionObject, Class<?> actionClass,
		Method actionMethod, String path, String method) {

		getJSONWebServiceActionsManager().registerJSONWebServiceAction(
			contextPath, actionObject, actionClass, actionMethod, path, method);
	}

	public static int registerServletContext(ServletContext servletContext) {
		return getJSONWebServiceActionsManager().registerServletContext(
			servletContext);
	}

	public static int registerServletContext(String contextPath) {
		return getJSONWebServiceActionsManager().registerServletContext(
			contextPath);
	}

	public static int unregisterJSONWebServiceActions(Object actionObject) {
		return getJSONWebServiceActionsManager().
			unregisterJSONWebServiceActions(actionObject);
	}

	public static int unregisterJSONWebServiceActions(String contextPath) {
		return getJSONWebServiceActionsManager().
			unregisterJSONWebServiceActions(contextPath);
	}

	public static int unregisterServletContext(ServletContext servletContext) {
		return getJSONWebServiceActionsManager().unregisterServletContext(
			servletContext);
	}

	public void setJSONWebServiceActionsManager(
		JSONWebServiceActionsManager jsonWebServiceActionsManager) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_jsonWebServiceActionsManager = jsonWebServiceActionsManager;
	}

	private static JSONWebServiceActionsManager _jsonWebServiceActionsManager;

}