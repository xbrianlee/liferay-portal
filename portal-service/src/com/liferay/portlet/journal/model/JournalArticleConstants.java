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

package com.liferay.portlet.journal.model;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Alexander Chow
 */
public class JournalArticleConstants {

	public static final int[] ASSET_ENTRY_CREATION_STATUSES = {
		WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_EXPIRED
	};

	public static final String CANONICAL_URL_SEPARATOR = "/-/";

	public static final long CLASSNAME_ID_DEFAULT = 0;

	public static final String PORTLET = "portlet";

	public static final String STAND_ALONE = "stand-alone";

	public static final String[] TYPES = PropsUtil.getArray(
		PropsKeys.JOURNAL_ARTICLE_TYPES);

	public static final double VERSION_DEFAULT = 1.0;

}