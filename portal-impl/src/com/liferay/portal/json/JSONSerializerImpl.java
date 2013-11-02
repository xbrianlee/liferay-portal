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

import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.json.JSONTransformer;

import flexjson.transformer.Transformer;

/**
 * Wrapper over flexjson serializer.
 *
 * @author Igor Spasic
 */
public class JSONSerializerImpl implements JSONSerializer {

	public JSONSerializerImpl() {
		_jsonSerializer = new flexjson.JSONSerializer();
	}

	@Override
	public JSONSerializerImpl exclude(String... fields) {
		_jsonSerializer.exclude(fields);

		return this;
	}

	@Override
	public JSONSerializerImpl include(String... fields) {
		_jsonSerializer.include(fields);

		return this;
	}

	@Override
	public String serialize(Object target) {
		return _jsonSerializer.serialize(target);
	}

	@Override
	public String serializeDeep(Object target) {
		return _jsonSerializer.deepSerialize(target);
	}

	@Override
	public JSONSerializerImpl transform(
		JSONTransformer jsonTransformer, Class<?>... types) {

		Transformer transformer = null;

		if (jsonTransformer instanceof Transformer) {
			transformer = (Transformer)jsonTransformer;
		}
		else {
			transformer = new FlexjsonTransformer(jsonTransformer);
		}

		_jsonSerializer.transform(transformer, types);

		return this;
	}

	@Override
	public JSONSerializerImpl transform(
		JSONTransformer jsonTransformer, String... fields) {

		Transformer transformer = null;

		if (jsonTransformer instanceof Transformer) {
			transformer = (Transformer)jsonTransformer;
		}
		else {
			transformer = new FlexjsonTransformer(jsonTransformer);
		}

		_jsonSerializer.transform(transformer, fields);

		return this;
	}

	private final flexjson.JSONSerializer _jsonSerializer;

}