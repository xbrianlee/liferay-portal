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

package com.liferay.portal.security.ldap;

import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 */
public interface PortalLDAPExporter {

	public void exportToLDAP(
			Contact contact, Map<String, Serializable> contactExpandoAttributes)
		throws Exception;

	public void exportToLDAP(
			long userId, long userGroupId, LDAPOperation ldapOperation)
		throws Exception;

	public void exportToLDAP(
			User user, Map<String, Serializable> userExpandoAttributes)
		throws Exception;

}