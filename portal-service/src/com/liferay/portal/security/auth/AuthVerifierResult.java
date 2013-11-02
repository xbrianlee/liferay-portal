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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.util.StringBundler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tomas Polesovsky
 */
public class AuthVerifierResult {

	public String getPassword() {
		return _password;
	}

	public Map<String, Object> getSettings() {
		return _settings;
	}

	public State getState() {
		return _state;
	}

	public long getUserId() {
		return _userId;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public void setSettings(Map<String, Object> settings) {
		_settings = settings;
	}

	public void setState(State state) {
		_state = state;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{settings=");
		sb.append(_settings);
		sb.append(", state=");
		sb.append(_state);
		sb.append(", userId=");
		sb.append(_userId);
		sb.append("}");

		return sb.toString();
	}

	public enum State {

		NOT_APPLICABLE, INVALID_CREDENTIALS, SUCCESS

	}

	private String _password;
	private Map<String, Object> _settings = new HashMap<String, Object>();
	private State _state = State.NOT_APPLICABLE;
	private long _userId;

}