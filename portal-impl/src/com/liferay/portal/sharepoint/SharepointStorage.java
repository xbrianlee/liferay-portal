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

import com.liferay.portal.kernel.xml.Element;

import java.io.InputStream;

import java.util.List;

/**
 * @author Bruno Farache
 */
public interface SharepointStorage {

	public void addDocumentElements(
			SharepointRequest sharepointRequest, Element element)
		throws Exception;

	public void createFolder(SharepointRequest sharepointRequest)
		throws Exception;

	public InputStream getDocumentInputStream(
			SharepointRequest sharepointRequest)
		throws Exception;

	public Tree getDocumentsTree(SharepointRequest sharepointRequest)
		throws Exception;

	public Tree getDocumentTree(SharepointRequest sharepointRequest)
		throws Exception;

	public Tree getFoldersTree(SharepointRequest sharepointRequest)
		throws Exception;

	public Tree getFolderTree(SharepointRequest sharepointRequest)
		throws Exception;

	public void getParentFolderIds(
			long groupId, String path, List<Long> folderIds)
		throws Exception;

	public Tree[] moveDocument(SharepointRequest sharepointRequest)
		throws Exception;

	public void putDocument(SharepointRequest sharepointRequest)
		throws Exception;

	public Tree[] removeDocument(SharepointRequest sharepointRequest)
		throws Exception;

}