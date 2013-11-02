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

package com.liferay.portlet.journal.lar;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.test.TransactionalCallbackAwareExecutionTestListener;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalArticleServiceUtil;
import com.liferay.portlet.journal.util.JournalTestUtil;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Julio Camarero
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class,
		TransactionalCallbackAwareExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
@Transactional
public class JournalExpiredVersionExportImportTest
	extends JournalExportImportTest {

	@Ignore()
	@Override
	@Test
	public void testExportImportAssetLinks() throws Exception {
	}

	@Override
	@Test
	public void testExportImportBasicJournalArticle() throws Exception {
		int initialArticlesCount =
			JournalArticleLocalServiceUtil.getArticlesCount(group.getGroupId());

		int initialSearchArticlesCount = JournalTestUtil.getSearchArticlesCount(
			group.getCompanyId(), group.getGroupId());

		JournalArticle article = JournalTestUtil.addArticle(
			group.getGroupId(), ServiceTestUtil.randomString(),
			ServiceTestUtil.randomString());

		Assert.assertEquals(1.0, article.getVersion(), 0);

		article = JournalTestUtil.updateArticle(
			article, ServiceTestUtil.randomString(),
			ServiceTestUtil.randomString());

		Assert.assertEquals(1.1, article.getVersion(), 0);
		Assert.assertEquals(
			initialArticlesCount + 2,
			JournalArticleLocalServiceUtil.getArticlesCount(
				group.getGroupId()));
		Assert.assertEquals(
			initialSearchArticlesCount + 1,
			JournalTestUtil.getSearchArticlesCount(
				group.getCompanyId(), group.getGroupId()));

		exportImportPortlet(PortletKeys.JOURNAL);

		Assert.assertEquals(
			initialArticlesCount + 2,
			JournalArticleLocalServiceUtil.getArticlesCount(
				importedGroup.getGroupId()));
		Assert.assertEquals(
			initialSearchArticlesCount + 1,
			JournalTestUtil.getSearchArticlesCount(
				importedGroup.getCompanyId(), importedGroup.getGroupId()));

		JournalArticleServiceUtil.expireArticle(
			group.getGroupId(), article.getArticleId(), null,
			ServiceTestUtil.getServiceContext(group.getGroupId()));

		Assert.assertEquals(
			initialSearchArticlesCount,
			JournalTestUtil.getSearchArticlesCount(
				group.getCompanyId(), group.getGroupId()));

		exportImportPortlet(PortletKeys.JOURNAL);

		Assert.assertEquals(
			initialSearchArticlesCount,
			JournalTestUtil.getSearchArticlesCount(
				importedGroup.getCompanyId(), importedGroup.getGroupId()));
	}

	@Ignore()
	@Override
	@Test
	public void testExportImportStructuredJournalArticle() throws Exception {
	}

}