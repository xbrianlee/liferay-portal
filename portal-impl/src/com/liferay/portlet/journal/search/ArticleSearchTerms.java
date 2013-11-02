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

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Date;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ArticleSearchTerms extends ArticleDisplayTerms {

	public ArticleSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		articleId = DAOParamUtil.getString(portletRequest, ARTICLE_ID);
		content = DAOParamUtil.getString(portletRequest, CONTENT);
		description = DAOParamUtil.getString(portletRequest, DESCRIPTION);
		status = ParamUtil.getString(portletRequest, STATUS);
		structureId = DAOParamUtil.getString(portletRequest, STRUCTURE_ID);
		templateId = DAOParamUtil.getString(portletRequest, TEMPLATE_ID);
		title = DAOParamUtil.getString(portletRequest, TITLE);
		type = DAOParamUtil.getString(portletRequest, TYPE);
		version = ParamUtil.getDouble(portletRequest, VERSION);

		groupId = setGroupId(portletRequest);
	}

	public Date getReviewDate() {
		if (status.equals("review")) {
			return new Date();
		}
		else {
			return null;
		}
	}

	public int getStatusCode() {
		if (status.equals("approved")) {
			return WorkflowConstants.STATUS_APPROVED;
		}
		else if (status.equals("draft")) {
			return WorkflowConstants.STATUS_DRAFT;
		}
		else if (status.equals("expired")) {
			return WorkflowConstants.STATUS_EXPIRED;
		}
		else if (status.equals("pending")) {
			return WorkflowConstants.STATUS_PENDING;
		}
		else {
			return WorkflowConstants.STATUS_ANY;
		}
	}

	public Double getVersionObj() {
		if (version == 0) {
			return null;
		}
		else {
			return new Double(version);
		}
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public void setStructureId(String structureId) {
		this.structureId = structureId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setVersion(double version) {
		this.version = version;
	}

}