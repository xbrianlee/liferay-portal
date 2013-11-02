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

package com.liferay.portal.search.lucene.cluster;

import com.liferay.portal.kernel.cluster.Address;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.lucene.LuceneHelperUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Shuyang Zhou
 * @author Tina Tian
 */
public class LuceneClusterUtil {

	public static void loadIndexesFromCluster(long companyId)
		throws SystemException {

		LuceneHelperUtil.loadIndexesFromCluster(companyId);
	}

	public static void loadIndexesFromCluster(
			long[] companyIds, Address bootupAddress)
		throws SystemException {

		if (bootupAddress == null) {
			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"Start loading Lucene index files from cluster node " +
					bootupAddress);
		}

		InputStream inputStream = null;

		for (long companyId : companyIds) {
			try {
				inputStream =
					LuceneHelperUtil.getLoadIndexesInputStreamFromCluster(
						companyId, bootupAddress);

				LuceneHelperUtil.loadIndex(companyId, inputStream);
			}
			catch (SystemException se) {
				throw se;
			}
			catch (IOException ioe) {
				throw new SystemException(ioe);
			}
			finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					}
					catch (IOException ioe) {
						throw new SystemException(ioe);
					}
				}
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LuceneClusterUtil.class);

}