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

package com.liferay.portlet.asset.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portlet.asset.AssetTagException;
import com.liferay.portlet.messageboards.model.MBDiscussion;

/**
 * @author Brian Wing Shun Chan
 */
public class MinimalAssetEntryValidator extends BaseAssetEntryValidator {

	@Override
	public void validate(
			long groupId, String className, long[] categoryIds,
			String[] tagNames)
		throws PortalException {

		if (!className.equals(MBDiscussion.class.getName()) &&
			ArrayUtil.isEmpty(tagNames)) {

			throw new AssetTagException(AssetTagException.AT_LEAST_ONE_TAG);
		}
	}

}