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

package com.liferay.portal.messaging.async;

import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class AsyncAdviceConfigurator {

	public void afterPropertiesSet() {
		if (_asyncAdvice == null) {
			throw new IllegalArgumentException("Async advice is null");
		}

		if (_defaultDestinationName == null) {
			throw new IllegalArgumentException(
				"Default destination name is null");
		}

		_asyncAdvice.setDefaultDestinationName(_defaultDestinationName);

		if (_destinationNames != null) {
			_asyncAdvice.setDestinationNames(_destinationNames);
		}
	}

	public void setAsyncAdvice(AsyncAdvice asyncAdvice) {
		_asyncAdvice = asyncAdvice;
	}

	public void setDefaultDestinationName(String defaultDestinationName) {
		_defaultDestinationName = defaultDestinationName;
	}

	public void setDestinationNames(Map<Class<?>, String> destinationNames) {
		_destinationNames = destinationNames;
	}

	private AsyncAdvice _asyncAdvice;
	private String _defaultDestinationName;
	private Map<Class<?>, String> _destinationNames;

}