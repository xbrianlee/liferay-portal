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

package com.liferay.portal.kernel.messaging.proxy;

import com.liferay.portal.kernel.annotation.AnnotationLocator;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Micha Kiener
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ProxyRequest implements Externalizable {

	/**
	 * The empty constructor is required by {@link java.io.Externalizable}. Do
	 * not use this for any other purpose.
	 */
	public ProxyRequest() {
	}

	public ProxyRequest(Method method, Object[] arguments) throws Exception {
		_method = method;
		_arguments = arguments;

		if (method.getReturnType() != Void.TYPE) {
			_hasReturnValue = true;
		}

		boolean[] localAndSynchronous = _localAndSynchronousMap.get(method);

		if (localAndSynchronous == null) {
			localAndSynchronous = new boolean[2];

			MessagingProxy messagingProxy = AnnotationLocator.locate(
				method, method.getDeclaringClass(), MessagingProxy.class);

			if (messagingProxy != null) {
				if (messagingProxy.local()) {
					localAndSynchronous[0] = true;
				}

				ProxyMode proxyMode = messagingProxy.mode();

				if (proxyMode.equals(ProxyMode.SYNC)) {
					localAndSynchronous[1] = true;
				}
			}

			_localAndSynchronousMap.put(method, localAndSynchronous);
		}

		_local = localAndSynchronous[0];
		_synchronous = localAndSynchronous[1];
	}

	public Object execute(Object object) throws Exception {
		try {
			return _method.invoke(object, _arguments);
		}
		catch (InvocationTargetException ite) {
			Throwable t = ite.getCause();

			if (t instanceof Exception) {
				throw (Exception)t;
			}
			else {
				throw new Exception(t);
			}
		}
	}

	public Object[] getArguments() {
		return _arguments;
	}

	public boolean hasReturnValue() {
		return _hasReturnValue;
	}

	public boolean isLocal() {
		return _local;
	}

	public boolean isSynchronous() {
		return _synchronous;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		_arguments = (Object[])objectInput.readObject();
		_hasReturnValue = objectInput.readBoolean();

		MethodKey methodKey = (MethodKey)objectInput.readObject();

		try {
			_method = methodKey.getMethod();
		}
		catch (NoSuchMethodException nsme) {
			throw new IOException(nsme);
		}

		_synchronous = objectInput.readBoolean();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{arguments=");
		sb.append(Arrays.toString(_arguments));
		sb.append(", hasReturnValue=");
		sb.append(_hasReturnValue);
		sb.append(", method=");
		sb.append(_method);
		sb.append(", synchronous");
		sb.append(_synchronous);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeObject(_arguments);
		objectOutput.writeBoolean(_hasReturnValue);
		objectOutput.writeObject(new MethodKey(_method));
		objectOutput.writeBoolean(_synchronous);
	}

	private static Map<Method, boolean[]> _localAndSynchronousMap =
		new ConcurrentHashMap<Method, boolean[]>();

	private Object[] _arguments;
	private boolean _hasReturnValue;
	private boolean _local;
	private Method _method;
	private boolean _synchronous;

}