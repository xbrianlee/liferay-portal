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

package com.liferay.portal.kernel.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Shuyang Zhou
 */
public class PersistentHttpServletRequestWrapper
	extends HttpServletRequestWrapper implements Cloneable {

	public PersistentHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public PersistentHttpServletRequestWrapper clone() {
		try {
			return (PersistentHttpServletRequestWrapper)super.clone();
		}
		catch (CloneNotSupportedException cnse) {
			throw new RuntimeException(cnse);
		}
	}

}