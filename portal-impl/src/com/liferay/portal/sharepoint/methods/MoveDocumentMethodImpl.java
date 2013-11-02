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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.sharepoint.Property;
import com.liferay.portal.sharepoint.ResponseElement;
import com.liferay.portal.sharepoint.SharepointRequest;
import com.liferay.portal.sharepoint.SharepointStorage;
import com.liferay.portal.sharepoint.SharepointUtil;
import com.liferay.portal.sharepoint.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Farache
 */
public class MoveDocumentMethodImpl extends BaseMethodImpl {

	@Override
	public String getMethodName() {
		return _METHOD_NAME;
	}

	@Override
	public String getRootPath(SharepointRequest sharepointRequest) {
		return sharepointRequest.getParameterValue("oldUrl");
	}

	@Override
	protected List<ResponseElement> getElements(
			SharepointRequest sharepointRequest)
		throws Exception {

		List<ResponseElement> elements = new ArrayList<ResponseElement>();

		String oldUrl = sharepointRequest.getParameterValue("oldUrl");

		oldUrl = SharepointUtil.replaceBackSlashes(oldUrl);

		String newUrl = sharepointRequest.getParameterValue("newUrl");

		newUrl = SharepointUtil.replaceBackSlashes(oldUrl);

		SharepointStorage storage = sharepointRequest.getSharepointStorage();

		Tree[] results = storage.moveDocument(sharepointRequest);

		elements.add(new Property("message", StringPool.BLANK));
		elements.add(new Property("oldUrl", oldUrl));
		elements.add(new Property("newUrl", newUrl));

		Property documentListProperty = new Property(
			"document_list", new Tree());

		elements.add(documentListProperty);

		elements.add(new Property("moved_docs", results[0]));
		elements.add(new Property("moved_dirs", results[1]));

		return elements;
	}

	private static final String _METHOD_NAME = "move document";

}