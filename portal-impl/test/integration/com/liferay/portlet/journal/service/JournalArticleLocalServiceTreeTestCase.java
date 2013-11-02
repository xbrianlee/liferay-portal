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

package com.liferay.portlet.journal.service;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalFolder;
import com.liferay.portlet.journal.util.JournalTestUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.testng.Assert;

/**
 * @author Shinn Lok
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class JournalArticleLocalServiceTreeTestCase {

	@After
	public void tearDown() throws Exception {
		for (int i = _articles.size() - 1; i >= 0; i--) {
			JournalArticleLocalServiceUtil.deleteArticle(_articles.get(i));
		}

		JournalFolderLocalServiceUtil.deleteFolder(_folder);
	}

	@Test
	public void testRebuildTree() throws Exception {
		createTree();

		for (JournalArticle article : _articles) {
			article.setTreePath(null);

			JournalArticleLocalServiceUtil.updateJournalArticle(article);
		}

		JournalArticleLocalServiceUtil.rebuildTree(
			TestPropsValues.getCompanyId());

		for (JournalArticle article : _articles) {
			article = JournalArticleLocalServiceUtil.getArticle(
				article.getId());

			Assert.assertEquals(article.buildTreePath(), article.getTreePath());
		}
	}

	protected void createTree() throws Exception {
		JournalArticle articleA = JournalTestUtil.addArticle(
			TestPropsValues.getGroupId(), "Article A",
			ServiceTestUtil.randomString());

		_articles.add(articleA);

		_folder = JournalTestUtil.addFolder(
			TestPropsValues.getGroupId(), "Folder A");

		JournalArticle articleAA = JournalTestUtil.addArticle(
			TestPropsValues.getGroupId(), _folder.getFolderId(), "Article AA",
			ServiceTestUtil.randomString());

		_articles.add(articleAA);
	}

	private List<JournalArticle> _articles = new ArrayList<JournalArticle>();
	private JournalFolder _folder;

}