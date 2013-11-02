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

package com.liferay.portal.sharepoint;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Bruno Farache
 */
public class GroupSharepointStorageImpl extends BaseSharepointStorageImpl {

	@Override
	public Tree getFoldersTree(SharepointRequest sharepointRequest)
		throws Exception {

		Tree foldersTree = new Tree();

		String rootPath = sharepointRequest.getRootPath();

		for (String token : SharepointUtil.getStorageTokens()) {
			String path = rootPath.concat(StringPool.SLASH).concat(token);

			foldersTree.addChild(getFolderTree(path));
		}

		foldersTree.addChild(getFolderTree(rootPath));

		return foldersTree;
	}

}