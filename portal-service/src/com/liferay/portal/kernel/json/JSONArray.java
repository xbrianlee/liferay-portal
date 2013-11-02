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

import java.io.Writer;

/**
 * @author Brian Wing Shun Chan
 */
public interface JSONArray {

	public boolean getBoolean(int index);

	public double getDouble(int index);

	public int getInt(int index);

	public JSONArray getJSONArray(int index);

	public JSONObject getJSONObject(int index);

	public long getLong(int index);

	public String getString(int index);

	public boolean isNull(int index);

	public String join(String separator) throws JSONException;

	public int length();

	public JSONArray put(boolean value);

	public JSONArray put(double value);

	public JSONArray put(int value);

	public JSONArray put(JSONArray value);

	public JSONArray put(JSONObject value);

	public JSONArray put(long value);

	public JSONArray put(String value);

	@Override
	public String toString();

	public String toString(int indentFactor) throws JSONException;

	public Writer write(Writer writer) throws JSONException;

}