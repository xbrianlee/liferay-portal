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

package com.liferay.portlet.asset.util;

import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.AssetCategoryException;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryConstants;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;

import java.util.List;

/**
 * @author Juan Fernández
 */
public class BaseAssetEntryValidator implements AssetEntryValidator {

	@Override
	public void validate(
			long groupId, String className, long[] categoryIds,
			String[] entryNames)
		throws PortalException, SystemException {

		List<AssetVocabulary> vocabularies =
			AssetVocabularyLocalServiceUtil.getGroupVocabularies(
				groupId, false);

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (!group.isCompany()) {
			try {
				Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
					group.getCompanyId());

				vocabularies = ListUtil.copy(vocabularies);

				vocabularies.addAll(
					AssetVocabularyLocalServiceUtil.getGroupVocabularies(
						companyGroup.getGroupId()));
			}
			catch (NoSuchGroupException nsge) {
			}
		}

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(className);

		for (AssetVocabulary vocabulary : vocabularies) {
			validate(classNameId, categoryIds, vocabulary);
		}
	}

	protected void validate(
			long classNameId, long[] categoryIds, AssetVocabulary vocabulary)
		throws PortalException, SystemException {

		UnicodeProperties settingsProperties =
			vocabulary.getSettingsProperties();

		long[] selectedClassNameIds = StringUtil.split(
			settingsProperties.getProperty("selectedClassNameIds"), 0L);

		if (selectedClassNameIds.length == 0) {
			return;
		}

		if ((selectedClassNameIds[0] !=
				AssetCategoryConstants.ALL_CLASS_NAME_IDS) &&
			!ArrayUtil.contains(selectedClassNameIds, classNameId)) {

			return;
		}

		String className = PortalUtil.getClassName(classNameId);

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if ((assetRendererFactory == null) ||
			!assetRendererFactory.isCategorizable()) {

			return;
		}

		long[] requiredClassNameIds = StringUtil.split(
			settingsProperties.getProperty("requiredClassNameIds"), 0L);

		List<AssetCategory> categories =
			AssetCategoryLocalServiceUtil.getVocabularyCategories(
				vocabulary.getVocabularyId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		if ((requiredClassNameIds.length > 0) &&
			((requiredClassNameIds[0] ==
				AssetCategoryConstants.ALL_CLASS_NAME_IDS) ||
			 ArrayUtil.contains(requiredClassNameIds, classNameId))) {

			boolean found = false;

			for (AssetCategory category : categories) {
				if (ArrayUtil.contains(categoryIds, category.getCategoryId())) {
					found = true;

					break;
				}
			}

			if (!found && !categories.isEmpty()) {
				throw new AssetCategoryException(
					vocabulary, AssetCategoryException.AT_LEAST_ONE_CATEGORY);
			}
		}

		if (!vocabulary.isMultiValued()) {
			boolean duplicate = false;

			for (AssetCategory category : categories) {
				if (ArrayUtil.contains(categoryIds, category.getCategoryId())) {
					if (!duplicate) {
						duplicate = true;
					}
					else {
						throw new AssetCategoryException(
							vocabulary,
							AssetCategoryException.TOO_MANY_CATEGORIES);
					}
				}
			}
		}
	}

}