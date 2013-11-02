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

package com.liferay.portal.kernel.util;

import java.util.Comparator;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class TimeZoneComparator implements Comparator<TimeZone> {

	public TimeZoneComparator(Locale locale) {
		_locale = locale;
	}

	@Override
	public int compare(TimeZone timeZone1, TimeZone timeZone2) {
		Integer rawOffset1 = timeZone1.getRawOffset();
		Integer rawOffset2 = timeZone2.getRawOffset();

		int value = rawOffset1.compareTo(rawOffset2);

		if (value == 0) {
			String displayName1 = timeZone1.getDisplayName(_locale);
			String displayName2 = timeZone2.getDisplayName(_locale);

			value = displayName1.compareTo(displayName2);
		}

		return value;
	}

	private Locale _locale;

}