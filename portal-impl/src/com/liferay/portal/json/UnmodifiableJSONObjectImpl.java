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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class UnmodifiableJSONObjectImpl extends JSONObjectImpl {

	@Override
	public Iterator<String> keys() {
		List<String> list = Collections.emptyList();

		return list.iterator();
	}

	@Override
	public JSONObject put(String key, boolean value) {
		if (_log.isWarnEnabled()) {
			_log.warn("Modifications are unsupported");
		}

		return this;
	}

	@Override
	public JSONObject put(String key, Date value) {
		if (_log.isWarnEnabled()) {
			_log.warn("Modifications are unsupported");
		}

		return this;
	}

	@Override
	public JSONObject put(String key, double value) {
		if (_log.isWarnEnabled()) {
			_log.warn("Modifications are unsupported");
		}

		return this;
	}

	@Override
	public JSONObject put(String key, int value) {
		if (_log.isWarnEnabled()) {
			_log.warn("Modifications are unsupported");
		}

		return this;
	}

	@Override
	public JSONObject put(String key, JSONArray value) {
		if (_log.isWarnEnabled()) {
			_log.warn("Modifications are unsupported");
		}

		return this;
	}

	@Override
	public JSONObject put(String key, JSONObject value) {
		if (_log.isWarnEnabled()) {
			_log.warn("Modifications are unsupported");
		}

		return this;
	}

	@Override
	public JSONObject put(String key, long value) {
		if (_log.isWarnEnabled()) {
			_log.warn("Modifications are unsupported");
		}

		return this;
	}

	@Override
	public JSONObject put(String key, String value) {
		if (_log.isWarnEnabled()) {
			_log.warn("Modifications are unsupported");
		}

		return this;
	}

	@Override
	public JSONObject putException(Exception exception) {
		if (_log.isWarnEnabled()) {
			_log.warn("Modifications are unsupported");
		}

		return this;
	}

	@Override
	public Object remove(String key) {
		if (_log.isWarnEnabled()) {
			_log.warn("Modifications are unsupported");
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(
		UnmodifiableJSONObjectImpl.class);

}