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

package com.liferay.portlet.documentlibrary.service;

import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.util.DLAppTestUtil;

import org.junit.After;
import org.junit.Before;

/**
 * @author Alexander Chow
 */
public abstract class BaseDLAppTestCase {

	@Before
	public void setUp() throws Exception {
		group = GroupTestUtil.addGroup();

		parentFolder = DLAppTestUtil.addFolder(
			group.getGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			"Test Folder", true);
	}

	@After
	public void tearDown() throws Exception {
		PrincipalThreadLocal.setName(TestPropsValues.getUserId());

		GroupLocalServiceUtil.deleteGroup(group);
	}

	protected static final String CONTENT =
		"Content: Enterprise. Open Source. For Life.";

	protected Group group;
	protected Folder parentFolder;

}