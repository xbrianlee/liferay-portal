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

import flexjson.JSONContext;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author Igor Spasic
 */
public class SortedHashMapJSONTransformer extends BaseJSONTransformer {

	@Override
	public void transform(Object object) {
		if (object instanceof HashMap) {
			HashMap<Object, Object> hashMap = (HashMap<Object, Object>)object;

			TreeMap<Object, Object> treeMap = new TreeMap<Object, Object>();

			treeMap.putAll(hashMap);

			object = treeMap;
		}

		JSONContext jsonContext = getContext();

		jsonContext.transform(object);
	}

}