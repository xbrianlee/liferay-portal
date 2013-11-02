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

package com.liferay.portal.theme;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class ThemeCompanyId implements Serializable {

	public ThemeCompanyId(String value, boolean pattern) {
		_value = value;
		_pattern = pattern;
	}

	public String getValue() {
		return _value;
	}

	public boolean isPattern() {
		return _pattern;
	}

	private boolean _pattern;
	private String _value;

}