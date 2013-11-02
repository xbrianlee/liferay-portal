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
public class ObjectValuePairComparator<K, V>
	implements Comparator<ObjectValuePair<K, V>> {

	public ObjectValuePairComparator() {
		this(true);
	}

	public ObjectValuePairComparator(boolean ascending) {
		this(true, ascending);
	}

	public ObjectValuePairComparator(boolean byKey, boolean ascending) {
		_byKey = byKey;
		_ascending = ascending;
	}

	@Override
	public int compare(ObjectValuePair<K, V> ovp1, ObjectValuePair<K, V> ovp2) {
		if (_byKey) {
			Comparable<K> key1 = (Comparable<K>)ovp1.getKey();
			Comparable<K> key2 = (Comparable<K>)ovp2.getKey();

			if (_ascending) {
				return key1.compareTo((K)key2);
			}
			else {
				return -(key1.compareTo((K)key2));
			}
		}
		else {
			Comparable<V> value1 = (Comparable<V>)ovp1.getValue();
			Comparable<V> value2 = (Comparable<V>)ovp2.getValue();

			if (_ascending) {
				return value1.compareTo((V)value2);
			}
			else {
				return -(value1.compareTo((V)value2));
			}
		}
	}

	private boolean _ascending;
	private boolean _byKey;

}