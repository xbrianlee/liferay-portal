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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael Young
 * @author Connor McKay
 * @author Shuyang Zhou
 */
public class InheritableMap<K, V> extends HashMap<K, V> {

	public InheritableMap() {
		super();
	}

	public InheritableMap(Map<? extends K, ? extends V> map) {
		super(map);
	}

	@Override
	public void clear() {
		super.clear();

		_parentMap = null;
	}

	@Override
	public boolean containsKey(Object key) {
		if ((_parentMap != null) && _parentMap.containsKey(key)) {
			return true;
		}
		else {
			return super.containsKey(key);
		}
	}

	@Override
	public boolean containsValue(Object value) {
		if ((_parentMap != null) && _parentMap.containsValue(value)) {
			return true;
		}
		else {
			return super.containsValue(value);
		}
	}

	@Override
	public V get(Object key) {
		V value = super.get(key);

		if (value != null) {
			return value;
		}
		else if (_parentMap != null) {
			return _parentMap.get(key);
		}

		return null;
	}

	public Map<K, V> getParentMap() {
		return _parentMap;
	}

	@Override
	public V remove(Object key) {
		V value = super.remove(key);

		if (value != null) {
			return value;
		}
		else if (_parentMap != null) {
			return _parentMap.remove(key);
		}

		return null;
	}

	public void setParentMap(Map<? extends K, ? extends V> parentMap) {
		_parentMap = (Map<K, V>)parentMap;
	}

	@Override
	public String toString() {
		String string = super.toString();

		String parentString = "{}";

		if (_parentMap != null) {
			parentString = _parentMap.toString();
		}

		if (string.length() <= 2) {
			return parentString;
		}

		StringBundler sb = new StringBundler(3);

		sb.append(string.substring(0, string.length() - 1));
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append(parentString.substring(1));

		return sb.toString();
	}

	private Map<K, V> _parentMap;

}