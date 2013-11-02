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

package com.liferay.portal.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.LayoutSetBranch;
import com.liferay.portal.model.User;
import com.liferay.portal.service.LayoutRevisionLocalServiceUtil;
import com.liferay.portal.service.LayoutSetBranchLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

/**
 * @author Raymond Augé
 */
public class LayoutRevisionAssetRendererFactory
	extends BaseAssetRendererFactory {

	public static final String TYPE = "layout_revision";

	@Override
	public AssetEntry getAssetEntry(long assetEntryId)
		throws PortalException, SystemException {

		return getAssetEntry(getClassName(), assetEntryId);
	}

	@Override
	public AssetEntry getAssetEntry(String className, long classPK)
		throws PortalException, SystemException {

		LayoutRevision layoutRevision =
			LayoutRevisionLocalServiceUtil.getLayoutRevision(classPK);

		LayoutSetBranch layoutSetBranch =
			LayoutSetBranchLocalServiceUtil.getLayoutSetBranch(
				layoutRevision.getLayoutSetBranchId());

		User user = UserLocalServiceUtil.getUserById(
			layoutRevision.getUserId());

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.createAssetEntry(
			classPK);

		assetEntry.setGroupId(layoutRevision.getGroupId());
		assetEntry.setCompanyId(user.getCompanyId());
		assetEntry.setUserId(user.getUserId());
		assetEntry.setUserName(user.getFullName());
		assetEntry.setCreateDate(layoutRevision.getCreateDate());
		assetEntry.setClassNameId(
			PortalUtil.getClassNameId(LayoutRevision.class.getName()));
		assetEntry.setClassPK(layoutRevision.getLayoutRevisionId());

		StringBundler sb = new StringBundler();

		sb.append(layoutRevision.getHTMLTitle(LocaleUtil.getSiteDefault()));
		sb.append(" [");
		sb.append(layoutSetBranch.getName());
		sb.append("]");

		assetEntry.setTitle(sb.toString());

		return assetEntry;
	}

	@Override
	public AssetRenderer getAssetRenderer(long layoutRevisionId, int type)
		throws PortalException, SystemException {

		LayoutRevision layoutRevision =
			LayoutRevisionLocalServiceUtil.getLayoutRevision(layoutRevisionId);

		LayoutRevisionAssetRenderer layoutRevisionAssetRenderer =
			new LayoutRevisionAssetRenderer(layoutRevision);

		layoutRevisionAssetRenderer.setAssetRendererType(type);

		return layoutRevisionAssetRenderer;
	}

	@Override
	public String getClassName() {
		return LayoutRevision.class.getName();
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean isCategorizable() {
		return false;
	}

	@Override
	public boolean isSelectable() {
		return _SELECTABLE;
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/pages.png";
	}

	private static final boolean _SELECTABLE = false;

}