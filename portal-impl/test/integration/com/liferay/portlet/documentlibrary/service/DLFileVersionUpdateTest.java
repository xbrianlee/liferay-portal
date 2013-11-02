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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alexander Chow
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class DLFileVersionUpdateTest extends BaseDLAppTestCase {

	@Test
	public void testWithExtensionWithContent() throws Exception {
		testVersionUpdate(
			_FULL_FILE_NAME, _ZERO_BYTES, ContentTypes.TEXT_PLAIN,
			_FULL_FILE_NAME, CONTENT.getBytes(), ContentTypes.TEXT_PLAIN);
	}

	@Test
	public void testWithExtensionWithoutContent() throws Exception {
		testVersionUpdate(
			_FULL_FILE_NAME, _ZERO_BYTES, ContentTypes.TEXT_PLAIN,
			_FULL_FILE_NAME, _ZERO_BYTES, ContentTypes.TEXT_PLAIN);
	}

	@Test
	public void testWithoutExtensionWithContent() throws Exception {
		testVersionUpdate(
			_BASE_FILE_NAME, _ZERO_BYTES, ContentTypes.APPLICATION_OCTET_STREAM,
			_BASE_FILE_NAME, CONTENT.getBytes(), ContentTypes.TEXT_PLAIN);
	}

	@Test
	public void testWithoutExtensionWithoutContent() throws Exception {
		testVersionUpdate(
			_BASE_FILE_NAME, _ZERO_BYTES, ContentTypes.APPLICATION_OCTET_STREAM,
			_BASE_FILE_NAME, _ZERO_BYTES,
			ContentTypes.APPLICATION_OCTET_STREAM);
	}

	protected void testVersionUpdate(
			String addFileName, byte[] addBytes, String addMimeType,
			String updateFileName, byte[] updateBytes, String updateMimeType)
		throws PortalException, SystemException {

		String description = StringPool.BLANK;
		String changeLog = StringPool.BLANK;

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			group.getGroupId());

		FileEntry fileEntry = DLAppServiceUtil.addFileEntry(
			group.getGroupId(), parentFolder.getFolderId(), addFileName,
			addMimeType, addFileName, description, changeLog, addBytes,
			serviceContext);

		fileEntry = DLAppServiceUtil.updateFileEntry(
			fileEntry.getFileEntryId(), updateFileName, updateMimeType,
			updateFileName, description, changeLog, false, updateBytes,
			serviceContext);

		FileVersion fileVersion = fileEntry.getFileVersion();

		Assert.assertEquals(
			DLFileEntryConstants.VERSION_DEFAULT, fileVersion.getVersion());
		Assert.assertEquals(updateMimeType, fileVersion.getMimeType());
		Assert.assertEquals(updateBytes.length, fileVersion.getSize());
		Assert.assertEquals(
			fileVersion.getExtension(), fileEntry.getExtension());
		Assert.assertEquals(fileVersion.getMimeType(), fileEntry.getMimeType());
		Assert.assertEquals(fileVersion.getSize(), fileEntry.getSize());
	}

	private static final String _BASE_FILE_NAME = "Test";

	private static final String _FULL_FILE_NAME = "Test.txt";

	private static final byte[] _ZERO_BYTES = new byte[0];

}