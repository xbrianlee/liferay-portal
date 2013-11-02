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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Contact;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.base.ContactServiceBaseImpl;
import com.liferay.portal.service.permission.CommonPermissionUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Vilmos Papp
 */
public class ContactServiceImpl extends ContactServiceBaseImpl {

	@Override
	public Contact getContact(long contactId)
		throws PortalException, SystemException {

		Contact contact = contactPersistence.findByPrimaryKey(contactId);

		CommonPermissionUtil.check(
			getPermissionChecker(), contact.getClassNameId(),
			contact.getClassPK(), ActionKeys.VIEW);

		return contact;
	}

	@Override
	public List<Contact> getContacts(
			long classNameId, long classPK, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		CommonPermissionUtil.check(
			getPermissionChecker(), classNameId, classPK, ActionKeys.VIEW);

		return contactPersistence.findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	@Override
	public int getContactsCount(long classNameId, long classPK)
		throws PortalException, SystemException {

		CommonPermissionUtil.check(
			getPermissionChecker(), classNameId, classPK, ActionKeys.VIEW);

		return contactPersistence.countByC_C(classNameId, classPK);
	}

}