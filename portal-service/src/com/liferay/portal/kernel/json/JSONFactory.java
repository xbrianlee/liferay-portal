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

package com.liferay.portal.kernel.json;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public interface JSONFactory {

	public String convertJSONMLArrayToXML(String jsonml);

	public String convertJSONMLObjectToXML(String jsonml);

	public String convertXMLtoJSONMLArray(String xml);

	public String convertXMLtoJSONMLObject(String xml);

	public JSONTransformer createJavaScriptNormalizerJSONTransformer(
		List<String> javaScriptAttributes);

	public JSONArray createJSONArray();

	public JSONArray createJSONArray(String json) throws JSONException;

	public <T> JSONDeserializer<T> createJSONDeserializer();

	public JSONObject createJSONObject();

	public JSONObject createJSONObject(String json) throws JSONException;

	public JSONSerializer createJSONSerializer();

	public Object deserialize(JSONObject jsonObj);

	public Object deserialize(String json);

	public String getNullJSON();

	public JSONObject getUnmodifiableJSONObject();

	public Object looseDeserialize(String json);

	public <T> T looseDeserialize(String json, Class<T> clazz);

	public Object looseDeserializeSafe(String json);

	public <T> T looseDeserializeSafe(String json, Class<T> clazz);

	public String looseSerialize(Object object);

	public String looseSerialize(
		Object object, JSONTransformer jsonTransformer, Class<?> clazz);

	public String looseSerialize(Object object, String... includes);

	public String looseSerializeDeep(Object object);

	public String looseSerializeDeep(
		Object object, JSONTransformer jsonTransformer, Class<?> clazz);

	public String serialize(Object object);

	public String serializeException(Exception exception);

	public String serializeThrowable(Throwable throwable);

}