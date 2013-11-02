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

package com.liferay.portal.parsers.creole.ast;

import com.liferay.portal.parsers.creole.visitor.ASTVisitor;

/**
 * @author Miguel Pastor
 */
public class HeadingNode
	extends BaseParentableNode implements Comparable<HeadingNode> {

	public HeadingNode(CollectionNode collectionNode, int level) {
		super(collectionNode);

		_level = level;
	}

	public HeadingNode(int level) {
		_level = level;
	}

	@Override
	public void accept(ASTVisitor astVisitor) {
		astVisitor.visit(this);
	}

	@Override
	public int compareTo(HeadingNode headingNode) {
		if (_level < headingNode.getLevel()) {
			return -1;
		}
		else if (_level > headingNode.getLevel()) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public int getLevel() {
		return _level;
	}

	public void setLevel(int level) {
		_level = level;
	}

	private int _level;

}