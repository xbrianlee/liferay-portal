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

package com.liferay.portlet.wiki.lar;

import com.liferay.portal.kernel.lar.PortletDataHandler;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.lar.BasePortletDataHandlerTestCase;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.util.WikiTestUtil;

import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class WikiPortletDataHandlerTest extends BasePortletDataHandlerTestCase {

	@Override
	protected void addStagedModels() throws Exception {
		WikiNode node = WikiTestUtil.addNode(
			TestPropsValues.getUserId(), stagingGroup.getGroupId(),
			ServiceTestUtil.randomString(), ServiceTestUtil.randomString());

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			stagingGroup.getGroupId());

		WikiTestUtil.addPage(
			TestPropsValues.getUserId(), node.getNodeId(),
			ServiceTestUtil.randomString(), ServiceTestUtil.randomString(),
			true, serviceContext);
	}

	@Override
	protected PortletDataHandler createPortletDataHandler() {
		return new WikiPortletDataHandler();
	}

	@Override
	protected String getPortletId() {
		return PortletKeys.WIKI;
	}

}