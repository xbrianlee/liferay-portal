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

package com.liferay.portlet.admin.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.model.Company;
import com.liferay.portal.security.ldap.LDAPSettingsUtil;
import com.liferay.portal.security.ldap.PortalLDAPImporterUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;

import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class LDAPImportMessageListener extends BaseMessageListener {

	protected void doImportOnStartup() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies(false);

		for (Company company : companies) {
			long companyId = company.getCompanyId();

			if (LDAPSettingsUtil.isImportOnStartup(companyId)) {
				PortalLDAPImporterUtil.importFromLDAP(companyId);
			}
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		if (_startup) {
			_startup = false;

			doImportOnStartup();
		}
		else {
			PortalLDAPImporterUtil.importFromLDAP();
		}
	}

	private boolean _startup = true;

}