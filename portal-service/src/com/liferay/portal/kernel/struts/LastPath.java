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

package com.liferay.portal.kernel.struts;

import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class LastPath implements Serializable {

	public LastPath(String contextPath, String path) {
		this(contextPath, path, Collections.<String, String[]>emptyMap());
	}

	public LastPath(
		String contextPath, String path, Map<String, String[]> parameterMap) {

		_contextPath = contextPath;
		_path = path;
		_parameterMap = new LinkedHashMap<String, String[]>(parameterMap);
	}

	public String getContextPath() {
		return _contextPath;
	}

	public Map<String, String[]> getParameterMap() {
		return _parameterMap;
	}

	public String getPath() {
		return _path;
	}

	public void setContextPath(String contextPath) {
		_contextPath = contextPath;
	}

	public void setParameterMap(Map<String, String[]> parameterMap) {
		_parameterMap = parameterMap;
	}

	public void setPath(String path) {
		_path = path;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{contextPath=");
		sb.append(_contextPath);
		sb.append(", path=");
		sb.append(_path);
		sb.append("}");

		return sb.toString();
	}

	private String _contextPath;
	private Map<String, String[]> _parameterMap;
	private String _path;

}