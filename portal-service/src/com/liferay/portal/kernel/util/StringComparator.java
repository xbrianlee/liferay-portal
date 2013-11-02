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

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class StringComparator implements Comparator<String>, Serializable {

	public StringComparator() {
		this(true, false);
	}

	public StringComparator(boolean ascending, boolean caseSensitive) {
		_ascending = ascending;
		_caseSensitive = caseSensitive;
	}

	@Override
	public int compare(String s1, String s2) {
		if (s1 == null) {
			s1 = StringPool.BLANK;
		}

		if (s2 == null) {
			s2 = StringPool.BLANK;
		}

		if (!_ascending) {
			String temp = s1;

			s1 = s2;
			s2 = temp;
		}

		if (_caseSensitive) {
			return s1.compareTo(s2);
		}
		else {
			return s1.compareToIgnoreCase(s2);
		}
	}

	private boolean _ascending;
	private boolean _caseSensitive;

}