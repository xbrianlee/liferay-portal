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

import com.liferay.portal.util.PropsValues;

import java.io.IOException;
import java.io.Serializable;

import java.util.Enumeration;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletPreferencesWrapper
	implements PortletPreferences, Serializable {

	public PortletPreferencesWrapper(
		PortletPreferences portletPreferences, String lifecycle) {

		_portletPreferences = portletPreferences;
		_lifecycle = lifecycle;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PortletPreferencesWrapper)) {
			return false;
		}

		PortletPreferencesWrapper portletPreferencesWrapper =
			(PortletPreferencesWrapper)obj;

		if (getPortletPreferencesImpl().equals(
				portletPreferencesWrapper.getPortletPreferencesImpl())) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Map<String, String[]> getMap() {
		return _portletPreferences.getMap();
	}

	@Override
	public Enumeration<String> getNames() {
		return _portletPreferences.getNames();
	}

	public PortletPreferencesImpl getPortletPreferencesImpl() {
		return (PortletPreferencesImpl)_portletPreferences;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getPortletPreferencesImpl}
	 */
	public PortletPreferencesImpl getPreferencesImpl() {
		return getPortletPreferencesImpl();
	}

	@Override
	public String getValue(String key, String def) {
		return _portletPreferences.getValue(key, def);
	}

	@Override
	public String[] getValues(String key, String[] def) {
		return _portletPreferences.getValues(key, def);
	}

	@Override
	public int hashCode() {
		return _portletPreferences.hashCode();
	}

	@Override
	public boolean isReadOnly(String key) {
		return _portletPreferences.isReadOnly(key);
	}

	@Override
	public void reset(String key) throws ReadOnlyException {
		_portletPreferences.reset(key);
	}

	@Override
	public void setValue(String key, String value) throws ReadOnlyException {
		_portletPreferences.setValue(key, value);
	}

	@Override
	public void setValues(String key, String[] values)
		throws ReadOnlyException {

		_portletPreferences.setValues(key, values);
	}

	@Override
	public void store() throws IOException, ValidatorException {
		if (PropsValues.TCK_URL) {

			// Be strict to pass the TCK

			if (_lifecycle.equals(PortletRequest.ACTION_PHASE)) {
				_portletPreferences.store();
			}
			else {
				throw new IllegalStateException(
					"Preferences cannot be stored inside a render call");
			}
		}
		else {

			// Relax so that poorly written portlets can still work

			_portletPreferences.store();
		}
	}

	private String _lifecycle;
	private PortletPreferences _portletPreferences;

}