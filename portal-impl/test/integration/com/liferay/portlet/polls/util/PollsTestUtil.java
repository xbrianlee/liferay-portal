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

package com.liferay.portlet.polls.util;

import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.polls.model.PollsChoice;
import com.liferay.portlet.polls.model.PollsQuestion;
import com.liferay.portlet.polls.model.PollsVote;
import com.liferay.portlet.polls.service.PollsChoiceLocalServiceUtil;
import com.liferay.portlet.polls.service.PollsQuestionLocalServiceUtil;
import com.liferay.portlet.polls.service.PollsVoteLocalServiceUtil;

/**
 * @author Shinn Lok
 */
public class PollsTestUtil {

	public static PollsChoice addChoice(long groupId, long questionId)
		throws Exception {

		return PollsChoiceLocalServiceUtil.addChoice(
			TestPropsValues.getUserId(), questionId,
			ServiceTestUtil.randomString(), ServiceTestUtil.randomString(),
			ServiceTestUtil.getServiceContext(groupId));
	}

	public static PollsQuestion addQuestion(long groupId) throws Exception {
		return PollsQuestionLocalServiceUtil.addQuestion(
			TestPropsValues.getUserId(),
			ServiceTestUtil.randomLocaleStringMap(),
			ServiceTestUtil.randomLocaleStringMap(), 0, 0, 0, 0, 0, true, null,
			ServiceTestUtil.getServiceContext(groupId));
	}

	public static PollsVote addVote(
			long groupId, long questionId, long choiceId)
		throws Exception {

		return PollsVoteLocalServiceUtil.addVote(
			TestPropsValues.getUserId(), questionId, choiceId,
			ServiceTestUtil.getServiceContext(groupId));
	}

}