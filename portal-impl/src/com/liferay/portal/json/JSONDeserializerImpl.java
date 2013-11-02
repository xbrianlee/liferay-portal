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

package com.liferay.portal.json;

import com.liferay.portal.kernel.json.JSONDeserializer;

import java.io.Reader;

/**
 * @author Brian Wing Shun Chan
 */
public class JSONDeserializerImpl<T> implements JSONDeserializer<T> {

	public JSONDeserializerImpl() {
		_jsonDeserializer = new flexjson.JSONDeserializer<T>();

		_portalBeanObjectFactory = new PortalBeanObjectFactory();

		_jsonDeserializer.use(Object.class, _portalBeanObjectFactory);
	}

	@Override
	public T deserialize(Reader input) {
		return _jsonDeserializer.deserialize(input);
	}

	@Override
	public T deserialize(String input) {
		return _jsonDeserializer.deserialize(input);
	}

	@Override
	public JSONDeserializer<T> safeMode(boolean safeMode) {
		_portalBeanObjectFactory.setSafeMode(safeMode);

		return this;
	}

	@Override
	public JSONDeserializer<T> use(String path, Class<?> clazz) {
		_jsonDeserializer.use(path, clazz);

		return this;
	}

	private flexjson.JSONDeserializer<T> _jsonDeserializer;
	private PortalBeanObjectFactory _portalBeanObjectFactory;

}