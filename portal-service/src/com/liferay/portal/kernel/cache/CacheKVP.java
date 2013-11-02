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

package com.liferay.portal.kernel.cache;

import java.io.Serializable;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of 6.1.0
 */
@Deprecated
public class CacheKVP implements Serializable {

	public CacheKVP(Class<?> modelClass, Serializable primaryKeyObj) {
		_modelClass = modelClass;
		_primaryKeyObj = primaryKeyObj;
	}

	public Class<?> getModelClass() {
		return _modelClass;
	}

	public Serializable getPrimaryKeyObj() {
		return _primaryKeyObj;
	}

	private Class<?> _modelClass;
	private Serializable _primaryKeyObj;

}