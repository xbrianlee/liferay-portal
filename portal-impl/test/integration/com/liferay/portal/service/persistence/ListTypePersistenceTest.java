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

import com.liferay.portal.NoSuchListTypeException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.model.ListType;
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
public class ListTypePersistenceTest {
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
		int pk = ServiceTestUtil.nextInt();

		ListType listType = _persistence.create(pk);

		Assert.assertNotNull(listType);

		Assert.assertEquals(listType.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ListType newListType = addListType();

		_persistence.remove(newListType);

		ListType existingListType = _persistence.fetchByPrimaryKey(newListType.getPrimaryKey());

		Assert.assertNull(existingListType);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addListType();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = ServiceTestUtil.nextInt();

		ListType newListType = _persistence.create(pk);

		newListType.setName(ServiceTestUtil.randomString());

		newListType.setType(ServiceTestUtil.randomString());

		_persistence.update(newListType);

		ListType existingListType = _persistence.findByPrimaryKey(newListType.getPrimaryKey());

		Assert.assertEquals(existingListType.getListTypeId(),
			newListType.getListTypeId());
		Assert.assertEquals(existingListType.getName(), newListType.getName());
		Assert.assertEquals(existingListType.getType(), newListType.getType());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ListType newListType = addListType();

		ListType existingListType = _persistence.findByPrimaryKey(newListType.getPrimaryKey());

		Assert.assertEquals(existingListType, newListType);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = ServiceTestUtil.nextInt();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchListTypeException");
		}
		catch (NoSuchListTypeException nsee) {
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
		return OrderByComparatorFactoryUtil.create("ListType", "listTypeId",
			true, "name", true, "type", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ListType newListType = addListType();

		ListType existingListType = _persistence.fetchByPrimaryKey(newListType.getPrimaryKey());

		Assert.assertEquals(existingListType, newListType);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = ServiceTestUtil.nextInt();

		ListType missingListType = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingListType);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ListType newListType = addListType();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ListType.class,
				ListType.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("listTypeId",
				newListType.getListTypeId()));

		List<ListType> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ListType existingListType = result.get(0);

		Assert.assertEquals(existingListType, newListType);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ListType.class,
				ListType.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("listTypeId",
				ServiceTestUtil.nextInt()));

		List<ListType> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ListType newListType = addListType();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ListType.class,
				ListType.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("listTypeId"));

		Object newListTypeId = newListType.getListTypeId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("listTypeId",
				new Object[] { newListTypeId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingListTypeId = result.get(0);

		Assert.assertEquals(existingListTypeId, newListTypeId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ListType.class,
				ListType.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("listTypeId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("listTypeId",
				new Object[] { ServiceTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ListType addListType() throws Exception {
		int pk = ServiceTestUtil.nextInt();

		ListType listType = _persistence.create(pk);

		listType.setName(ServiceTestUtil.randomString());

		listType.setType(ServiceTestUtil.randomString());

		_persistence.update(listType);

		return listType;
	}

	private static Log _log = LogFactoryUtil.getLog(ListTypePersistenceTest.class);
	private ListTypePersistence _persistence = (ListTypePersistence)PortalBeanLocatorUtil.locate(ListTypePersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}