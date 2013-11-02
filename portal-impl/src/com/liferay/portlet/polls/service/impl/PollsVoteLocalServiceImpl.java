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

package com.liferay.portlet.polls.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.polls.DuplicateVoteException;
import com.liferay.portlet.polls.NoSuchQuestionException;
import com.liferay.portlet.polls.QuestionExpiredException;
import com.liferay.portlet.polls.model.PollsChoice;
import com.liferay.portlet.polls.model.PollsQuestion;
import com.liferay.portlet.polls.model.PollsVote;
import com.liferay.portlet.polls.service.base.PollsVoteLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Mate Thurzo
 */
public class PollsVoteLocalServiceImpl extends PollsVoteLocalServiceBaseImpl {

	@Override
	public PollsVote addVote(
			long userId, long questionId, long choiceId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Choice

		Date now = new Date();

		PollsChoice choice = pollsChoicePersistence.findByPrimaryKey(choiceId);

		if (choice.getQuestionId() != questionId) {
			throw new NoSuchQuestionException();
		}

		// Question

		PollsQuestion question = pollsQuestionPersistence.findByPrimaryKey(
			questionId);

		if (question.isExpired(serviceContext, now)) {
			throw new QuestionExpiredException();
		}

		question.setLastVoteDate(serviceContext.getCreateDate(now));

		pollsQuestionPersistence.update(question);

		// Vote

		PollsVote vote = pollsVotePersistence.fetchByQ_U(questionId, userId);

		if (vote != null) {
			throw new DuplicateVoteException();
		}
		else {
			String userName = null;

			User user = userPersistence.fetchByPrimaryKey(userId);

			if (user != null) {
				userName = user.getFullName();
			}
			else {
				userName = serviceContext.translate("anonymous");
			}

			long voteId = counterLocalService.increment();

			vote = pollsVotePersistence.create(voteId);

			vote.setUuid(serviceContext.getUuid());
			vote.setGroupId(serviceContext.getScopeGroupId());
			vote.setCompanyId(serviceContext.getCompanyId());
			vote.setUserId(userId);
			vote.setUserName(userName);
			vote.setCreateDate(serviceContext.getCreateDate(now));
			vote.setModifiedDate(serviceContext.getModifiedDate(now));
			vote.setQuestionId(questionId);
			vote.setChoiceId(choiceId);
			vote.setVoteDate(serviceContext.getCreateDate(now));

			pollsVotePersistence.update(vote);
		}

		return vote;
	}

	@Override
	public List<PollsVote> getChoiceVotes(long choiceId, int start, int end)
		throws SystemException {

		return pollsVotePersistence.findByChoiceId(choiceId, start, end);
	}

	@Override
	public int getChoiceVotesCount(long choiceId) throws SystemException {
		return pollsVotePersistence.countByChoiceId(choiceId);
	}

	@Override
	public List<PollsVote> getQuestionVotes(long questionId, int start, int end)
		throws SystemException {

		return pollsVotePersistence.findByQuestionId(questionId, start, end);
	}

	@Override
	public int getQuestionVotesCount(long questionId) throws SystemException {
		return pollsVotePersistence.countByQuestionId(questionId);
	}

	@Override
	public PollsVote getVote(long questionId, long userId)
		throws PortalException, SystemException {

		return pollsVotePersistence.findByQ_U(questionId, userId);
	}

}