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

package com.liferay.util;

import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.property.XProperty;

/**
 * @author Samuel Kong
 */
public class TimeZoneSensitive extends XProperty {

	public static final String PROPERTY_NAME = "X-LIFERAY-TIMEZONE-SENSITIVE";

	public TimeZoneSensitive() {
		super(PROPERTY_NAME);
	}

	public TimeZoneSensitive(ParameterList list, String value) {
		super(PROPERTY_NAME, list, value);
	}

	public TimeZoneSensitive(String value) {
		super(PROPERTY_NAME, value);
	}

	public void setValue(boolean value) {
		setValue(value ? "TRUE" : "FALSE");
	}

}