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

/**
 * @author Alexander Chow
 */
public class VerifyProcessSuite extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verify(new VerifyProperties());

		verify(new VerifyDB2());
		verify(new VerifyMySQL());
		verify(new VerifyOracle());
		verify(new VerifySQLServer());

		verify(new VerifyUUID());

		verify(new VerifyPermission());
		verify(new VerifyGroup());
		verify(new VerifyRole());

		verify(new VerifyAsset());
		verify(new VerifyAuditedModel());
		verify(new VerifyBlogs());
		verify(new VerifyBookmarks());
		verify(new VerifyDocumentLibrary());
		verify(new VerifyDynamicDataMapping());
		verify(new VerifyGroupId());
		verify(new VerifyJournal());
		verify(new VerifyLayout());
		verify(new VerifyMessageBoards());
		verify(new VerifyOrganization());
		verify(new VerifyResourcePermissions());
		verify(new VerifyUser());
		verify(new VerifyWiki());
		verify(new VerifyWorkflow());

		// VerifyBlogsTrackbacks looks at every blog comment to see if it is a
		// trackback and verifies that the source URL is a valid URL.

		//verify(new VerifyBlogsTrackbacks());
	}

}