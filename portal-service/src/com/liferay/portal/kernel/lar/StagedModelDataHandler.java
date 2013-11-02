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

package com.liferay.portal.kernel.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.StagedModel;

import java.util.Map;

/**
 * @author Mate Thurzo
 * @author Daniel Kocsis
 * @author Zsolt Berentey
 */
public interface StagedModelDataHandler<T extends StagedModel> {

	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException;

	public void exportStagedModel(
			PortletDataContext portletDataContext, T stagedModel)
		throws PortletDataException;

	public String[] getClassNames();

	public String getDisplayName(T StagedModel);

	public int[] getExportableStatuses();

	public Map<String, String> getReferenceAttributes(
		PortletDataContext portletDataContext, T stagedModel);

	public void importCompanyStagedModel(
			PortletDataContext portletDataContext, Element element)
		throws PortletDataException;

	public void importCompanyStagedModel(
			PortletDataContext portletDataContext, String uuid, long classPK)
		throws PortletDataException;

	public void importStagedModel(
			PortletDataContext portletDataContext, T stagedModel)
		throws PortletDataException;

	public void restoreStagedModel(
			PortletDataContext portletDataContext, T stagedModel)
		throws PortletDataException;

	public boolean validateReference(
		PortletDataContext portletDataContext, Element referenceElement);

}