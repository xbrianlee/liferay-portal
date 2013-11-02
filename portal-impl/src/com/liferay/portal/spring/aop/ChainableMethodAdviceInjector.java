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

package com.liferay.portal.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author Shuyang Zhou
 */
public class ChainableMethodAdviceInjector {

	public void inject() {
		if (!_injectCondition) {
			return;
		}

		_injectCondition = false;

		if (_newChainableMethodAdvice == null) {
			throw new IllegalArgumentException(
				"New Chainable method advice is null");
		}

		if (_parentChainableMethodAdvice == null) {
			throw new IllegalArgumentException(
				"Parent chainable method advice is null");
		}

		if (_childMethodInterceptor == null) {
			_newChainableMethodAdvice.nextMethodInterceptor =
				_parentChainableMethodAdvice.nextMethodInterceptor;
			_parentChainableMethodAdvice.nextMethodInterceptor =
				_newChainableMethodAdvice;

			return;
		}

		ChainableMethodAdvice parentChainableMethodAdvice =
			_parentChainableMethodAdvice;

		while ((parentChainableMethodAdvice != null) &&
			   (parentChainableMethodAdvice.nextMethodInterceptor !=
					_childMethodInterceptor)) {

			MethodInterceptor methodInterceptor =
				parentChainableMethodAdvice.nextMethodInterceptor;

			if (!(methodInterceptor instanceof ChainableMethodAdvice)) {
				break;
			}

			parentChainableMethodAdvice =
				(ChainableMethodAdvice)methodInterceptor;
		}

		if (parentChainableMethodAdvice.nextMethodInterceptor !=
				_childMethodInterceptor) {

			throw new IllegalArgumentException(
				"Unable to find " + _childMethodInterceptor + " from " +
					_parentChainableMethodAdvice);
		}

		_newChainableMethodAdvice.nextMethodInterceptor =
			parentChainableMethodAdvice.nextMethodInterceptor;

		parentChainableMethodAdvice.nextMethodInterceptor =
			_newChainableMethodAdvice;
	}

	public void setChildMethodInterceptor(
		MethodInterceptor childMethodInterceptor) {

		_childMethodInterceptor = childMethodInterceptor;
	}

	public void setInjectCondition(boolean injectCondition) {
		_injectCondition = injectCondition;
	}

	public void setNewChainableMethodAdvice(
		ChainableMethodAdvice newChainableMethodAdvice) {

		_newChainableMethodAdvice = newChainableMethodAdvice;
	}

	public void setParentChainableMethodAdvice(
		ChainableMethodAdvice parentChainableMethodAdvice) {

		_parentChainableMethodAdvice = parentChainableMethodAdvice;
	}

	private MethodInterceptor _childMethodInterceptor;
	private boolean _injectCondition;
	private ChainableMethodAdvice _newChainableMethodAdvice;
	private ChainableMethodAdvice _parentChainableMethodAdvice;

}