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

package com.liferay.portal.xml;

import com.liferay.portal.kernel.xml.Comment;
import com.liferay.portal.kernel.xml.Visitor;

/**
 * @author Brian Wing Shun Chan
 */
public class CommentImpl extends NodeImpl implements Comment {

	public CommentImpl(org.dom4j.Comment comment) {
		super(comment);

		_comment = comment;
	}

	@Override
	public <T, V extends Visitor<T>> T accept(V visitor) {
		return visitor.visitComment(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommentImpl)) {
			return false;
		}

		org.dom4j.Comment comment = ((CommentImpl)obj).getWrappedComment();

		return _comment.equals(comment);
	}

	public org.dom4j.Comment getWrappedComment() {
		return _comment;
	}

	@Override
	public int hashCode() {
		return _comment.hashCode();
	}

	@Override
	public String toString() {
		return _comment.toString();
	}

	private org.dom4j.Comment _comment;

}