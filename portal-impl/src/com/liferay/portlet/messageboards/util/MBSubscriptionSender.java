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

package com.liferay.portlet.messageboards.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.mail.Account;
import com.liferay.portal.kernel.mail.SMTPAccount;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.messageboards.NoSuchMailingListException;
import com.liferay.portlet.messageboards.model.MBMailingList;
import com.liferay.portlet.messageboards.service.MBMailingListLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Thiago Moreira
 */
public class MBSubscriptionSender extends SubscriptionSender {

	public void addMailingListSubscriber(long groupId, long categoryId)
		throws PortalException, SystemException {

		if (_calledAddMailingListSubscriber) {
			throw new IllegalArgumentException();
		}

		_calledAddMailingListSubscriber = true;

		MBMailingList mailingList = null;

		try {
			mailingList = MBMailingListLocalServiceUtil.getCategoryMailingList(
				groupId, categoryId);
		}
		catch (NoSuchMailingListException nsmle) {
			return;
		}

		if (!mailingList.isActive()) {
			return;
		}

		setFrom(mailingList.getOutEmailAddress(), null);
		setReplyToAddress(mailingList.getEmailAddress());

		if (mailingList.isOutCustom()) {
			String protocol = Account.PROTOCOL_SMTP;

			if (mailingList.isOutUseSSL()) {
				protocol = Account.PROTOCOL_SMTPS;
			}

			SMTPAccount smtpAccount = (SMTPAccount)Account.getInstance(
				protocol, mailingList.getOutServerPort());

			smtpAccount.setHost(mailingList.getOutServerName());
			smtpAccount.setUser(mailingList.getOutUserName());
			smtpAccount.setPassword(mailingList.getOutPassword());

			setSMTPAccount(smtpAccount);
		}

		setSubject(getMailingListSubject(subject, mailId));

		addRuntimeSubscribers(
			mailingList.getEmailAddress(), mailingList.getEmailAddress());
	}

	protected String getMailingListSubject(String subject, String mailId) {
		subject = GetterUtil.getString(subject);
		mailId = GetterUtil.getString(mailId);

		return subject.concat(StringPool.SPACE).concat(mailId);
	}

	private boolean _calledAddMailingListSubscriber;

}