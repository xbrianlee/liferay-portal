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

package com.liferay.portlet.messageboards.social;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousDestinationExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.portlet.messageboards.util.MBTestUtil;
import com.liferay.portlet.social.BaseSocialActivityInterpreterTestCase;
import com.liferay.portlet.social.model.SocialActivityInterpreter;

import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		SynchronousDestinationExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class MBMessageActivityInterpreterTest
	extends BaseSocialActivityInterpreterTestCase {

	@Override
	protected void addActivities() throws Exception {
		message = MBTestUtil.addMessage(group.getGroupId());

		message = MBTestUtil.addMessage(
			group.getGroupId(), message.getCategoryId(), message.getThreadId(),
			message.getMessageId());
	}

	@Override
	protected SocialActivityInterpreter getActivityInterpreter() {
		return new MBMessageActivityInterpreter();
	}

	@Override
	protected int[] getActivityTypes() {
		return new int[] {
			MBActivityKeys.ADD_MESSAGE, MBActivityKeys.REPLY_MESSAGE
		};
	}

	@Override
	protected boolean isSupportsRename(String className) {
		return false;
	}

	@Override
	protected void moveModelsToTrash() throws Exception {
		MBThreadLocalServiceUtil.moveThreadToTrash(
			TestPropsValues.getUserId(), message.getThreadId());
	}

	@Override
	protected void renameModels() throws Exception {
	}

	@Override
	protected void restoreModelsFromTrash() throws Exception {
		MBThreadLocalServiceUtil.restoreThreadFromTrash(
			TestPropsValues.getUserId(), message.getThreadId());
	}

	protected MBMessage message;

}