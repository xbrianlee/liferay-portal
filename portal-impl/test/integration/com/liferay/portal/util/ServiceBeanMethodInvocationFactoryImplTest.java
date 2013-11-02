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

package com.liferay.portal.util;

import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ServiceBeanMethodInvocationFactoryUtil;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.persistence.EmailAddressUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import java.lang.reflect.Method;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 * @see    OrderByComparatorFactoryImplTest
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class ServiceBeanMethodInvocationFactoryImplTest {

	@After
	public void tearDown() throws Exception {
		for (EmailAddress emailAddress : _emailAddresses) {
			EmailAddressLocalServiceUtil.deleteEmailAddress(emailAddress);
		}

		_emailAddresses.clear();
	}

	@Test
	public void testRollback() throws Exception {
		EmailAddress emailAddress1 = newEmailAddress("abc@liferay.com");
		EmailAddress emailAddress2 = newEmailAddress("def@liferay.com");

		_emailAddresses.add(emailAddress1);
		_emailAddresses.add(emailAddress2);

		try {
			ServiceBeanMethodInvocationFactoryUtil.proceed(
				this, ServiceBeanMethodInvocationFactoryImplTest.class,
				getSaveMethod(), new Object[] {true},
				new String[] {"transactionAdvice"});

			Assert.fail();
		}
		catch (Exception e) {
		}

		Assert.assertEquals(
			0, EmailAddressLocalServiceUtil.getEmailAddressesCount());
	}

	@Test
	public void testSave() throws Exception {
		EmailAddress emailAddress1 = newEmailAddress("abc@liferay.com");
		EmailAddress emailAddress2 = newEmailAddress("def@liferay.com");

		_emailAddresses.add(emailAddress1);
		_emailAddresses.add(emailAddress2);

		ServiceBeanMethodInvocationFactoryUtil.proceed(
			this, ServiceBeanMethodInvocationFactoryImplTest.class,
			getSaveMethod(), new Object[] {false},
			new String[] {"transactionAdvice"});

		Assert.assertEquals(
			2, EmailAddressLocalServiceUtil.getEmailAddressesCount());
	}

	protected Method getSaveMethod() throws Exception {
		Class<?> clazz = getClass();

		return clazz.getDeclaredMethod("save", new Class<?>[] {boolean.class});
	}

	protected EmailAddress newEmailAddress(String address) throws Exception {
		long emailAddressId = ServiceTestUtil.nextLong();

		EmailAddress emailAddress = EmailAddressUtil.create(emailAddressId);

		emailAddress.setCompanyId(TestPropsValues.getCompanyId());
		emailAddress.setAddress(address);

		return emailAddress;
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {Exception.class}
	)
	protected void save(boolean rollback) throws Exception {
		for (EmailAddress emailAddress : _emailAddresses) {
			EmailAddressUtil.update(emailAddress);
		}

		if (rollback) {
			throw new Exception();
		}
	}

	private Set<EmailAddress> _emailAddresses = new HashSet<EmailAddress>();

}