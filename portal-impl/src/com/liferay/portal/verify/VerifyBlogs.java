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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;

import java.util.List;

/**
 * @author Raymond Augé
 */
public class VerifyBlogs extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		updateEntryAssets();
		verifyStatus();
	}

	protected void updateEntryAssets() throws Exception {
		List<BlogsEntry> entries =
			BlogsEntryLocalServiceUtil.getNoAssetEntries();

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Processing " + entries.size() + " entries with no asset");
		}

		for (BlogsEntry entry : entries) {
			try {
				BlogsEntryLocalServiceUtil.updateAsset(
					entry.getUserId(), entry, null, null, null);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to update asset for entry " +
							entry.getEntryId() + ": " + e.getMessage());
				}
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Assets verified for entries");
		}
	}

	protected void verifyStatus() throws Exception {
		runSQL(
			"update BlogsEntry set status = " +
				WorkflowConstants.STATUS_APPROVED + " where status is null");
	}

	private static Log _log = LogFactoryUtil.getLog(VerifyBlogs.class);

}