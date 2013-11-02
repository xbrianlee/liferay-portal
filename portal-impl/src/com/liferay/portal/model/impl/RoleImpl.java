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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.Team;
import com.liferay.portal.service.TeamLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class RoleImpl extends RoleBaseImpl {

	public RoleImpl() {
	}

	@Override
	public String getDescriptiveName() throws PortalException, SystemException {
		String name = getName();

		if (isTeam()) {
			Team team = TeamLocalServiceUtil.getTeam(getClassPK());

			name = team.getName();
		}

		return name;
	}

	@Override
	public String getTitle(String languageId) {
		String value = super.getTitle(languageId);

		if (Validator.isNull(value)) {
			try {
				value = getDescriptiveName();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return value;
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		String value = super.getTitle(languageId, useDefault);

		if (Validator.isNull(value)) {
			try {
				value = getDescriptiveName();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return value;
	}

	@Override
	public String getTypeLabel() {
		return RoleConstants.getTypeLabel(getType());
	}

	@Override
	public boolean isTeam() {
		return hasClassName(Team.class);
	}

	protected boolean hasClassName(Class<?> clazz) {
		long classNameId = getClassNameId();

		if (classNameId == PortalUtil.getClassNameId(clazz)) {
			return true;
		}
		else {
			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(RoleImpl.class);

}