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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Farache
 */
public class Tree implements ResponseElement {

	public static final String CLOSE_UL = "</ul>";

	public static final String OPEN_UL = "<ul>";

	public void addChild(ResponseElement node) {
		_children.add(node);
	}

	@Override
	public String parse() {
		StringBundler sb = new StringBundler(_children.size() * 4 + 4);

		sb.append(OPEN_UL);
		sb.append(StringPool.NEW_LINE);

		for (ResponseElement child : _children) {
			sb.append(child.parse());
		}

		sb.append(CLOSE_UL);
		sb.append(StringPool.NEW_LINE);

		return sb.toString();
	}

	private List<ResponseElement> _children = new ArrayList<ResponseElement>();

}