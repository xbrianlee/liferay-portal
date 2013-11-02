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

package com.liferay.portal.messaging.proxy;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ClassResolverUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.aop.BeanMatcher;

/**
 * @author Shuyang Zhou
 */
public class MessagingProxyBeanMatcher implements BeanMatcher {

	@Override
	public boolean match(Class<?> beanClass, String beanName) {
		if (_beanClass.isAssignableFrom(beanClass) &&
			StringUtil.wildcardMatches(
				beanName, _beanNamePattern, CharPool.QUESTION, CharPool.STAR,
				CharPool.PERCENT, true)) {

			return true;
		}

		return false;
	}

	public void setBeanClass(String beanClassName) {
		_beanClass = ClassResolverUtil.resolveByPortalClassLoader(
			beanClassName);
	}

	public void setBeanNamePattern(String beanNamePattern) {
		_beanNamePattern = beanNamePattern;
	}

	private Class<?> _beanClass;
	private String _beanNamePattern;

}