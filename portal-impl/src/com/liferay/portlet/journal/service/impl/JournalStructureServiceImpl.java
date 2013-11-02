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

package com.liferay.portlet.journal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureConstants;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.service.base.JournalStructureServiceBaseImpl;
import com.liferay.portlet.journal.service.permission.JournalPermission;
import com.liferay.portlet.journal.service.permission.JournalStructurePermission;
import com.liferay.portlet.journal.util.JournalUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author     Brian Wing Shun Chan
 * @author     Raymond Augé
 * @deprecated As of 6.2.0, since Web Content Administration now uses the
 *             Dynamic Data Mapping framework to handle templates
 */
public class JournalStructureServiceImpl
	extends JournalStructureServiceBaseImpl {

	@Override
	public JournalStructure addStructure(
			long groupId, String structureId, boolean autoStructureId,
			String parentStructureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String xsd,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_STRUCTURE);

		return journalStructureLocalService.addStructure(
			getUserId(), groupId, structureId, autoStructureId,
			parentStructureId, nameMap, descriptionMap, xsd, serviceContext);
	}

	@Override
	public JournalStructure copyStructure(
			long groupId, String oldStructureId, String newStructureId,
			boolean autoStructureId)
		throws PortalException, SystemException {

		JournalPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_STRUCTURE);

		return journalStructureLocalService.copyStructure(
			getUserId(), groupId, oldStructureId, newStructureId,
			autoStructureId);
	}

	@Override
	public void deleteStructure(long groupId, String structureId)
		throws PortalException, SystemException {

		JournalStructurePermission.check(
			getPermissionChecker(), groupId, structureId, ActionKeys.DELETE);

		journalStructureLocalService.deleteStructure(groupId, structureId);
	}

	@Override
	public JournalStructure getStructure(long groupId, String structureId)
		throws PortalException, SystemException {

		JournalStructurePermission.check(
			getPermissionChecker(), groupId, structureId, ActionKeys.VIEW);

		return journalStructureLocalService.getStructure(groupId, structureId);
	}

	@Override
	public JournalStructure getStructure(
			long groupId, String structureId, boolean includeGlobalStructures)
		throws PortalException, SystemException {

		JournalStructurePermission.check(
			getPermissionChecker(), groupId, structureId, ActionKeys.VIEW);

		return journalStructureLocalService.getStructure(
			groupId, structureId, includeGlobalStructures);
	}

	@Override
	public List<JournalStructure> getStructures(long groupId)
		throws SystemException {

		List<DDMStructure> ddmStructures =
			ddmStructurePersistence.filterFindByG_C(
				groupId, PortalUtil.getClassNameId(JournalArticle.class));

		return JournalUtil.toJournalStructures(ddmStructures);
	}

	@Override
	public List<JournalStructure> getStructures(long[] groupIds)
		throws SystemException {

		List<DDMStructure> ddmStructures =
			ddmStructurePersistence.filterFindByG_C(
				groupIds, PortalUtil.getClassNameId(JournalArticle.class));

		return JournalUtil.toJournalStructures(ddmStructures);
	}

	@Override
	public List<JournalStructure> search(
			long companyId, long[] groupIds, String keywords, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		long[] classNameIds = {PortalUtil.getClassNameId(JournalArticle.class)};

		List<DDMStructure> ddmStructures =
			ddmStructureFinder.filterFindByKeywords(
				companyId, groupIds, classNameIds, keywords, start, end, obc);

		return JournalUtil.toJournalStructures(ddmStructures);
	}

	@Override
	public List<JournalStructure> search(
			long companyId, long[] groupIds, String structureId, String name,
			String description, boolean andOperator, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		long[] classNameIds = {PortalUtil.getClassNameId(JournalArticle.class)};

		List<DDMStructure> ddmStructures =
			ddmStructureFinder.filterFindByC_G_C_N_D_S_T(
				companyId, groupIds, classNameIds, name, description, null,
				DDMStructureConstants.TYPE_DEFAULT, andOperator, start, end,
				obc);

		return JournalUtil.toJournalStructures(ddmStructures);
	}

	@Override
	public int searchCount(long companyId, long[] groupIds, String keywords)
		throws SystemException {

		long[] classNameIds = {PortalUtil.getClassNameId(JournalArticle.class)};

		return ddmStructureFinder.filterCountByKeywords(
			companyId, groupIds, classNameIds, keywords);
	}

	@Override
	public int searchCount(
			long companyId, long[] groupIds, String structureId, String name,
			String description, boolean andOperator)
		throws SystemException {

		long[] classNameIds = {PortalUtil.getClassNameId(JournalArticle.class)};

		return ddmStructureFinder.filterCountByC_G_C_N_D_S_T(
			companyId, groupIds, classNameIds, name, description, null,
			DDMStructureConstants.TYPE_DEFAULT, andOperator);
	}

	@Override
	public JournalStructure updateStructure(
			long groupId, String structureId, String parentStructureId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String xsd, ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalStructurePermission.check(
			getPermissionChecker(), groupId, structureId, ActionKeys.UPDATE);

		return journalStructureLocalService.updateStructure(
			groupId, structureId, parentStructureId, nameMap, descriptionMap,
			xsd, serviceContext);
	}

}