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

import com.liferay.portal.kernel.servlet.taglib.TagSupport;

/**
 * @author Shuyang Zhou
 */
public class ChooseTag extends TagSupport {

	public boolean canRun() {
		return !_ran;
	}

	@Override
	public int doStartTag() {
		_ran = false;

		return EVAL_BODY_INCLUDE;
	}

	public void markRan() {
		if (_ran) {
			throw new IllegalStateException("Another subtag has already run");
		}

		_ran = true;
	}

	@Override
	public void release() {
		super.release();

		_ran = false;
	}

	private boolean _ran;

}