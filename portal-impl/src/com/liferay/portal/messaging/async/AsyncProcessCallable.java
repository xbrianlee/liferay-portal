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

import com.liferay.portal.bean.IdentifiableBeanInvokerUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.util.MethodHandler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Shuyang Zhou
 */
public class AsyncProcessCallable
	implements Externalizable, ProcessCallable<Serializable> {

	public AsyncProcessCallable() {
	}

	public AsyncProcessCallable(MethodInvocation methodInvocation) {
		_methodInvocation = methodInvocation;
	}

	@Override
	public Serializable call() {
		try {
			if (_methodInvocation != null) {
				_methodInvocation.proceed();
			}
			else {
				AsyncInvokeThreadLocal.setEnabled(true);

				try {
					_methodHandler.invoke(null);
				}
				finally {
					AsyncInvokeThreadLocal.setEnabled(false);
				}
			}
		}
		catch (Throwable t) {
			throw new RuntimeException(t);
		}

		return null;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		_methodHandler = (MethodHandler)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		MethodHandler methodHandler = _methodHandler;

		if (methodHandler == null) {
			methodHandler = IdentifiableBeanInvokerUtil.createMethodHandler(
				_methodInvocation);
		}

		objectOutput.writeObject(methodHandler);
	}

	private MethodHandler _methodHandler;
	private MethodInvocation _methodInvocation;

}