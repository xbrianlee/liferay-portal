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

package com.liferay.portal.upgrade.util;

import com.liferay.portal.kernel.upgrade.StagnantRowException;
import com.liferay.portal.kernel.upgrade.util.ValueMapper;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Alexander Chow
 * @author Brian Wing Shun Chan
 */
public class MemoryValueMapper implements ValueMapper {

	public MemoryValueMapper() {
		this(new LinkedHashSet<Object>());
	}

	public MemoryValueMapper(Set<Object> exceptions) {
		_map = new LinkedHashMap<Object, Object>();
		_exceptions = exceptions;
	}

	@Override
	public void appendException(Object exception) {
		_exceptions.add(exception);
	}

	public Map<Object, Object> getMap() {
		return _map;
	}

	@Override
	public Object getNewValue(Object oldValue) throws Exception {
		Object value = _map.get(oldValue);

		if (value == null) {
			if (_exceptions.contains(oldValue)) {
				value = oldValue;
			}
			else {
				throw new StagnantRowException(String.valueOf(oldValue));
			}
		}

		return value;
	}

	@Override
	public Iterator<Object> iterator() throws Exception {
		return _map.keySet().iterator();
	}

	@Override
	public void mapValue(Object oldValue, Object newValue) throws Exception {
		_map.put(oldValue, newValue);
	}

	@Override
	public int size() throws Exception {
		return _map.size();
	}

	private Set<Object> _exceptions;
	private Map<Object, Object> _map;

}