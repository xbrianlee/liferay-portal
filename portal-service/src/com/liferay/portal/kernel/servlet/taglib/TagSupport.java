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

package com.liferay.portal.kernel.servlet.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-13878.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class TagSupport implements Tag {

	public static Tag findAncestorWithClass(Tag fromTag, Class<?> clazz) {
		boolean isInterface = false;

		if ((fromTag == null) || (clazz == null) ||
			(!Tag.class.isAssignableFrom(clazz) &&
			 !(isInterface = clazz.isInterface()))) {

			return null;
		}

		while (true) {
			Tag parentTag = fromTag.getParent();

			if (parentTag == null) {
				return null;
			}

			if ((isInterface && clazz.isInstance(parentTag)) ||
				clazz.isAssignableFrom(parentTag.getClass())) {

				return parentTag;
			}

			fromTag = parentTag;
		}
	}

	@Override
	@SuppressWarnings("unused")
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	@SuppressWarnings("unused")
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public Tag getParent() {
		return _parent;
	}

	@Override
	public void release() {
		_parent = null;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	@Override
	public void setParent(Tag tag) {
		_parent = tag;
	}

	protected PageContext pageContext;

	private Tag _parent;

}