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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.User;

import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapContext;

/**
 * @author Edward Han
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class PortalLDAPImporterUtil {

	public static PortalLDAPImporter getPortalLDAPImporter() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortalLDAPImporterUtil.class);

		return _portalLDAPImporter;
	}

	public static void importFromLDAP() throws Exception {
		getPortalLDAPImporter().importFromLDAP();
	}

	public static void importFromLDAP(long companyId) throws Exception {
		getPortalLDAPImporter().importFromLDAP(companyId);
	}

	public static void importFromLDAP(long ldapServerId, long companyId)
		throws Exception {

		getPortalLDAPImporter().importFromLDAP(ldapServerId, companyId);
	}

	public static User importLDAPUser(
			long ldapServerId, long companyId, LdapContext ldapContext,
			Attributes attributes, String password)
		throws Exception {

		return getPortalLDAPImporter().importLDAPUser(
			ldapServerId, companyId, ldapContext, attributes, password);
	}

	public static User importLDAPUser(
			long ldapServerId, long companyId, String emailAddress,
			String screenName)
		throws Exception {

		return getPortalLDAPImporter().importLDAPUser(
			ldapServerId, companyId, emailAddress, screenName);
	}

	public static User importLDAPUser(
			long companyId, String emailAddress, String screenName)
		throws Exception {

		return getPortalLDAPImporter().importLDAPUser(
			companyId, emailAddress, screenName);
	}

	public static User importLDAPUserByScreenName(
			long companyId, String screenName)
		throws Exception {

		return getPortalLDAPImporter().importLDAPUserByScreenName(
			companyId, screenName);
	}

	public void setPortalLDAPImporter(PortalLDAPImporter portalLDAPImporter) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portalLDAPImporter = portalLDAPImporter;
	}

	private static PortalLDAPImporter _portalLDAPImporter;

}