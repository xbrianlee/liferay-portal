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

package com.liferay.portal.image;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Image;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.NoSuchFileException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jorge Ferrer
 */
public class FileSystemHook extends BaseHook {

	public FileSystemHook() {
		_rootDir = new File(PropsValues.IMAGE_HOOK_FILE_SYSTEM_ROOT_DIR);

		if (!_rootDir.exists()) {
			_rootDir.mkdirs();
		}
	}

	@Override
	public void deleteImage(Image image) {
		File file = getFile(image.getImageId(), image.getType());

		FileUtil.delete(file);
	}

	@Override
	public byte[] getImageAsBytes(Image image)
		throws PortalException, SystemException {

		try {
			File file = getFile(image.getImageId(), image.getType());

			if (!file.exists()) {
				throw new NoSuchFileException(file.getPath());
			}

			return FileUtil.getBytes(file);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	@Override
	public InputStream getImageAsStream(Image image)
		throws PortalException, SystemException {

		try {
			File file = getFile(image.getImageId(), image.getType());

			if (!file.exists()) {
				throw new NoSuchFileException(file.getPath());
			}

			return new FileInputStream(file);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	@Override
	public void updateImage(Image image, String type, byte[] bytes)
		throws SystemException {

		try {
			File file = getFile(image.getImageId(), type);

			FileUtil.write(file, bytes);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	protected String buildPath(String fileNameFragment) {
		int fileNameFragmentLength = fileNameFragment.length();

		if (fileNameFragmentLength <= 2) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(
			fileNameFragmentLength / 2 + fileNameFragmentLength);

		for (int i = 0; i < fileNameFragmentLength; i += 2) {
			if ((i + 2) < fileNameFragmentLength) {
				sb.append(StringPool.SLASH);
				sb.append(fileNameFragment.substring(i, i + 2));
			}
		}

		return sb.toString();
	}

	protected File getFile(long imageId, String type) {
		String path = buildPath(String.valueOf(imageId));

		return new File(
			_rootDir + StringPool.SLASH + path + StringPool.SLASH +
				imageId + StringPool.PERIOD + type);
	}

	private File _rootDir;

}