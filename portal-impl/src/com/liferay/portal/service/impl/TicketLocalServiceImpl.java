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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Ticket;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.TicketLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;

import java.util.Date;

/**
 * @author Mika Koivisto
 */
public class TicketLocalServiceImpl extends TicketLocalServiceBaseImpl {

	@Override
	public Ticket addTicket(
			long companyId, String className, long classPK, int type,
			String extraInfo, Date expirationDate,
			ServiceContext serviceContext)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);
		Date now = new Date();

		long ticketId = counterLocalService.increment();

		Ticket ticket = ticketPersistence.create(ticketId);

		ticket.setCompanyId(companyId);
		ticket.setCreateDate(now);
		ticket.setClassNameId(classNameId);
		ticket.setClassPK(classPK);
		ticket.setKey(PortalUUIDUtil.generate());
		ticket.setType(type);
		ticket.setExtraInfo(extraInfo);
		ticket.setExpirationDate(expirationDate);

		ticketPersistence.update(ticket);

		return ticket;
	}

	@Override
	public Ticket fetchTicket(String key) throws SystemException {
		return ticketPersistence.fetchByKey(key);
	}

	@Override
	public Ticket getTicket(String key)
		throws PortalException, SystemException {

		return ticketPersistence.findByKey(key);
	}

}