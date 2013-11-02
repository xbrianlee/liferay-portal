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

package com.liferay.portal.kernel.util;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Ferrer
 */
public class TreeView implements Serializable {

	public TreeView() {
		this(new ArrayList<TreeNodeView>(), 0);
	}

	public TreeView(List<TreeNodeView> list, int depth) {
		_list = list;
		_depth = depth;
	}

	public int getDepth() {
		return _depth;
	}

	public List<TreeNodeView> getList() {
		return _list;
	}

	private int _depth;
	private List<TreeNodeView> _list;

}