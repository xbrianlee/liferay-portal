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

package com.liferay.portal.resiliency.service;

import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class ServiceMethodProcessCallable
	implements Externalizable, ProcessCallable<Serializable> {

	/**
	 * The empty constructor is required by {@link java.io.Externalizable}. Do
	 * not use this for any other purpose.
	 */
	public ServiceMethodProcessCallable() {
	}

	public ServiceMethodProcessCallable(MethodHandler methodHandler) {
		_methodHandler = methodHandler;

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker != null) {
			_userId = permissionChecker.getUserId();
		}
	}

	@Override
	public Serializable call() throws ProcessException {
		String oldName = PrincipalThreadLocal.getName();
		PermissionChecker oldPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			if (_userId != 0) {
				PrincipalThreadLocal.setName(_userId);

				User user = UserLocalServiceUtil.fetchUser(_userId);

				if (user != null) {
					PermissionChecker permissionChecker =
						PermissionCheckerFactoryUtil.create(user);

					PermissionThreadLocal.setPermissionChecker(
						permissionChecker);
				}
			}

			return (Serializable)_methodHandler.invoke(false);
		}
		catch (Exception e) {
			throw new ProcessException(e);
		}
		finally {
			PrincipalThreadLocal.setName(oldName);
			PermissionThreadLocal.setPermissionChecker(oldPermissionChecker);
		}
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		_methodHandler = (MethodHandler)objectInput.readObject();
		_userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeObject(_methodHandler);
		objectOutput.writeLong(_userId);
	}

	private MethodHandler _methodHandler;
	private long _userId;

}