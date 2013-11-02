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

package com.liferay.portlet.documentlibrary.store;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portlet.documentlibrary.NoSuchDirectoryException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Vilmos Papp
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class AdvancedFileSystemStoreTest {

	@Test
	public void testUpdateFileWithMoveFiles() throws Exception {
		Object[] data = initStoreData();

		long companyId = (Long)data[0];
		long repositoryId = (Long)data[1];
		long newRepositoryId = ServiceTestUtil.nextLong();

		try {
			String[] fileNames = _store.getFileNames(companyId, repositoryId);

			for (String fileName : fileNames) {
				_store.updateFile(
					companyId, repositoryId, newRepositoryId, fileName);

				Assert.assertTrue(
					fileName + " was not found in " + newRepositoryId,
					_store.hasFile(companyId, newRepositoryId, fileName));
				Assert.assertFalse(
					fileName + " was found in " + repositoryId,
					_store.hasFile(companyId, repositoryId, fileName));
			}
		}
		finally {
			try {
				_store.deleteDirectory(
					companyId, repositoryId, StringPool.BLANK);
			}
			catch (NoSuchDirectoryException nsde) {
			}

			try {
				_store.deleteDirectory(
					companyId, newRepositoryId, StringPool.BLANK);
			}
			catch (NoSuchDirectoryException nsde) {
			}
		}
	}

	protected Object[] initStoreData() throws Exception {
		long companyId = ServiceTestUtil.nextLong();
		long repositoryId = ServiceTestUtil.nextLong();

		for (int i = 0; i < _FILE_COUNT; i++) {
			String fileName = String.valueOf(i) + _FILE_NAME_EXTENSION;

			_store.addFile(companyId, repositoryId, fileName, _FILE_DATA);
		}

		return new Object[] {companyId, repositoryId};
	}

	private static final int _DATA_SIZE = 100;

	private static final int _FILE_COUNT = 200;

	private static final byte[] _FILE_DATA = new byte[_DATA_SIZE];

	private static final String _FILE_NAME_EXTENSION = ".txt";

	private static Store _store = new AdvancedFileSystemStore();

	static {
		for (int i = 0; i < _DATA_SIZE; i++) {
			_FILE_DATA[i] = (byte)i;
		}
	}

}