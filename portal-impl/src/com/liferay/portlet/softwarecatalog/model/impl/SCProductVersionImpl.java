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

package com.liferay.portlet.softwarecatalog.model.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion;
import com.liferay.portlet.softwarecatalog.model.SCProductEntry;
import com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalServiceUtil;
import com.liferay.portlet.softwarecatalog.service.SCProductEntryLocalServiceUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class SCProductVersionImpl extends SCProductVersionBaseImpl {

	public SCProductVersionImpl() {
	}

	@Override
	public List<SCFrameworkVersion> getFrameworkVersions()
		throws SystemException {

		return SCFrameworkVersionLocalServiceUtil.
			getProductVersionFrameworkVersions(getProductVersionId());
	}

	@Override
	public SCProductEntry getProductEntry() {
		SCProductEntry productEntry = null;

		try {
			productEntry = SCProductEntryLocalServiceUtil.getProductEntry(
				getProductEntryId());
		}
		catch (Exception e) {
			productEntry = new SCProductEntryImpl();

			_log.error(e);
		}

		return productEntry;
	}

	private static Log _log = LogFactoryUtil.getLog(SCProductVersionImpl.class);

}