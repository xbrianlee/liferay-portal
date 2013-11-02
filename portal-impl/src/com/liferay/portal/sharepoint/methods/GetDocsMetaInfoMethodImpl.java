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

package com.liferay.portal.sharepoint.methods;

import com.liferay.portal.sharepoint.Property;
import com.liferay.portal.sharepoint.ResponseElement;
import com.liferay.portal.sharepoint.SharepointRequest;
import com.liferay.portal.sharepoint.SharepointStorage;
import com.liferay.portal.sharepoint.Tree;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Farache
 */
public class GetDocsMetaInfoMethodImpl extends BaseMethodImpl {

	@Override
	public String getMethodName() {
		return _METHOD_NAME;
	}

	@Override
	public String getRootPath(SharepointRequest sharepointRequest) {
		String urlList = sharepointRequest.getParameterValue("url_list");

		return urlList.substring(1, urlList.length() - 1);
	}

	@Override
	protected List<ResponseElement> getElements(
			SharepointRequest sharepointRequest)
		throws Exception {

		List<ResponseElement> elements = new ArrayList<ResponseElement>();

		SharepointStorage storage = sharepointRequest.getSharepointStorage();

		Tree documentListTree = new Tree();

		try {
			documentListTree.addChild(
				storage.getDocumentTree(sharepointRequest));
		}
		catch (Exception e1) {
			if (e1 instanceof NoSuchFileEntryException) {
				try {
					documentListTree.addChild(
						storage.getFolderTree(sharepointRequest));
				}
				catch (Exception e2) {
				}
			}
		}

		Property documentProperty = new Property(
			"document_list", documentListTree);

		elements.add(documentProperty);

		elements.add(new Property("urldirs", new Tree()));

		return elements;
	}

	private static final String _METHOD_NAME = "getDocsMetaInfo";

}