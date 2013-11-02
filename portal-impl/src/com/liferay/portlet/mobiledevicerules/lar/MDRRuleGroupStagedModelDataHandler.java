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

package com.liferay.portlet.mobiledevicerules.lar;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupLocalServiceUtil;

/**
 * @author Mate Thurzo
 */
public class MDRRuleGroupStagedModelDataHandler
	extends BaseStagedModelDataHandler<MDRRuleGroup> {

	public static final String[] CLASS_NAMES = {MDRRuleGroup.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws SystemException {

		MDRRuleGroup ruleGroup =
			MDRRuleGroupLocalServiceUtil.fetchMDRRuleGroupByUuidAndGroupId(
				uuid, groupId);

		if (ruleGroup != null) {
			MDRRuleGroupLocalServiceUtil.deleteRuleGroup(ruleGroup);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(MDRRuleGroup ruleGroup) {
		return ruleGroup.getNameCurrentValue();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, MDRRuleGroup ruleGroup)
		throws Exception {

		Element ruleGroupElement = portletDataContext.getExportDataElement(
			ruleGroup);

		portletDataContext.addClassedModel(
			ruleGroupElement, ExportImportPathUtil.getModelPath(ruleGroup),
			ruleGroup);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, MDRRuleGroup ruleGroup)
		throws Exception {

		long userId = portletDataContext.getUserId(ruleGroup.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			ruleGroup);

		serviceContext.setUserId(userId);

		MDRRuleGroup importedRuleGroup = null;

		if (portletDataContext.isDataStrategyMirror()) {
			MDRRuleGroup existingRuleGroup =
				MDRRuleGroupLocalServiceUtil.fetchMDRRuleGroupByUuidAndGroupId(
					ruleGroup.getUuid(), portletDataContext.getScopeGroupId());

			if (existingRuleGroup == null) {
				serviceContext.setUuid(ruleGroup.getUuid());

				importedRuleGroup = MDRRuleGroupLocalServiceUtil.addRuleGroup(
					portletDataContext.getScopeGroupId(),
					ruleGroup.getNameMap(), ruleGroup.getDescriptionMap(),
					serviceContext);
			}
			else {
				importedRuleGroup =
					MDRRuleGroupLocalServiceUtil.updateRuleGroup(
						existingRuleGroup.getRuleGroupId(),
						ruleGroup.getNameMap(), ruleGroup.getDescriptionMap(),
						serviceContext);
			}
		}
		else {
			importedRuleGroup = MDRRuleGroupLocalServiceUtil.addRuleGroup(
				portletDataContext.getScopeGroupId(), ruleGroup.getNameMap(),
				ruleGroup.getDescriptionMap(), serviceContext);
		}

		portletDataContext.importClassedModel(ruleGroup, importedRuleGroup);
	}

}