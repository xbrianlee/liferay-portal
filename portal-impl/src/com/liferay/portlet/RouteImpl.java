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

package com.liferay.portlet;

import com.liferay.portal.kernel.portlet.Route;
import com.liferay.portal.kernel.util.InheritableMap;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringEncoder;
import com.liferay.portal.kernel.util.StringParser;
import com.liferay.portal.kernel.util.URLStringEncoder;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Connor McKay
 * @author Brian Wing Shun Chan
 */
public class RouteImpl implements Route {

	public RouteImpl(String pattern) {
		_stringParser = new StringParser(pattern);

		_stringParser.setStringEncoder(_urlEncoder);
	}

	@Override
	public void addGeneratedParameter(String name, String pattern) {
		StringParser stringParser = new StringParser(pattern);

		_generatedParameters.put(name, stringParser);
	}

	@Override
	public void addIgnoredParameter(String name) {
		_ignoredParameters.add(name);
	}

	@Override
	public void addImplicitParameter(String name, String value) {
		_implicitParameters.put(name, value);
	}

	@Override
	public void addOverriddenParameter(String name, String value) {
		_overriddenParameters.put(name, value);
	}

	@Override
	public Map<String, StringParser> getGeneratedParameters() {
		return _generatedParameters;
	}

	@Override
	public Set<String> getIgnoredParameters() {
		return _ignoredParameters;
	}

	@Override
	public Map<String, String> getImplicitParameters() {
		return _implicitParameters;
	}

	@Override
	public Map<String, String> getOverriddenParameters() {
		return _overriddenParameters;
	}

	@Override
	public String parametersToUrl(Map<String, String> parameters) {
		InheritableMap<String, String> allParameters =
			new InheritableMap<String, String>();

		allParameters.setParentMap(parameters);

		// The order is important because virtual parameters may sometimes be
		// checked by implicit parameters

		for (Map.Entry<String, StringParser> entry :
				_generatedParameters.entrySet()) {

			String name = entry.getKey();
			StringParser stringParser = entry.getValue();

			String value = MapUtil.getString(allParameters, name);

			if (!stringParser.parse(value, allParameters)) {
				return null;
			}
		}

		for (Map.Entry<String, String> entry : _implicitParameters.entrySet()) {
			String name = entry.getKey();
			String value = entry.getValue();

			if (!value.equals(MapUtil.getString(allParameters, name))) {
				return null;
			}
		}

		String url = _stringParser.build(allParameters);

		if (Validator.isNull(url)) {
			return null;
		}

		for (String name : _generatedParameters.keySet()) {

			// Virtual parameters will never be placed in the query string, so
			// parameters is modified directly instead of allParameters

			parameters.remove(name);
		}

		for (String name : _implicitParameters.keySet()) {
			parameters.remove(name);
		}

		for (String name : _ignoredParameters) {
			parameters.remove(name);
		}

		return url;
	}

	@Override
	public boolean urlToParameters(String url, Map<String, String> parameters) {
		if (!_stringParser.parse(url, parameters)) {
			return false;
		}

		parameters.putAll(_implicitParameters);
		parameters.putAll(_overriddenParameters);

		// The order is important because generated parameters may be dependent
		// on implicit parameters or overridden parameters

		for (Map.Entry<String, StringParser> entry :
				_generatedParameters.entrySet()) {

			String name = entry.getKey();
			StringParser stringParser = entry.getValue();

			String value = stringParser.build(parameters);

			// Generated parameters are not guaranteed to be created. The format
			// of the virtual parameters in the route pattern must match their
			// format in the generated parameter.

			if (value != null) {
				parameters.put(name, value);
			}
		}

		return true;
	}

	private static StringEncoder _urlEncoder = new URLStringEncoder();

	private Map<String, StringParser> _generatedParameters =
		new HashMap<String, StringParser>();
	private Set<String> _ignoredParameters = new LinkedHashSet<String>();
	private Map<String, String> _implicitParameters =
		new HashMap<String, String>();
	private Map<String, String> _overriddenParameters =
		new HashMap<String, String>();
	private StringParser _stringParser;

}