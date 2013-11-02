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

package com.liferay.portal.json.transformer;

import com.liferay.portal.kernel.json.JSONSerializable;

import flexjson.JSONContext;

/**
 * @author Igor Spasic
 */
public class JSONSerializableJSONTransformer extends BaseJSONTransformer {

	@Override
	public void transform(Object object) {
		JSONSerializable jsonSerializable = (JSONSerializable)object;

		JSONContext jsonContext = getContext();

		jsonContext.write(jsonSerializable.toJSONString());
	}

}