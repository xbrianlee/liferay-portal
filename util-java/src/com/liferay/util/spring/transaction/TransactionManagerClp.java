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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.SimplePojoClp;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * <p>
 * A transaction manager class loader proxy delegating the method invocations to
 * the transaction manager in the portal implementation. The transaction manager
 * within the portal must be specified as a Spring bean having ID
 * <code>"liferayTransactionManager"</code>.
 * </p>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class TransactionManagerClp implements PlatformTransactionManager {

	@Override
	public void commit(TransactionStatus transactionStatus)
		throws TransactionException {

		try {
			Method method = _transactionManagerMethods.get("commit");

			method.invoke(
				_transactionManager,
				unwrapTransactionStatus(transactionStatus));
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}
	}

	@Override
	public TransactionStatus getTransaction(
			TransactionDefinition transactionDefinition)
		throws TransactionException {

		Object transactionStatus = null;

		try {
			Method method = _transactionManagerMethods.get("getTransaction");

			transactionStatus = method.invoke(
				_transactionManager,
				createRemoteTransactionDefinition(transactionDefinition));
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}

		return new TransactionStatusClp(transactionStatus);
	}

	@PostConstruct
	public void init() throws ClassNotFoundException {
		_transactionManager = PortalBeanLocatorUtil.locate(
			"liferayTransactionManager");

		_transactionDefinitionClp = new SimplePojoClp<TransactionDefinition>(
			DefaultTransactionDefinition.class,
			PortalClassLoaderUtil.getClassLoader());

		initTransactionManagerMethods();
	}

	@Override
	public void rollback(TransactionStatus transactionStatus)
		throws TransactionException {

		try {
			Method method = _transactionManagerMethods.get("rollback");

			method.invoke(
				_transactionManager,
				unwrapTransactionStatus(transactionStatus));
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}
	}

	protected Object createRemoteTransactionDefinition(
			TransactionDefinition transactionDefinition)
		throws IllegalAccessException, InstantiationException {

		return _transactionDefinitionClp.createRemoteObject(
			transactionDefinition);
	}

	protected void initTransactionManagerMethods() {
		_transactionManagerMethods = new HashMap<String, Method>();

		Method[] methods = _transactionManager.getClass().getMethods();

		for (Method method : methods) {
			_transactionManagerMethods.put(method.getName(), method);
		}
	}

	protected Object unwrapTransactionStatus(
		TransactionStatus localTransactionStatus) {

		TransactionStatusClp transactionStatusClp =
			(TransactionStatusClp)localTransactionStatus;

		return transactionStatusClp.getRemoteTransactionStatus();
	}

	private static Log _log = LogFactoryUtil.getLog(
		TransactionManagerClp.class);

	private SimplePojoClp<TransactionDefinition> _transactionDefinitionClp;
	private Object _transactionManager;
	private Map<String, Method> _transactionManagerMethods;

}