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

package com.liferay.portlet.journal.lar;

import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portlet.journal.model.JournalArticle;

/**
 * <p>
 * Provides the strategy for creating new content when new Journal content is
 * imported into a layout set from a LAR. The default strategy implemented by
 * this class is to return zero for the author and approval user IDs, which
 * causes the default user ID import strategy to be used. Content will be added
 * as is with no transformations.
 * </p>
 *
 * @author Joel Kozikowski
 * @see    com.liferay.portlet.journal.lar.JournalContentPortletDataHandler
 * @see    com.liferay.portlet.journal.lar.JournalPortletDataHandler
 */
public class JournalCreationStrategyImpl implements JournalCreationStrategy {

	@Override
	public boolean addGroupPermissions(
			PortletDataContext context, Object journalObj)
		throws Exception {

		return true;
	}

	@Override
	public boolean addGuestPermissions(
			PortletDataContext context, Object journalObj)
		throws Exception {

		return true;
	}

	@Override
	public long getAuthorUserId(PortletDataContext context, Object journalObj)
		throws Exception {

		return JournalCreationStrategy.USE_DEFAULT_USER_ID_STRATEGY;
	}

	@Override
	public String getTransformedContent(
			PortletDataContext context, JournalArticle newArticle)
		throws Exception {

		return JournalCreationStrategy.ARTICLE_CONTENT_UNCHANGED;
	}

}