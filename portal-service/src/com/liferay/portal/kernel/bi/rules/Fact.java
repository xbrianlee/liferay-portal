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

package com.liferay.portal.kernel.bi.rules;

import java.io.Serializable;

/**
 * @author Michael C. Han
 */
public class Fact<T> implements Serializable {

	public Fact(String identifier, T object) {
		_identifier = identifier;
		_factObject = object;
	}

	public T getFactObject() {
		return _factObject;
	}

	public String getIdentifier() {
		return _identifier;
	}

	private T _factObject;
	private String _identifier;

}