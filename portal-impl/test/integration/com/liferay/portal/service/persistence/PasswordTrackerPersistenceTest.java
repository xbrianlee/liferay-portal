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

package com.liferay.portal.service.persistence;

import com.liferay.portal.NoSuchPasswordTrackerException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.model.PasswordTracker;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.persistence.TransactionalPersistenceAdvice;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
@ExecutionTestListeners(listeners =  {
	PersistenceExecutionTestListener.class})
@RunWith(LiferayPersistenceIntegrationJUnitTestRunner.class)
public class PasswordTrackerPersistenceTest {
	@After
	public void tearDown() throws Exception {
		Map<Serializable, BasePersistence<?>> basePersistences = _transactionalPersistenceAdvice.getBasePersistences();

		Set<Serializable> primaryKeys = basePersistences.keySet();

		for (Serializable primaryKey : primaryKeys) {
			BasePersistence<?> basePersistence = basePersistences.get(primaryKey);

			try {
				basePersistence.remove(primaryKey);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug("The model with primary key " + primaryKey +
						" was already deleted");
				}
			}
		}

		_transactionalPersistenceAdvice.reset();
	}

	@Test
	public void testCreate() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		PasswordTracker passwordTracker = _persistence.create(pk);

		Assert.assertNotNull(passwordTracker);

		Assert.assertEquals(passwordTracker.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		PasswordTracker newPasswordTracker = addPasswordTracker();

		_persistence.remove(newPasswordTracker);

		PasswordTracker existingPasswordTracker = _persistence.fetchByPrimaryKey(newPasswordTracker.getPrimaryKey());

		Assert.assertNull(existingPasswordTracker);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPasswordTracker();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		PasswordTracker newPasswordTracker = _persistence.create(pk);

		newPasswordTracker.setUserId(ServiceTestUtil.nextLong());

		newPasswordTracker.setCreateDate(ServiceTestUtil.nextDate());

		newPasswordTracker.setPassword(ServiceTestUtil.randomString());

		_persistence.update(newPasswordTracker);

		PasswordTracker existingPasswordTracker = _persistence.findByPrimaryKey(newPasswordTracker.getPrimaryKey());

		Assert.assertEquals(existingPasswordTracker.getPasswordTrackerId(),
			newPasswordTracker.getPasswordTrackerId());
		Assert.assertEquals(existingPasswordTracker.getUserId(),
			newPasswordTracker.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingPasswordTracker.getCreateDate()),
			Time.getShortTimestamp(newPasswordTracker.getCreateDate()));
		Assert.assertEquals(existingPasswordTracker.getPassword(),
			newPasswordTracker.getPassword());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		PasswordTracker newPasswordTracker = addPasswordTracker();

		PasswordTracker existingPasswordTracker = _persistence.findByPrimaryKey(newPasswordTracker.getPrimaryKey());

		Assert.assertEquals(existingPasswordTracker, newPasswordTracker);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchPasswordTrackerException");
		}
		catch (NoSuchPasswordTrackerException nsee) {
		}
	}

	@Test
	public void testFindAll() throws Exception {
		try {
			_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				getOrderByComparator());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	protected OrderByComparator getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PasswordTracker",
			"passwordTrackerId", true, "userId", true, "createDate", true,
			"password", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		PasswordTracker newPasswordTracker = addPasswordTracker();

		PasswordTracker existingPasswordTracker = _persistence.fetchByPrimaryKey(newPasswordTracker.getPrimaryKey());

		Assert.assertEquals(existingPasswordTracker, newPasswordTracker);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		PasswordTracker missingPasswordTracker = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPasswordTracker);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = new PasswordTrackerActionableDynamicQuery() {
				@Override
				protected void performAction(Object object) {
					PasswordTracker passwordTracker = (PasswordTracker)object;

					Assert.assertNotNull(passwordTracker);

					count.increment();
				}
			};

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		PasswordTracker newPasswordTracker = addPasswordTracker();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PasswordTracker.class,
				PasswordTracker.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("passwordTrackerId",
				newPasswordTracker.getPasswordTrackerId()));

		List<PasswordTracker> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		PasswordTracker existingPasswordTracker = result.get(0);

		Assert.assertEquals(existingPasswordTracker, newPasswordTracker);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PasswordTracker.class,
				PasswordTracker.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("passwordTrackerId",
				ServiceTestUtil.nextLong()));

		List<PasswordTracker> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		PasswordTracker newPasswordTracker = addPasswordTracker();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PasswordTracker.class,
				PasswordTracker.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"passwordTrackerId"));

		Object newPasswordTrackerId = newPasswordTracker.getPasswordTrackerId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("passwordTrackerId",
				new Object[] { newPasswordTrackerId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPasswordTrackerId = result.get(0);

		Assert.assertEquals(existingPasswordTrackerId, newPasswordTrackerId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PasswordTracker.class,
				PasswordTracker.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"passwordTrackerId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("passwordTrackerId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected PasswordTracker addPasswordTracker() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		PasswordTracker passwordTracker = _persistence.create(pk);

		passwordTracker.setUserId(ServiceTestUtil.nextLong());

		passwordTracker.setCreateDate(ServiceTestUtil.nextDate());

		passwordTracker.setPassword(ServiceTestUtil.randomString());

		_persistence.update(passwordTracker);

		return passwordTracker;
	}

	private static Log _log = LogFactoryUtil.getLog(PasswordTrackerPersistenceTest.class);
	private PasswordTrackerPersistence _persistence = (PasswordTrackerPersistence)PortalBeanLocatorUtil.locate(PasswordTrackerPersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}