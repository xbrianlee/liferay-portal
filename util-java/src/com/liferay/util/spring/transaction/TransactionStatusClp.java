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

package com.liferay.util.spring.transaction;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;

/**
 * <p>
 * A class loader proxy implementation for a transaction status object created
 * by the transaction manager within the portal and serialized back and forth
 * between the portal and plugin class loader.
 * </p>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class TransactionStatusClp implements TransactionStatus {

	public TransactionStatusClp(Object remoteTransactionStatus) {
		_remoteTransactionStatus = remoteTransactionStatus;

		if (_remoteMethods == null) {
			initRemoteMethods(remoteTransactionStatus);
		}
	}

	@Override
	public Object createSavepoint() throws TransactionException {
		try {
			Method method = _remoteMethods.get("createSavepoint");

			return method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}
	}

	@Override
	public void flush() {
		try {
			Method method = _remoteMethods.get("flush");

			method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}
	}

	public Object getRemoteTransactionStatus() {
		return _remoteTransactionStatus;
	}

	@Override
	public boolean hasSavepoint() {
		try {
			Method method = _remoteMethods.get("hasSavepoint");

			return (Boolean)method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public boolean isCompleted() {
		try {
			Method method = _remoteMethods.get("isCompleted");

			return (Boolean)method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public boolean isNewTransaction() {
		try {
			Method method = _remoteMethods.get("isNewTransaction");

			return (Boolean)method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public boolean isRollbackOnly() {
		try {
			Method method = _remoteMethods.get("isRollbackOnly");

			return (Boolean)method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void releaseSavepoint(Object savepoint) throws TransactionException {
		try {
			Method method = _remoteMethods.get("releaseSavepoint");

			method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}
	}

	@Override
	public void rollbackToSavepoint(Object savepoint)
		throws TransactionException {

		try {
			Method method = _remoteMethods.get("rollbackToSavepoint");

			method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}
	}

	@Override
	public void setRollbackOnly() {
		try {
			Method method = _remoteMethods.get("setRollbackOnly");

			method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	protected void initRemoteMethods(Object remoteTransactionStatus) {
		_remoteMethods = new HashMap<String, Method>();

		Method[] methods = TransactionStatus.class.getMethods();

		for (Method method : methods) {
			_remoteMethods.put(method.getName(), method);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TransactionStatusClp.class);

	private static Map<String, Method> _remoteMethods;

	private Object _remoteTransactionStatus;

}