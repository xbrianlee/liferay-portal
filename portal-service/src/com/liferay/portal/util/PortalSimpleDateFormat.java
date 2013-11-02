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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

/**
 * @author Mate Thurzo
 */
public class PortalSimpleDateFormat extends SimpleDateFormat {

	public PortalSimpleDateFormat(String pattern, Locale locale) {
		super(pattern, locale);

		if (pattern.equals(DateUtil.ISO_8601_PATTERN)) {
			_iso8601Pattern = true;
		}
	}

	@Override
	public StringBuffer format(
		Date date, StringBuffer toAppendToSB, FieldPosition fieldPosition) {

		StringBuffer originalSB = super.format(
			date, toAppendToSB, fieldPosition);

		if (!_iso8601Pattern) {
			return originalSB;
		}

		StringBuffer modifiedSB = new StringBuffer();

		modifiedSB.append(originalSB.substring(0, 22));
		modifiedSB.append(StringPool.COLON);
		modifiedSB.append(originalSB.substring(22));

		return modifiedSB;
	}

	private boolean _iso8601Pattern;

}