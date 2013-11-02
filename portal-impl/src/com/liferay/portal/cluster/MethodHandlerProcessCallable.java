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

package com.liferay.portal.cluster;

import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.util.MethodHandler;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class MethodHandlerProcessCallable<T extends Serializable>
	implements ProcessCallable<T> {

	public MethodHandlerProcessCallable(MethodHandler methodHandler) {
		_methodHandler = methodHandler;
	}

	@Override
	public T call() throws ProcessException {
		try {
			return (T)_methodHandler.invoke(false);
		}
		catch (Exception e) {
			throw new ProcessException(e);
		}
	}

	private static final long serialVersionUID = 1L;

	private MethodHandler _methodHandler;

}