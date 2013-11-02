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

package com.liferay.portal.spring.annotation;

import com.liferay.portal.kernel.annotation.AnnotationLocator;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.spring.transaction.TransactionAttributeBuilder;

import java.io.Serializable;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import org.springframework.transaction.annotation.TransactionAnnotationParser;
import org.springframework.transaction.interceptor.TransactionAttribute;

/**
 * @author     Michael Young
 * @author     Shuyang Zhou
 * @deprecated As of 6.1.0
 */
public class PortalTransactionAnnotationParser
	implements TransactionAnnotationParser, Serializable {

	@Override
	public TransactionAttribute parseTransactionAnnotation(
		AnnotatedElement annotatedElement) {

		Transactional transactional = null;

		if (annotatedElement instanceof Method) {
			Method method = (Method)annotatedElement;

			transactional = AnnotationLocator.locate(
				method, method.getDeclaringClass(), Transactional.class);
		}
		else {
			transactional = AnnotationLocator.locate(
				(Class<?>)annotatedElement, Transactional.class);
		}

		return TransactionAttributeBuilder.build(transactional);
	}

}