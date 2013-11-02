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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.ThemeSetting;

import java.io.Serializable;

/**
 * @author Julio Camarero
 * @author Raymond Augé
 */
public class ThemeSettingImpl implements Serializable, ThemeSetting {

	public static String namespaceProperty(String device) {
		return _PROPERTY_NAMESPACE.concat(device);
	}

	public static String namespaceProperty(String device, String key) {
		return namespaceProperty(device).concat(StringPool.COLON).concat(key);
	}

	public ThemeSettingImpl(
		boolean configurable, String[] options, String script, String type,
		String value) {

		_configurable = configurable;
		_options = options;
		_script = script;
		_type = type;
		_value = value;
	}

	@Override
	public String[] getOptions() {
		return _options;
	}

	@Override
	public String getScript() {
		return _script;
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public String getValue() {
		return _value;
	}

	@Override
	public boolean isConfigurable() {
		return _configurable;
	}

	@Override
	public void setConfigurable(boolean configurable) {
		this._configurable = configurable;
	}

	@Override
	public void setOptions(String[] options) {
		_options = options;
	}

	@Override
	public void setScript(String script) {
		_script = script;
	}

	@Override
	public void setType(String type) {
		_type = type;
	}

	@Override
	public void setValue(String value) {
		_value = value;
	}

	private static final String _PROPERTY_NAMESPACE = "lfr-theme:";

	private static final long serialVersionUID = 1L;

	private boolean _configurable;
	private String[] _options;
	private String _script;
	private String _type;
	private String _value;

}