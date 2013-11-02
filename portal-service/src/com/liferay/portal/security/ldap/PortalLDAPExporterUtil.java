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
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Edward Han
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 * @author Raymond Augé
 */
public class PortalLDAPExporterUtil {

	public static void exportToLDAP(
			Contact contact, Map<String, Serializable> contactExpandoAttributes)
		throws Exception {

		getPortalLDAPExporter().exportToLDAP(contact, contactExpandoAttributes);
	}

	public static void exportToLDAP(
			long userId, long userGroupId, LDAPOperation ldapOperation)
		throws Exception {

		getPortalLDAPExporter().exportToLDAP(
			userId, userGroupId, ldapOperation);
	}

	public static void exportToLDAP(
			User user, Map<String, Serializable> userExpandoAttributes)
		throws Exception {

		getPortalLDAPExporter().exportToLDAP(user, userExpandoAttributes);
	}

	public static PortalLDAPExporter getPortalLDAPExporter() {
		PortalRuntimePermission.checkGetBeanProperty(
			PortalLDAPExporterUtil.class);

		return _portalLDAPExporter;
	}

	public void setPortalLDAPExporter(PortalLDAPExporter portalLDAPExporter) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portalLDAPExporter = portalLDAPExporter;
	}

	private static PortalLDAPExporter _portalLDAPExporter;

}