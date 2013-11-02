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

/**
 * @author Shuyang Zhou
 */
public class ServiceBeanMatcher implements BeanMatcher {

	public ServiceBeanMatcher() {
		this(false);
	}

	public ServiceBeanMatcher(boolean counterMatcher) {
		_counterMatcher = counterMatcher;
	}

	@Override
	public boolean match(Class<?> beanClass, String beanName) {
		if (_counterMatcher) {
			return beanName.equals(_COUNTER_SERVICE_BEAN_NAME);
		}
		else if (!beanName.equals(_COUNTER_SERVICE_BEAN_NAME) &&
				 beanName.endsWith(_SERVICE_SUFFIX)) {

			return true;
		}

		return false;
	}

	private static final String _COUNTER_SERVICE_BEAN_NAME =
		"com.liferay.counter.service.CounterLocalService";

	private static final String _SERVICE_SUFFIX = "Service";

	private boolean _counterMatcher;

}