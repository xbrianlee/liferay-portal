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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 */
public class ImageImpl extends ImageBaseImpl {

	public ImageImpl() {
	}

	@Override
	public byte[] getTextObj() {
		if (_textObj != null) {
			return _textObj;
		}

		long imageId = getImageId();

		try {
			DLFileEntry dlFileEntry =
				DLFileEntryLocalServiceUtil.fetchFileEntryByAnyImageId(imageId);

			InputStream is = null;

			if ((dlFileEntry != null) &&
				(dlFileEntry.getLargeImageId() == imageId)) {

				is = DLStoreUtil.getFileAsStream(
					dlFileEntry.getCompanyId(),
					dlFileEntry.getDataRepositoryId(), dlFileEntry.getName());
			}
			else {
				is = DLStoreUtil.getFileAsStream(
					_DEFAULT_COMPANY_ID, _DEFAULT_REPOSITORY_ID, getFileName());
			}

			byte[] bytes = FileUtil.getBytes(is);

			_textObj = bytes;
		}
		catch (Exception e) {
			_log.error("Error reading image " + imageId, e);
		}

		return _textObj;
	}

	@Override
	public void setTextObj(byte[] textObj) {
		_textObj = textObj;
	}

	protected String getFileName() {
		return getImageId() + StringPool.PERIOD + getType();
	}

	private static final long _DEFAULT_COMPANY_ID = 0;

	private static final long _DEFAULT_REPOSITORY_ID = 0;

	private static Log _log = LogFactoryUtil.getLog(ImageImpl.class);

	private byte[] _textObj;

}