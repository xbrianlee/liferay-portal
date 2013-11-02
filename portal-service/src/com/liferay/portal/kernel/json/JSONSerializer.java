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

/**
 * @author Igor Spasic
 */
public interface JSONSerializer {

	public JSONSerializer exclude(String... fields);

	public JSONSerializer include(String... fields);

	public String serialize(Object target);

	public String serializeDeep(Object target);

	public JSONSerializer transform(
		JSONTransformer jsonTransformer, Class<?>... types);

	public JSONSerializer transform(
		JSONTransformer jsonTransformer, String... fields);

}