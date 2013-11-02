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

package com.liferay.portlet.messageboards.lar;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBBan;
import com.liferay.portlet.messageboards.service.MBBanLocalServiceUtil;

/**
 * @author Daniel Kocsis
 */
public class MBBanStagedModelDataHandler
	extends BaseStagedModelDataHandler<MBBan> {

	public static final String[] CLASS_NAMES = {MBBan.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws SystemException {

		MBBan ban = MBBanLocalServiceUtil.fetchMBBanByUuidAndGroupId(
			uuid, groupId);

		if (ban != null) {
			MBBanLocalServiceUtil.deleteBan(ban);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, MBBan ban)
		throws Exception {

		Element userBanElement = portletDataContext.getExportDataElement(ban);

		ban.setBanUserUuid(ban.getBanUserUuid());

		User bannedUser = UserLocalServiceUtil.getUser(ban.getUserId());

		portletDataContext.addReferenceElement(
			ban, userBanElement, bannedUser, User.class,
			PortletDataContext.REFERENCE_TYPE_DEPENDENCY_DISPOSABLE, true);

		portletDataContext.addClassedModel(
			userBanElement, ExportImportPathUtil.getModelPath(ban), ban);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, MBBan ban)
		throws Exception {

		User user = UserLocalServiceUtil.fetchUserByUuidAndCompanyId(
			ban.getBanUserUuid(), portletDataContext.getCompanyId());

		if (user == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to find banned user with uuid " +
						ban.getBanUserUuid());
			}

			return;
		}

		long userId = portletDataContext.getUserId(ban.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			ban);

		serviceContext.setUuid(ban.getUuid());

		MBBanLocalServiceUtil.addBan(userId, user.getUserId(), serviceContext);
	}

	private static Log _log = LogFactoryUtil.getLog(
		MBBanStagedModelDataHandler.class);

}