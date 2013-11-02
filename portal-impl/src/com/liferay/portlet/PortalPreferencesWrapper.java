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

import java.io.IOException;
import java.io.Serializable;

import java.util.Enumeration;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

/**
 * @author Alexander Chow
 */
public class PortalPreferencesWrapper
	implements Cloneable, PortletPreferences, Serializable {

	public PortalPreferencesWrapper(
		PortalPreferencesImpl portalPreferencesImpl) {

		_portalPreferencesImpl = portalPreferencesImpl;
	}

	@Override
	public PortalPreferencesWrapper clone() {
		return new PortalPreferencesWrapper(_portalPreferencesImpl.clone());
	}

	@Override
	public Map<String, String[]> getMap() {
		return _portalPreferencesImpl.getMap();
	}

	@Override
	public Enumeration<String> getNames() {
		return _portalPreferencesImpl.getNames();
	}

	public PortalPreferencesImpl getPortalPreferencesImpl() {
		return _portalPreferencesImpl;
	}

	@Override
	public String getValue(String key, String def) {
		return _portalPreferencesImpl.getValue(null, key, def);
	}

	@Override
	public String[] getValues(String key, String[] def) {
		return _portalPreferencesImpl.getValues(null, key, def);
	}

	@Override
	public boolean isReadOnly(String key) {
		return _portalPreferencesImpl.isReadOnly(key);
	}

	@Override
	public void reset(String key) throws ReadOnlyException {
		_portalPreferencesImpl.reset(key);
	}

	@Override
	public void setValue(String key, String value) throws ReadOnlyException {
		_portalPreferencesImpl.setValue(key, value);
	}

	@Override
	public void setValues(String key, String[] values)
		throws ReadOnlyException {

		_portalPreferencesImpl.setValues(key, values);
	}

	@Override
	public void store() throws IOException {
		_portalPreferencesImpl.store();
	}

	private PortalPreferencesImpl _portalPreferencesImpl;

}