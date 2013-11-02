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

package com.liferay.portal.kernel.upgrade.util;

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedWriter;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.FileWriter;

import java.util.Iterator;

/**
 * @author Brian Wing Shun Chan
 */
public class ValueMapperUtil {

	public static void persist(
			ValueMapper valueMapper, String tmpDir, String fileName)
		throws Exception {

		FileUtil.mkdirs(tmpDir);

		UnsyncBufferedWriter unsyncBufferedWriter = new UnsyncBufferedWriter(
			new FileWriter(tmpDir + "/" + fileName + ".txt"));

		try {
			Iterator<Object> itr = valueMapper.iterator();

			while (itr.hasNext()) {
				Object oldValue = itr.next();

				Object newValue = valueMapper.getNewValue(oldValue);

				unsyncBufferedWriter.write(
					oldValue + StringPool.EQUAL + newValue);

				if (itr.hasNext()) {
					unsyncBufferedWriter.write(StringPool.NEW_LINE);
				}
			}
		}
		finally {
			unsyncBufferedWriter.close();
		}
	}

}