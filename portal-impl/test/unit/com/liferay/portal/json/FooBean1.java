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

import com.liferay.portal.kernel.json.JSON;

import java.util.Collection;

/**
 * @author Igor Spasic
 */
public class FooBean1 extends FooBean {

	@JSON
	@Override
	public Collection<Object> getCollection() {
		return super.getCollection();
	}

	@JSON(include = false)
	@Override
	public String getName() {
		return super.getName();
	}

}