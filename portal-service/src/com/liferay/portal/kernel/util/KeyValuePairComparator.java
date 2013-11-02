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

/**
 * @author Brian Wing Shun Chan
 */
public class KeyValuePairComparator implements Comparator<KeyValuePair> {

	public KeyValuePairComparator() {
		this(true);
	}

	public KeyValuePairComparator(boolean ascending) {
		this(true, ascending);
	}

	public KeyValuePairComparator(boolean byKey, boolean ascending) {
		_byKey = byKey;
		_ascending = ascending;
	}

	@Override
	public int compare(KeyValuePair kvp1, KeyValuePair kvp2) {
		if (_byKey) {
			String key1 = kvp1.getKey();
			String key2 = kvp2.getKey();

			if (_ascending) {
				return key1.compareTo(key2);
			}
			else {
				return -(key1.compareTo(key2));
			}
		}
		else {
			String value1 = kvp1.getValue();
			String value2 = kvp2.getValue();

			if (_ascending) {
				return value1.compareTo(value2);
			}
			else {
				return -(value1.compareTo(value2));
			}
		}
	}

	private boolean _ascending;
	private boolean _byKey;

}