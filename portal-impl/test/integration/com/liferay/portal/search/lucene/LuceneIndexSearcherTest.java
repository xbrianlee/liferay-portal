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

package com.liferay.portal.search.lucene;

import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portal.util.UserTestUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Roberto Díaz
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class LuceneIndexSearcherTest {

	@Before
	public void setUp() throws Exception {
		FinderCacheUtil.clearCache();

		int initialUsersCount = 0;

		do {
			_randomLastName = ServiceTestUtil.randomString(10);

			Hits hits = getHits(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			initialUsersCount = hits.getLength();
		}
		while (initialUsersCount > 0);

		for (int i = 0; i < _USERS_COUNT; i ++) {
			User user = UserTestUtil.addUser(
				ServiceTestUtil.randomString(), false,
				ServiceTestUtil.randomString(), _randomLastName,
				new long[] {TestPropsValues.getGroupId()});

			_users.add(user);
		}
	}

	@After
	public void tearDown() throws Exception {
		for (User user : _users) {
			UserLocalServiceUtil.deleteUser(user);
		}
	}

	@Test
	public void testResultsWhenTotalLessThanStartAndDeltaIsBiggerThanTotal()
		throws Exception {

		testResults(10, 20, _USERS_COUNT, 0);
	}

	@Test
	public void testResultsWhenTotalLessThanStartAndDeltaIsOne()
		throws Exception {

		testResults(10, 11, 1, 4);
	}

	@Test
	public void testResultsWhenTotalLessThanStartAndDeltaIsThree()
		throws Exception {

		testResults(10, 13, 2, 3);
	}

	@Test
	public void testSearchWithOneResult() throws Exception {
		Hits hits = getSearchWithOneResult(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(1, hits.getLength());
	}

	@Test
	public void testSearchWithOneResultWhenTotalEqualsStart() throws Exception {
		Hits hits = getSearchWithOneResult(_USERS_COUNT, 2 * _USERS_COUNT);

		Assert.assertEquals(1, hits.getLength());
	}

	@Test
	public void testSearchWithOneResultWhenTotalLessThanStart()
		throws Exception {

		Hits hits = getSearchWithOneResult(1000, 1000 + _USERS_COUNT);

		Assert.assertEquals(1, hits.getLength());
	}

	@Test
	public void testSearchWithoutResults() throws Exception {
		Hits hits = getSearchWithoutResults(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(0, hits.getLength());
	}

	@Test
	public void testSearchWithoutResultsWhenTotalEqualsStart()
		throws Exception {

		Hits hits = getSearchWithoutResults(_USERS_COUNT, 2 * _USERS_COUNT);

		Assert.assertEquals(0, hits.getLength());
	}

	@Test
	public void testSearchWithoutResultsWhenTotalLessThanStart()
		throws Exception {

		Hits hits = getSearchWithoutResults(1000, 1000 + _USERS_COUNT);

		Assert.assertEquals(0, hits.getLength());
	}

	@Test
	public void testSearchWithoutResultsWhenTotalLessThanStartAndDeltaIsOne()
		throws Exception {

		Hits hits = getSearchWithoutResults(1000, 1001);

		Assert.assertEquals(0, hits.getLength());
		Assert.assertEquals(0, hits.getDocs().length);
	}

	@Test
	public void testSearchWithResults() throws Exception {
		Hits hits = getHits(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Assert.assertEquals(_USERS_COUNT, hits.getLength());
		Assert.assertEquals(5, hits.getDocs().length);
	}

	@Test
	public void testSearchWithResultsWhenTotalEqualsStart() throws Exception {
		Hits hits = getHits(_USERS_COUNT, 2 * _USERS_COUNT);

		Assert.assertEquals(_USERS_COUNT, hits.getLength());
		Assert.assertEquals(_USERS_COUNT, hits.getDocs().length);
	}

	@Test
	public void testSearchWithResultsWhenTotalLessThanStart() throws Exception {
		Hits hits = getHits(1000, 1000 + _USERS_COUNT);

		Assert.assertEquals(_USERS_COUNT, hits.getLength());
	}

	@Test
	public void testSearchWithResultsWhenTotalLessThanStartAndDeltaIsOne()
		throws Exception {

		Hits hits = getHits(1000, 1001);

		Assert.assertEquals(_USERS_COUNT, hits.getLength());
		Assert.assertEquals(1, hits.getDocs().length);
	}

	protected Hits getHits(int start, int end) throws Exception {
		return getHits(_randomLastName, start, end);
	}

	protected Hits getHits(String keyword, int start, int end)
		throws Exception {

		Indexer indexer = IndexerRegistryUtil.getIndexer(User.class);

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(TestPropsValues.getCompanyId());
		searchContext.setEnd(end);
		searchContext.setGroupIds(new long[] {TestPropsValues.getGroupId()});
		searchContext.setKeywords(keyword);

		QueryConfig queryConfig = new QueryConfig();

		searchContext.setQueryConfig(queryConfig);

		searchContext.setStart(start);

		return indexer.search(searchContext);
	}

	protected Hits getSearchWithOneResult(int start, int end) throws Exception {
		User user = _users.get(0);

		return getHits(user.getFirstName(), start, end);
	}

	protected Hits getSearchWithoutResults(int start, int end)
		throws Exception {

		return getHits("invalidKeyword", start, end);
	}

	protected void testResults(
			int start, int end, int expectedTotal,
			int expectedRecalculatedStart)
		throws Exception {

		Hits hits = getHits(start, end);

		Assert.assertEquals(expectedTotal, hits.getDocs().length);

		for (int i = 0; i < hits.getDocs().length; i++) {
			Document doc = hits.doc(i);

			long userId = GetterUtil.getLong(doc.get(Field.USER_ID));

			User returnedUser = UserLocalServiceUtil.getUser(userId);

			Assert.assertEquals(
				_users.get(expectedRecalculatedStart + i), returnedUser);
		}
	}

	private static final int _USERS_COUNT = 5;

	private String _randomLastName;
	private List<User> _users = new ArrayList<User>();

}