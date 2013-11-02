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

package com.liferay.portal.test.persistence;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Miguel Pastor
 */
public class TransactionalPersistenceAdvice implements MethodInterceptor {

	public Map<Serializable, BasePersistence<?>> getBasePersistences() {
		return _basePersistences;
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		BaseModel<?> baseModel = (BaseModel<?>)methodInvocation.proceed();

		BasePersistence<?> basePersistence =
			(BasePersistence<?>)methodInvocation.getThis();

		_basePersistences.put(baseModel.getPrimaryKeyObj(), basePersistence);

		return baseModel;
	}

	public void reset() {
		_basePersistences.clear();
	}

	private Map<Serializable, BasePersistence<?>> _basePersistences =
		new HashMap<Serializable, BasePersistence<?>>();

}