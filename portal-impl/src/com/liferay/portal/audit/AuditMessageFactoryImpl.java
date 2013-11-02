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

package com.liferay.portal.audit;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditMessageFactory;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

import java.util.Date;

/**
 * @author Amos Fong
 */
@DoPrivileged
public class AuditMessageFactoryImpl implements AuditMessageFactory {

	@Override
	public AuditMessage getAuditMessage(String message) throws JSONException {
		return new AuditMessage(message);
	}

	@Override
	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName) {

		return new AuditMessage(eventType, companyId, userId, userName);
	}

	@Override
	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK) {

		return new AuditMessage(
			eventType, companyId, userId, userName, className, classPK);
	}

	@Override
	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK, String message) {

		return new AuditMessage(
			eventType, companyId, userId, userName, className, classPK,
			message);
	}

	@Override
	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK, String message, Date timestamp,
		JSONObject additionalInfo) {

		return new AuditMessage(
			eventType, companyId, userId, userName, className, classPK, message,
			timestamp, additionalInfo);
	}

	@Override
	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK, String message,
		JSONObject additionalInfo) {

		return new AuditMessage(
			eventType, companyId, userId, userName, className, classPK, message,
			additionalInfo);
	}

}