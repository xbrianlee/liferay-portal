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

package com.liferay.portal.tools.seleniumbuilder;

import com.liferay.portal.kernel.util.StringPool;

import java.util.Stack;

/**
 * @author Michael Hashimoto
 */
public class FreeMarkerStack {

	public boolean empty() {
		return _stack.empty();
	}

	public Object peek() {
		return _stack.peek();
	}

	public Object pop() {
		return _stack.pop();
	}

	public Object push(Object object) {
		_stack.push(object);

		return StringPool.BLANK;
	}

	private Stack<Object> _stack = new Stack<Object>();

}