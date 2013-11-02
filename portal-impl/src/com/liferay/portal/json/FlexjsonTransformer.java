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

import com.liferay.portal.kernel.json.JSONTransformer;

import flexjson.transformer.Transformer;

/**
 * @author Brian Wing Shun Chan
 */
public class FlexjsonTransformer implements Transformer {

	public FlexjsonTransformer(JSONTransformer jsonTransformer) {
		_jsonTransformer = jsonTransformer;
	}

	@Override
	public void transform(Object object) {
		_jsonTransformer.transform(object);
	}

	private JSONTransformer _jsonTransformer;

}