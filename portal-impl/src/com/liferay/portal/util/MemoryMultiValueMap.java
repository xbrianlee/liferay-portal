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

import com.liferay.portal.kernel.util.MultiValueMap;

import java.io.Serializable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexander Chow
 */
public class MemoryMultiValueMap<K extends Serializable, V extends Serializable>
	extends MultiValueMap<K, V> {

	@Override
	public void clear() {
		_map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return _map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		for (Map.Entry<K, Set<V>> entry : _map.entrySet()) {
			Set<V> values = entry.getValue();

			if (values.contains(value)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Set<V> getAll(Object key) {
		return _map.get(key);
	}

	@Override
	public boolean isEmpty() {
		return _map.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return _map.keySet();
	}

	@Override
	public V put(K key, V value) {
		Set<V> values = _map.get(key);

		if (values == null) {
			values = new HashSet<V>();
		}

		values.add(value);

		_map.put(key, values);

		return value;
	}

	@Override
	public Set<V> putAll(K key, Collection<? extends V> values) {
		Set<V> oldValues = _map.get(key);

		if (oldValues == null) {
			oldValues = new HashSet<V>();
		}

		oldValues.addAll(values);

		_map.put(key, oldValues);

		return oldValues;
	}

	@Override
	public V remove(Object key) {
		V value = null;

		Set<V> values = _map.remove(key);

		if ((values != null) && !values.isEmpty()) {
			value = values.iterator().next();
		}

		return value;
	}

	private Map<K, Set<V>> _map = new HashMap<K, Set<V>>();

}