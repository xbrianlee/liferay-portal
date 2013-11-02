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

import com.liferay.portal.json.transformer.FlexjsonObjectJSONTransformer;
import com.liferay.portal.json.transformer.JSONArrayJSONTransformer;
import com.liferay.portal.json.transformer.JSONObjectJSONTransformer;
import com.liferay.portal.json.transformer.JSONSerializableJSONTransformer;
import com.liferay.portal.json.transformer.RepositoryModelJSONTransformer;
import com.liferay.portal.json.transformer.UserJSONTransformer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializable;
import com.liferay.portal.kernel.repository.model.RepositoryModel;
import com.liferay.portal.model.User;

import flexjson.TransformerUtil;

import flexjson.transformer.NullTransformer;
import flexjson.transformer.Transformer;
import flexjson.transformer.TransformerWrapper;
import flexjson.transformer.TypeTransformerMap;

import java.io.InputStream;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.Map;

/**
 * @author Igor Spasic
 */
public class JSONInit {

	@SuppressWarnings("rawtypes")
	public static synchronized void init() {
		try {
			if (_initalized) {
				return;
			}

			Field defaultTransformersField =
				TransformerUtil.class.getDeclaredField("defaultTransformers");

			defaultTransformersField.setAccessible(true);

			TypeTransformerMap oldTransformersMap =
				TransformerUtil.getDefaultTypeTransformers();

			TypeTransformerMap newTransformersMap = new TypeTransformerMap();

			for (Map.Entry<Class, Transformer> entry :
					oldTransformersMap.entrySet()) {

				newTransformersMap.put(entry.getKey(), entry.getValue());
			}

			_registerDefaultTransformers(newTransformersMap);

			Field modifiersField = Field.class.getDeclaredField("modifiers");

			modifiersField.setAccessible(true);

			modifiersField.setInt(
				defaultTransformersField,
				defaultTransformersField.getModifiers() & ~Modifier.FINAL);

			defaultTransformersField.set(null, newTransformersMap);

			_initalized = true;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void _registerDefaultTransformers(
		TypeTransformerMap transformersMap) {

		transformersMap.put(
			InputStream.class, new TransformerWrapper(new NullTransformer()));

		transformersMap.put(
			JSONArray.class,
			new TransformerWrapper(new JSONArrayJSONTransformer()));

		transformersMap.put(
			JSONObject.class,
			new TransformerWrapper(new JSONObjectJSONTransformer()));

		transformersMap.put(
			JSONSerializable.class,
			new TransformerWrapper(new JSONSerializableJSONTransformer()));

		transformersMap.put(
			Object.class,
			new TransformerWrapper(new FlexjsonObjectJSONTransformer()));

		transformersMap.put(
			RepositoryModel.class,
			new TransformerWrapper(new RepositoryModelJSONTransformer()));

		transformersMap.put(
			User.class, new TransformerWrapper(new UserJSONTransformer()));
	}

	private static boolean _initalized = false;

}