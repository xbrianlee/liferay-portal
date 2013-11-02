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

import com.liferay.portal.model.User;

import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapContext;

/**
 * @author Michael C. Han
 */
public interface PortalLDAPImporter {

	public void importFromLDAP() throws Exception;

	public void importFromLDAP(long companyId) throws Exception;

	public void importFromLDAP(long ldapServerId, long companyId)
		throws Exception;

	public User importLDAPUser(
			long ldapServerId, long companyId, LdapContext ldapContext,
			Attributes attributes, String password)
		throws Exception;

	public User importLDAPUser(
			long ldapServerId, long companyId, String emailAddress,
			String screenName)
		throws Exception;

	public User importLDAPUser(
			long companyId, String emailAddress, String screenName)
		throws Exception;

	public User importLDAPUserByScreenName(long companyId, String screenName)
		throws Exception;

}