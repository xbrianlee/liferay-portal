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
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.service.base.JournalTemplateServiceBaseImpl;
import com.liferay.portlet.journal.service.permission.JournalPermission;
import com.liferay.portlet.journal.service.permission.JournalTemplatePermission;
import com.liferay.portlet.journal.util.JournalUtil;

import java.io.File;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author     Brian Wing Shun Chan
 * @author     Raymond Augé
 * @deprecated As of 6.2.0, since Web Content Administration now uses the
 *             Dynamic Data Mapping framework to handle templates
 */
public class JournalTemplateServiceImpl extends JournalTemplateServiceBaseImpl {

	@Override
	public JournalTemplate addTemplate(
			long groupId, String templateId, boolean autoTemplateId,
			String structureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String xsl, boolean formatXsl,
			String langType, boolean cacheable, boolean smallImage,
			String smallImageURL, File smallFile, ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_TEMPLATE);

		return journalTemplateLocalService.addTemplate(
			getUserId(), groupId, templateId, autoTemplateId, structureId,
			nameMap, descriptionMap, xsl, formatXsl, langType, cacheable,
			smallImage, smallImageURL, smallFile, serviceContext);
	}

	@Override
	public JournalTemplate addTemplate(
			long groupId, String templateId, boolean autoTemplateId,
			String structureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String xsl, boolean formatXsl,
			String langType, boolean cacheable, ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_TEMPLATE);

		return journalTemplateLocalService.addTemplate(
			getUserId(), groupId, templateId, autoTemplateId, structureId,
			nameMap, descriptionMap, xsl, formatXsl, langType, cacheable, false,
			null, null, serviceContext);
	}

	@Override
	public JournalTemplate copyTemplate(
			long groupId, String oldTemplateId, String newTemplateId,
			boolean autoTemplateId)
		throws PortalException, SystemException {

		JournalPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_TEMPLATE);

		return journalTemplateLocalService.copyTemplate(
			getUserId(), groupId, oldTemplateId, newTemplateId, autoTemplateId);
	}

	@Override
	public void deleteTemplate(long groupId, String templateId)
		throws PortalException, SystemException {

		JournalTemplatePermission.check(
			getPermissionChecker(), groupId, templateId, ActionKeys.DELETE);

		journalTemplateLocalService.deleteTemplate(groupId, templateId);
	}

	@Override
	public List<JournalTemplate> getStructureTemplates(
			long groupId, String structureId)
		throws PortalException, SystemException {

		JournalStructure structure = journalStructureLocalService.getStructure(
			groupId, structureId);

		List<DDMTemplate> ddmTemplates =
			ddmTemplatePersistence.filterFindByG_CPK(
				groupId, structure.getPrimaryKey());

		return JournalUtil.toJournalTemplates(ddmTemplates);
	}

	@Override
	public JournalTemplate getTemplate(long groupId, String templateId)
		throws PortalException, SystemException {

		JournalTemplatePermission.check(
			getPermissionChecker(), groupId, templateId, ActionKeys.VIEW);

		return journalTemplateLocalService.getTemplate(groupId, templateId);
	}

	@Override
	public JournalTemplate getTemplate(
			long groupId, String templateId, boolean includeGlobalTemplates)
		throws PortalException, SystemException {

		JournalTemplatePermission.check(
			getPermissionChecker(), groupId, templateId, ActionKeys.VIEW);

		return journalTemplateLocalService.getTemplate(
			groupId, templateId, includeGlobalTemplates);
	}

	@Override
	public List<JournalTemplate> search(
			long companyId, long[] groupIds, String keywords,
			String structureId, String structureIdComparator, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		long[] classNameIds = {PortalUtil.getClassNameId(DDMStructure.class)};
		long[] classPKs = JournalUtil.getStructureClassPKs(
			groupIds, structureId);

		List<DDMTemplate> ddmTemplates = ddmTemplateFinder.filterFindByKeywords(
			companyId, groupIds, classNameIds, classPKs, keywords, null, null,
			start, end, obc);

		return JournalUtil.toJournalTemplates(ddmTemplates);
	}

	@Override
	public List<JournalTemplate> search(
			long companyId, long[] groupIds, String templateId,
			String structureId, String structureIdComparator, String name,
			String description, boolean andOperator, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		long[] classNameIds = {PortalUtil.getClassNameId(DDMStructure.class)};
		long[] classPKs = JournalUtil.getStructureClassPKs(
			groupIds, structureId);

		List<DDMTemplate> ddmTemplates =
			ddmTemplateFinder.filterFindByC_G_C_C_N_D_T_M_L(
				companyId, groupIds, classNameIds, classPKs, name, description,
				null, null, null, andOperator, start, end, obc);

		return JournalUtil.toJournalTemplates(ddmTemplates);
	}

	@Override
	public int searchCount(
			long companyId, long[] groupIds, String keywords,
			String structureId, String structureIdComparator)
		throws SystemException {

		long[] classNameIds = {PortalUtil.getClassNameId(DDMStructure.class)};
		long[] classPKs = JournalUtil.getStructureClassPKs(
			groupIds, structureId);

		return ddmTemplateFinder.filterCountByKeywords(
			companyId, groupIds, classNameIds, classPKs, keywords, null, null);
	}

	@Override
	public int searchCount(
			long companyId, long[] groupIds, String templateId,
			String structureId, String structureIdComparator, String name,
			String description, boolean andOperator)
		throws SystemException {

		long[] classNameIds = {PortalUtil.getClassNameId(DDMStructure.class)};
		long[] classPKs = JournalUtil.getStructureClassPKs(
			groupIds, structureId);

		return ddmTemplateFinder.filterCountByC_G_C_C_N_D_T_M_L(
			companyId, groupIds, classNameIds, classPKs, name, description,
			null, null, null, andOperator);
	}

	@Override
	public JournalTemplate updateTemplate(
			long groupId, String templateId, String structureId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String xsl, boolean formatXsl, String langType, boolean cacheable,
			boolean smallImage, String smallImageURL, File smallFile,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalTemplatePermission.check(
			getPermissionChecker(), groupId, templateId, ActionKeys.UPDATE);

		return journalTemplateLocalService.updateTemplate(
			groupId, templateId, structureId, nameMap, descriptionMap, xsl,
			formatXsl, langType, cacheable, smallImage, smallImageURL,
			smallFile, serviceContext);
	}

	@Override
	public JournalTemplate updateTemplate(
			long groupId, String templateId, String structureId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String xsl, boolean formatXsl, String langType, boolean cacheable,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalTemplatePermission.check(
			getPermissionChecker(), groupId, templateId, ActionKeys.UPDATE);

		return journalTemplateLocalService.updateTemplate(
			groupId, templateId, structureId, nameMap, descriptionMap, xsl,
			formatXsl, langType, cacheable, false, null, null, serviceContext);
	}

}