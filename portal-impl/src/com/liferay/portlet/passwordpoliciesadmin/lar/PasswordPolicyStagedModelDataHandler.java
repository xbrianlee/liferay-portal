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

package com.liferay.portlet.passwordpoliciesadmin.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PasswordPolicyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

/**
 * @author Daniela Zapata Riesco
 */
public class PasswordPolicyStagedModelDataHandler
	extends BaseStagedModelDataHandler<PasswordPolicy> {

	public static final String[] CLASS_NAMES = {PasswordPolicy.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		PasswordPolicy passwordPolicy =
			PasswordPolicyLocalServiceUtil.
				fetchPasswordPolicyByUuidAndCompanyId(
					uuid, group.getCompanyId());

		if (passwordPolicy != null) {
			PasswordPolicyLocalServiceUtil.deletePasswordPolicy(passwordPolicy);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			PasswordPolicy passwordPolicy)
		throws Exception {

		Element passwordPolicyElement = portletDataContext.getExportDataElement(
			passwordPolicy);

		portletDataContext.addClassedModel(
			passwordPolicyElement,
			ExportImportPathUtil.getModelPath(passwordPolicy), passwordPolicy);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			PasswordPolicy passwordPolicy)
		throws Exception {

		long userId = portletDataContext.getUserId(
			passwordPolicy.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			passwordPolicy);

		PasswordPolicy existingPasswordPolicy =
			PasswordPolicyLocalServiceUtil.
				fetchPasswordPolicyByUuidAndCompanyId(
					passwordPolicy.getUuid(),
					portletDataContext.getCompanyId());

		if (existingPasswordPolicy == null) {
			existingPasswordPolicy =
				PasswordPolicyLocalServiceUtil.fetchPasswordPolicy(
					portletDataContext.getCompanyId(),
					passwordPolicy.getName());
		}

		PasswordPolicy importedPasswordPolicy = null;

		if (existingPasswordPolicy == null) {
			serviceContext.setUuid(passwordPolicy.getUuid());

			importedPasswordPolicy =
				PasswordPolicyLocalServiceUtil.addPasswordPolicy(
					userId, passwordPolicy.isDefaultPolicy(),
					passwordPolicy.getName(), passwordPolicy.getDescription(),
					passwordPolicy.getChangeable(),
					passwordPolicy.isChangeRequired(),
					passwordPolicy.getMinAge(), passwordPolicy.getCheckSyntax(),
					passwordPolicy.isAllowDictionaryWords(),
					passwordPolicy.getMinAlphanumeric(),
					passwordPolicy.getMinLength(),
					passwordPolicy.getMinLowerCase(),
					passwordPolicy.getMinNumbers(),
					passwordPolicy.getMinSymbols(),
					passwordPolicy.getMinUpperCase(), passwordPolicy.getRegex(),
					passwordPolicy.isHistory(),
					passwordPolicy.getHistoryCount(),
					passwordPolicy.isExpireable(), passwordPolicy.getMaxAge(),
					passwordPolicy.getWarningTime(),
					passwordPolicy.getGraceLimit(), passwordPolicy.isLockout(),
					passwordPolicy.getMaxFailure(),
					passwordPolicy.getLockoutDuration(),
					passwordPolicy.getResetFailureCount(),
					passwordPolicy.getResetTicketMaxAge(), serviceContext);
		}
		else {
			importedPasswordPolicy =
				PasswordPolicyLocalServiceUtil.updatePasswordPolicy(
					existingPasswordPolicy.getPasswordPolicyId(),
					passwordPolicy.getName(), passwordPolicy.getDescription(),
					passwordPolicy.isChangeable(),
					passwordPolicy.isChangeRequired(),
					passwordPolicy.getMinAge(), passwordPolicy.isCheckSyntax(),
					passwordPolicy.isAllowDictionaryWords(),
					passwordPolicy.getMinAlphanumeric(),
					passwordPolicy.getMinLength(),
					passwordPolicy.getMinLowerCase(),
					passwordPolicy.getMinNumbers(),
					passwordPolicy.getMinSymbols(),
					passwordPolicy.getMinUpperCase(), passwordPolicy.getRegex(),
					passwordPolicy.isHistory(),
					passwordPolicy.getHistoryCount(),
					passwordPolicy.isExpireable(), passwordPolicy.getMaxAge(),
					passwordPolicy.getWarningTime(),
					passwordPolicy.getGraceLimit(), passwordPolicy.isLockout(),
					passwordPolicy.getMaxFailure(),
					passwordPolicy.getLockoutDuration(),
					passwordPolicy.getResetFailureCount(),
					passwordPolicy.getResetTicketMaxAge(), serviceContext);
		}

		portletDataContext.importClassedModel(
			passwordPolicy, importedPasswordPolicy);
	}

}