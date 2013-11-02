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

import com.liferay.portal.kernel.util.StringPool;

import java.lang.reflect.Method;

/**
 * @author Igor Spasic
 */
public class JSONWebServiceMappingResolver {

	public JSONWebServiceMappingResolver(
		JSONWebServiceNaming jsonWebServiceNaming) {

		_jsonWebServiceNaming = jsonWebServiceNaming;
	}

	public String resolveHttpMethod(Method method) {
		JSONWebService jsonWebServiceAnnotation = method.getAnnotation(
			JSONWebService.class);

		String httpMethod = null;

		if (jsonWebServiceAnnotation != null) {
			httpMethod = jsonWebServiceAnnotation.method().trim();
		}

		if ((httpMethod != null) && (httpMethod.length() != 0)) {
			return httpMethod;
		}

		return _jsonWebServiceNaming.convertMethodNameToHttpMethod(method);
	}

	public String resolvePath(Class<?> clazz, Method method) {
		JSONWebService jsonWebServiceAnnotation = method.getAnnotation(
			JSONWebService.class);

		String path = null;

		if (jsonWebServiceAnnotation != null) {
			path = jsonWebServiceAnnotation.value().trim();
		}

		if ((path == null) || (path.length() == 0)) {
			path = _jsonWebServiceNaming.convertMethodNameToPath(method);
		}

		if (path.startsWith(StringPool.SLASH)) {
			return path;
		}

		path = StringPool.SLASH + path;

		String pathFromClass = null;

		jsonWebServiceAnnotation = clazz.getAnnotation(JSONWebService.class);

		if (jsonWebServiceAnnotation != null) {
			pathFromClass = jsonWebServiceAnnotation.value().trim();
		}

		if ((pathFromClass == null) || (pathFromClass.length() == 0)) {
			pathFromClass = _jsonWebServiceNaming.convertClassNameToPath(clazz);
		}

		if (!pathFromClass.startsWith(StringPool.SLASH)) {
			pathFromClass = StringPool.SLASH + pathFromClass;
		}

		return pathFromClass + path;
	}

	private JSONWebServiceNaming _jsonWebServiceNaming;

}