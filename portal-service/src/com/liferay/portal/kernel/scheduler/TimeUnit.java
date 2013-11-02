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

package com.liferay.portal.kernel.scheduler;

/**
 * @author Brian Wing Shun Chan
 */
public enum TimeUnit {

	DAY("day"), HOUR("hour"), MINUTE("minute"), SECOND("second"), WEEK("week");

	public static TimeUnit parse(String value) {
		if (DAY.getValue().equals(value)) {
			return DAY;
		}
		else if (HOUR.getValue().equals(value)) {
			return HOUR;
		}
		else if (MINUTE.getValue().equals(value)) {
			return MINUTE;
		}
		else if (SECOND.getValue().equals(value)) {
			return SECOND;
		}
		else if (WEEK.getValue().equals(value)) {
			return WEEK;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private TimeUnit(String value) {
		_value = value;
	}

	private String _value;

}