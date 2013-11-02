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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceActionMapping;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.MethodParameter;
import com.liferay.portal.kernel.util.MethodParametersResolverUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.lang.reflect.Method;

/**
 * @author Igor Spasic
 */
public class JSONWebServiceActionConfig
	implements Comparable<JSONWebServiceActionConfig>,
	JSONWebServiceActionMapping {

	public JSONWebServiceActionConfig(
		String contextPath, Class<?> actionClass, Method actionMethod,
		String path, String method) {

		_contextPath = contextPath;
		_actionClass = actionClass;
		_actionMethod = actionMethod;
		_path = path;
		_method = method;

		_methodParameters =
			MethodParametersResolverUtil.resolveMethodParameters(actionMethod);

		_fullPath = _contextPath + _path;

		StringBundler sb = new StringBundler(_methodParameters.length * 2 + 4);

		sb.append(_fullPath);
		sb.append(CharPool.MINUS);
		sb.append(_methodParameters.length);

		for (MethodParameter methodParameter : _methodParameters) {
			sb.append(CharPool.MINUS);
			sb.append(methodParameter.getName());
		}

		_signature = sb.toString();
	}

	public JSONWebServiceActionConfig(
		String contextPath, Object actionObject, Class<?> actionClass,
		Method actionMethod, String path, String method) {

		this(contextPath, actionClass, actionMethod, path, method);

		_actionObject = actionObject;

		try {
			Class<?> actionObjectClass = actionObject.getClass();

			Method actionObjectClassActionMethod = actionObjectClass.getMethod(
				actionMethod.getName(), actionMethod.getParameterTypes());

			_actionMethod = actionObjectClassActionMethod;
		}
		catch (NoSuchMethodException nsme) {
			throw new IllegalArgumentException(nsme);
		}
	}

	@Override
	public int compareTo(
		JSONWebServiceActionConfig jsonWebServiceActionConfig) {

		return _signature.compareTo(jsonWebServiceActionConfig._signature);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof JSONWebServiceActionConfig)) {
			return false;
		}

		JSONWebServiceActionConfig jsonWebServiceActionConfig =
			(JSONWebServiceActionConfig)object;

		if (Validator.equals(
				_signature, jsonWebServiceActionConfig._signature)) {

			return true;
		}

		return false;
	}

	@Override
	public Class<?> getActionClass() {
		return _actionClass;
	}

	@Override
	public Method getActionMethod() {
		return _actionMethod;
	}

	@Override
	public Object getActionObject() {
		return _actionObject;
	}

	@Override
	public String getContextPath() {
		return _contextPath;
	}

	public String getFullPath() {
		return _fullPath;
	}

	@Override
	public String getMethod() {
		return _method;
	}

	@Override
	public MethodParameter[] getMethodParameters() {
		return _methodParameters;
	}

	@Override
	public String getPath() {
		return _path;
	}

	@Override
	public String getSignature() {
		return _signature;
	}

	@Override
	public int hashCode() {
		return _signature.hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{actionClass=");
		sb.append(_actionClass);
		sb.append(", actionMethod=");
		sb.append(_actionMethod);
		sb.append(", contextPath=");
		sb.append(_contextPath);
		sb.append(", fullPath=");
		sb.append(_fullPath);
		sb.append(", method=");
		sb.append(_method);
		sb.append(", methodParameters=");
		sb.append(_methodParameters);
		sb.append(", path=");
		sb.append(_path);
		sb.append(", signature=");
		sb.append(_signature);
		sb.append("}");

		return sb.toString();
	}

	private Class<?> _actionClass;
	private Method _actionMethod;
	private Object _actionObject;
	private String _contextPath;
	private String _fullPath;
	private String _method;
	private MethodParameter[] _methodParameters;
	private String _path;
	private String _signature;

}