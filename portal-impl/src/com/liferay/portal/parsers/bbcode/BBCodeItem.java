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

package com.liferay.portal.parsers.bbcode;

/**
 * @author Iliyan Peychev
 */
public class BBCodeItem {

	public BBCodeItem(int type, String attribute, String value) {
		_attribute = attribute;
		_type = type;
		_value = value;
	}

	public String getAttribute() {
		return _attribute;
	}

	public int getType() {
		return _type;
	}

	public String getValue() {
		return _value;
	}

	private String _attribute;
	private int _type;
	private String _value;

}