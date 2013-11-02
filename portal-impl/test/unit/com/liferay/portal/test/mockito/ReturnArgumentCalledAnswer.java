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

package com.liferay.portal.test.mockito;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author Miguel Pastor
 */
public class ReturnArgumentCalledAnswer<T> implements Answer<T> {

	public ReturnArgumentCalledAnswer(int position) {
		_position = position;
	}

	@Override
	public T answer(InvocationOnMock invocationOnMock) {
		return (T)invocationOnMock.getArguments()[_position];
	}

	private int _position;

}