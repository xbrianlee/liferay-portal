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

package com.liferay.portlet.journal.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ArticleDisplayTerms extends DisplayTerms {

	public static final String ARTICLE_ID = "searchArticleId";

	public static final String CONTENT = "content";

	public static final String DESCRIPTION = "description";

	public static final String DISPLAY_DATE_GT = "displayDateGT";

	public static final String DISPLAY_DATE_LT = "displayDateLT";

	public static final String FOLDER_ID = "folderId";

	public static final String GROUP_ID = "groupId";

	public static final String NAVIGATION = "navigation";

	public static final String STATUS = "status";

	public static final String STRUCTURE_ID = "structureId";

	public static final String TEMPLATE_ID = "templateId";

	public static final String TITLE = "title";

	public static final String TYPE = "type";

	public static final String VERSION = "version";

	public ArticleDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		articleId = ParamUtil.getString(portletRequest, ARTICLE_ID);
		content = ParamUtil.getString(portletRequest, CONTENT);
		description = ParamUtil.getString(portletRequest, DESCRIPTION);
		folderId = ParamUtil.getLong(portletRequest, FOLDER_ID);
		navigation = ParamUtil.getString(portletRequest, NAVIGATION);
		status = ParamUtil.getString(portletRequest, STATUS);
		structureId = ParamUtil.getString(portletRequest, STRUCTURE_ID);
		templateId = ParamUtil.getString(portletRequest, TEMPLATE_ID);
		title = ParamUtil.getString(portletRequest, TITLE);
		type = ParamUtil.getString(portletRequest, TYPE);
		version = ParamUtil.getDouble(portletRequest, VERSION);

		groupId = setGroupId(portletRequest);
	}

	public String getArticleId() {
		return articleId;
	}

	public String getContent() {
		return content;
	}

	public String getDescription() {
		return description;
	}

	public Date getDisplayDateGT() {
		return displayDateGT;
	}

	public Date getDisplayDateLT() {
		return displayDateLT;
	}

	public long getFolderId() {
		return folderId;
	}

	public List<Long> getFolderIds() {
		if (folderIds != null) {
			return folderIds;
		}

		List<Long> folderIds = new ArrayList<Long>();

		folderIds.add(folderId);

		return folderIds;
	}

	public long getGroupId() {
		return groupId;
	}

	public String getNavigation() {
		return navigation;
	}

	public String getStatus() {
		return status;
	}

	public String getStructureId() {
		return structureId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public double getVersion() {
		return version;
	}

	public String getVersionString() {
		if (version != 0) {
			return String.valueOf(version);
		}
		else {
			return StringPool.BLANK;
		}
	}

	public boolean isNavigationRecent() {
		if (navigation.equals("recent")) {
			return true;
		}

		return false;
	}

	public void setDisplayDateGT(Date displayDateGT) {
		this.displayDateGT = displayDateGT;
	}

	public void setDisplayDateLT(Date displayDateLT) {
		this.displayDateLT = displayDateLT;
	}

	public void setFolderIds(List<Long> folderIds) {
		this.folderIds = folderIds;
	}

	public long setGroupId(PortletRequest portletRequest) {
		groupId = ParamUtil.getLong(portletRequest, GROUP_ID);

		if (groupId != 0) {
			return groupId;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (Validator.isNotNull(structureId) && !structureId.equals("0")) {
			DDMStructure ddmStructure = null;

			try {
				ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
					themeDisplay.getSiteGroupId(),
					PortalUtil.getClassNameId(JournalArticle.class),
					structureId);
			}
			catch (SystemException se) {
			}

			if (ddmStructure != null) {
				return 0;
			}
		}

		if (Validator.isNotNull(templateId) && !templateId.equals("0")) {
			DDMTemplate ddmTemplate = null;

			try {
				ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(
					themeDisplay.getSiteGroupId(),
					PortalUtil.getClassNameId(JournalArticle.class),
					templateId);
			}
			catch (SystemException se) {
			}

			if (ddmTemplate != null) {
				return 0;
			}
		}

		return themeDisplay.getScopeGroupId();
	}

	public void setStatus(String status) {
		this.status = status;
	}

	protected String articleId;
	protected String content;
	protected String description;
	protected Date displayDateGT;
	protected Date displayDateLT;
	protected long folderId;
	protected List<Long> folderIds;
	protected long groupId;
	protected String navigation;
	protected String status;
	protected String structureId;
	protected String templateId;
	protected String title;
	protected String type;
	protected double version;

}