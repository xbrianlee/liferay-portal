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

package com.liferay.portlet.social.model;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.persistence.SocialRequestUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public abstract class BaseSocialRequestInterpreter
	implements SocialRequestInterpreter {

	public String getUserName(long userId, ThemeDisplay themeDisplay) {
		try {
			if (userId <= 0) {
				return StringPool.BLANK;
			}

			User user = UserLocalServiceUtil.getUserById(userId);

			if (user.getUserId() == themeDisplay.getUserId()) {
				return HtmlUtil.escape(user.getFirstName());
			}

			String userName = user.getFullName();

			Group group = user.getGroup();

			if (group.getGroupId() == themeDisplay.getScopeGroupId()) {
				return HtmlUtil.escape(userName);
			}

			String userDisplayURL = user.getDisplayURL(themeDisplay);

			return "<a href=\"" + userDisplayURL + "\">" +
				HtmlUtil.escape(userName) + "</a>";
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	public String getUserNameLink(long userId, ThemeDisplay themeDisplay) {
		try {
			if (userId <= 0) {
				return StringPool.BLANK;
			}

			User user = UserLocalServiceUtil.getUserById(userId);

			String userName = user.getFullName();

			String userDisplayURL = user.getDisplayURL(themeDisplay);

			return "<a href=\"" + userDisplayURL + "\">" +
				HtmlUtil.escape(userName) + "</a>";
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	@Override
	public SocialRequestFeedEntry interpret(
		SocialRequest request, ThemeDisplay themeDisplay) {

		try {
			return doInterpret(request, themeDisplay);
		}
		catch (Exception e) {
			_log.error("Unable to interpret request", e);
		}

		return null;
	}

	@Override
	public boolean processConfirmation(
		SocialRequest request, ThemeDisplay themeDisplay) {

		try {
			return doProcessConfirmation(request, themeDisplay);
		}
		catch (Exception e) {
			_log.error("Unable to process confirmation", e);
		}

		return false;
	}

	public void processDuplicateRequestsFromUser(
			SocialRequest request, int oldStatus)
		throws SystemException {

		List<SocialRequest> requests = SocialRequestUtil.findByU_C_C_T_S(
			request.getUserId(), request.getClassNameId(), request.getClassPK(),
			request.getType(), oldStatus);

		int newStatus = request.getStatus();

		for (SocialRequest curRequest : requests) {
			curRequest.setStatus(newStatus);

			SocialRequestUtil.update(curRequest);
		}
	}

	public void processDuplicateRequestsToUser(
			SocialRequest request, int oldStatus)
		throws SystemException {

		List<SocialRequest> requests = SocialRequestUtil.findByC_C_T_R_S(
			request.getClassNameId(), request.getClassPK(), request.getType(),
			request.getReceiverUserId(), oldStatus);

		int newStatus = request.getStatus();

		for (SocialRequest curRequest : requests) {
			curRequest.setStatus(newStatus);

			SocialRequestUtil.update(curRequest);
		}
	}

	@Override
	public boolean processRejection(
		SocialRequest request, ThemeDisplay themeDisplay) {

		try {
			return doProcessRejection(request, themeDisplay);
		}
		catch (Exception e) {
			_log.error("Unable to process rejection", e);
		}

		return false;
	}

	protected abstract SocialRequestFeedEntry doInterpret(
			SocialRequest request, ThemeDisplay themeDisplay)
		throws Exception;

	protected abstract boolean doProcessConfirmation(
			SocialRequest request, ThemeDisplay themeDisplay)
		throws Exception;

	protected boolean doProcessRejection(
			SocialRequest request, ThemeDisplay themeDisplay)
		throws Exception {

		return true;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseSocialRequestInterpreter.class);

}