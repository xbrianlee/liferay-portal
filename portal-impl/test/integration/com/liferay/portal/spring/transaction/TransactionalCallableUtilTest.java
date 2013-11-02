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

package com.liferay.portal.spring.transaction;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.persistence.ClassNameUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.util.PwdGenerator;

import java.util.concurrent.Callable;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.transaction.interceptor.TransactionAttribute;

/**
 * @author Shuyang Zhou
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class TransactionalCallableUtilTest {

	@Test
	public void testCommit() throws Throwable {
		final long classNameId = CounterLocalServiceUtil.increment();
		final String classNameValue = PwdGenerator.getPassword();

		try {
			TransactionalCallableUtil.call(
				_transactionAttribute, new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					ClassName className = ClassNameUtil.create(classNameId);

					className.setValue(classNameValue);

					ClassNameUtil.update(className);

					return null;
				}

			});

			ClassName className = ClassNameLocalServiceUtil.fetchClassName(
				classNameId);

			Assert.assertNotNull(className);
			Assert.assertEquals(classNameValue, className.getClassName());
		}
		finally {
			ClassNameLocalServiceUtil.deleteClassName(classNameId);
		}
	}

	@Test
	public void testRollback() throws Throwable {
		final long classNameId = CounterLocalServiceUtil.increment();
		final Exception exception = new Exception();

		try {
			TransactionalCallableUtil.call(
				_transactionAttribute, new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					ClassName className = ClassNameUtil.create(classNameId);

					className.setValue(PwdGenerator.getPassword());

					ClassNameUtil.update(className);

					throw exception;
				}

			});

			Assert.fail();
		}
		catch (Throwable throwable) {
			Assert.assertSame(exception, throwable);

			ClassName className = ClassNameLocalServiceUtil.fetchClassName(
				classNameId);

			Assert.assertNull(className);
		}
		finally {
			try {
				ClassNameLocalServiceUtil.deleteClassName(classNameId);
			}
			catch (Exception e) {
			}
		}
	}

	private TransactionAttribute _transactionAttribute =
		TransactionAttributeBuilder.build(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

}