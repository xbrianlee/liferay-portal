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

package com.liferay.portlet.asset.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;

/**
 * @author Eduardo Lundgren
 * @author JorgeFerrer
 */
public class AssetVocabularyPermission {

	public static void check(
			PermissionChecker permissionChecker, AssetVocabulary vocabulary,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, vocabulary, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long vocabularyId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, vocabularyId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, AssetVocabulary vocabulary,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				vocabulary.getCompanyId(), AssetVocabulary.class.getName(),
				vocabulary.getVocabularyId(), vocabulary.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			vocabulary.getGroupId(), AssetVocabulary.class.getName(),
			vocabulary.getVocabularyId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long vocabularyId,
			String actionId)
		throws PortalException, SystemException {

		AssetVocabulary vocabulary =
			AssetVocabularyLocalServiceUtil.getVocabulary(vocabularyId);

		return contains(permissionChecker, vocabulary, actionId);
	}

}