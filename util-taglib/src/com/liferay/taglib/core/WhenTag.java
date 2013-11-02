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

package com.liferay.taglib.core;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

/**
 * @author Shuyang Zhou
 */
public class WhenTag extends ConditionalTagSupport {

	@Override
	public int doStartTag() throws JspTagException {
		Tag parentTag = getParent();

		if (!(parentTag instanceof ChooseTag)) {
			throw new JspTagException(
				"The when tag must exist under a choose tag");
		}

		ChooseTag chooseTag = (ChooseTag)parentTag;

		if (!chooseTag.canRun()) {
			return SKIP_BODY;
		}

		if (condition()) {
			chooseTag.markRan();

			return EVAL_BODY_INCLUDE;
		}
		else {
			return SKIP_BODY;
		}
	}

	@Override
	public void release() {
		super.release();

		_test = false;
	}

	public void setTest(boolean test) {
		_test = test;
	}

	@Override
	protected boolean condition() {
		return _test;
	}

	private boolean _test;

}