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

package com.liferay.portlet.portletdisplaytemplate.lar;

import com.liferay.portal.kernel.lar.PortletDataHandler;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.lar.BasePortletDataHandlerTestCase;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatamapping.util.DDMTemplateTestUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.wiki.model.WikiPage;

import org.junit.runner.RunWith;

/**
 * @author Daniel Kocsis
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class PortletDisplayTemplatePortletDataHandlerTest
	extends BasePortletDataHandlerTestCase {

	@Override
	protected void addStagedModels() throws Exception {
		for (Class<?> clazz : _DDM_TEMPLATE_CLASSES) {
			DDMTemplateTestUtil.addTemplate(
				stagingGroup.getGroupId(), PortalUtil.getClassNameId(clazz), 0);
		}
	}

	@Override
	protected PortletDataHandler createPortletDataHandler() {
		return new PortletDisplayTemplatePortletDataHandler();
	}

	@Override
	protected String getPortletId() {
		return PortletKeys.PORTLET_DISPLAY_TEMPLATES;
	}

	private Class<?>[] _DDM_TEMPLATE_CLASSES = {
		AssetCategory.class, AssetEntry.class, AssetTag.class, BlogsEntry.class,
		DDLRecordSet.class, FileEntry.class, JournalArticle.class,
		LayoutSet.class, WikiPage.class
	};

}