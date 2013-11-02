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

package com.liferay.portal.spring.transaction;

import com.liferay.portal.kernel.annotation.AnnotationLocator;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.MethodTargetClassKey;

import java.lang.reflect.Method;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;

/**
 * @author Shuyang Zhou
 */
public class AnnotationTransactionAttributeSource
	implements TransactionAttributeSource {

	@Override
	public TransactionAttribute getTransactionAttribute(
		Method method, Class<?> targetClass) {

		MethodTargetClassKey methodTargetClassKey = new MethodTargetClassKey(
			method, targetClass);

		TransactionAttribute transactionAttribute = _transactionAttributes.get(
			methodTargetClassKey);

		if (transactionAttribute != null) {
			if (transactionAttribute == _nullTransactionAttribute) {
				return null;
			}
			else {
				return transactionAttribute;
			}
		}

		Transactional transactional = AnnotationLocator.locate(
			method, targetClass, Transactional.class);

		transactionAttribute = TransactionAttributeBuilder.build(transactional);

		if (transactionAttribute == null) {
			_transactionAttributes.put(
				methodTargetClassKey, _nullTransactionAttribute);
		}
		else {
			_transactionAttributes.put(
				methodTargetClassKey, transactionAttribute);
		}

		return transactionAttribute;
	}

	private static TransactionAttribute _nullTransactionAttribute =
		new DefaultTransactionAttribute();

	private Map<MethodTargetClassKey, TransactionAttribute>
		_transactionAttributes =
			new ConcurrentHashMap<MethodTargetClassKey, TransactionAttribute>();

}