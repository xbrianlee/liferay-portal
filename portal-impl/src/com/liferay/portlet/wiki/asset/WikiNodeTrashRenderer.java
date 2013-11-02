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

package com.liferay.portlet.wiki.asset;

import com.liferay.portal.kernel.trash.BaseTrashRenderer;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.trash.util.TrashUtil;
import com.liferay.portlet.wiki.model.WikiNode;

import java.util.Locale;

/**
 * @author Eudaldo Alonso
 */
public class WikiNodeTrashRenderer extends BaseTrashRenderer {

	public static final String TYPE = "wiki_node";

	public WikiNodeTrashRenderer(WikiNode node) {
		_node = node;
	}

	@Override
	public String getClassName() {
		return WikiNode.class.getName();
	}

	@Override
	public long getClassPK() {
		return _node.getPrimaryKey();
	}

	@Override
	public String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/all_pages.png";
	}

	@Override
	public String getPortletId() {
		return PortletKeys.WIKI;
	}

	@Override
	public String getSummary(Locale locale) {
		return HtmlUtil.stripHtml(_node.getDescription());
	}

	@Override
	public String getTitle(Locale locale) {
		if (!_node.isInTrash()) {
			return _node.getName();
		}

		return TrashUtil.getOriginalTitle(_node.getName());
	}

	@Override
	public String getType() {
		return TYPE;
	}

	private WikiNode _node;

}