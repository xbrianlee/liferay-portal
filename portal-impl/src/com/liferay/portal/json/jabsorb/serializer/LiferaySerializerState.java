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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.jabsorb.serializer.ProcessedObject;
import org.jabsorb.serializer.SerializerState;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Tomas Polesovsky
 */
public class LiferaySerializerState extends SerializerState {

	@Override
	public ProcessedObject store(Object object) {
		if (!(object instanceof JSONObject)) {
			return super.store(object);
		}

		JSONObject jsonObject = (JSONObject)object;

		if (jsonObject.has("javaClass")) {
			try {
				String javaClass = jsonObject.getString("javaClass");

				if (javaClass.contains("com.liferay") &&
					javaClass.contains("Util")) {

					throw new RuntimeException(
						"Not instantiating " + javaClass);
				}
			}
			catch (JSONException jsone) {
				_log.error("Unable to parse object", jsone);
			}
		}

		return super.store(object);
	}

	private static Log _log = LogFactoryUtil.getLog(
		LiferaySerializerState.class);

}