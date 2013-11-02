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

package com.liferay.portlet.wiki.security.permission;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.security.permission.BasePermissionPropagator;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import java.util.List;

import javax.portlet.ActionRequest;

/**
 * @author Hugo Huijser
 * @author Angelo Jefferson
 */
public class WikiPermissionPropagatorImpl extends BasePermissionPropagator {

	@Override
	public void propagateRolePermissions(
			ActionRequest actionRequest, String className, String primKey,
			long[] roleIds)
		throws PortalException, SystemException {

		if (!className.equals(WikiNode.class.getName())) {
			return;
		}

		long nodeId = GetterUtil.getLong(primKey);

		List<WikiPage> wikiPages = WikiPageLocalServiceUtil.getPages(
			nodeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WikiPage wikiPage : wikiPages) {
			for (long roleId : roleIds) {
				propagateRolePermissions(
					actionRequest, roleId, WikiNode.class.getName(), nodeId,
					WikiPage.class.getName(), wikiPage.getResourcePrimKey());
			}
		}
	}

}