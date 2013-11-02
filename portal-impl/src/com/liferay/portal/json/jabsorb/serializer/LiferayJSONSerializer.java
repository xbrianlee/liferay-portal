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

package com.liferay.portal.json.jabsorb.serializer;

import org.jabsorb.JSONSerializer;
import org.jabsorb.serializer.ObjectMatch;
import org.jabsorb.serializer.SerializerState;
import org.jabsorb.serializer.UnmarshallException;

/**
 * @author Tomas Polesovsky
 */
public class LiferayJSONSerializer extends JSONSerializer {

	@Override
	public ObjectMatch tryUnmarshall(
			SerializerState serializerState,
			@SuppressWarnings("rawtypes") Class clazz, Object json)
		throws UnmarshallException {

		if (!(serializerState instanceof LiferaySerializerState)) {
			serializerState = new LiferaySerializerState();
		}

		return super.tryUnmarshall(serializerState, clazz, json);
	}

	@Override
	public Object unmarshall(
			SerializerState serializerState,
			@SuppressWarnings("rawtypes") Class clazz, Object json)
		throws UnmarshallException {

		if (!(serializerState instanceof LiferaySerializerState)) {
			serializerState = new LiferaySerializerState();
		}

		return super.unmarshall(serializerState, clazz, json);
	}

}