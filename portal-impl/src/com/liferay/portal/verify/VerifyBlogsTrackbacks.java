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
import com.liferay.portlet.blogs.util.LinkbackConsumerUtil;
import com.liferay.portlet.messageboards.model.MBDiscussion;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

import java.util.List;

/**
 * <p>
 * This class looks at every blog comment to see if it is a trackback and
 * verifies that the source URL is a valid URL. Do not run this unless you want
 * to do this.
 * </p>
 *
 * @author Alexander Chow
 */
public class VerifyBlogsTrackbacks extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		List<MBDiscussion> discussions =
			MBMessageLocalServiceUtil.getDiscussions(
				BlogsEntry.class.getName());

		for (MBDiscussion discussion : discussions) {
			long entryId = discussion.getClassPK();
			long threadId = discussion.getThreadId();

			try {
				BlogsEntry entry = BlogsEntryLocalServiceUtil.getBlogsEntry(
					entryId);

				List<MBMessage> messages =
					MBMessageLocalServiceUtil.getThreadMessages(
						threadId, WorkflowConstants.STATUS_APPROVED);

				for (MBMessage message : messages) {
					LinkbackConsumerUtil.verifyPost(entry, message);
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		VerifyBlogsTrackbacks.class);

}