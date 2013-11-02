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

package com.liferay.portlet.dynamicdatamapping.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class DDMStorageLinkImpl extends DDMStorageLinkBaseImpl {

	public DDMStorageLinkImpl() {
	}

	@Override
	public String getStorageType() throws PortalException, SystemException {
		DDMStructure structure = getStructure();

		return structure.getStorageType();
	}

	@Override
	public DDMStructure getStructure() throws PortalException, SystemException {
		return DDMStructureLocalServiceUtil.getStructure(getStructureId());
	}

}