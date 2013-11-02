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
import com.liferay.portal.kernel.util.StringUtil;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

/**
 * @author Shuyang Zhou
 */
public abstract class ConditionalTagSupport extends TagSupport {

	@Override
	@SuppressWarnings("unused")
	public int doStartTag() throws JspTagException {
		_result = condition();

		if (_var != null) {
			pageContext.setAttribute(_var, _result, _scope);
		}

		if (_result) {
			return EVAL_BODY_INCLUDE;
		}
		else {
			return SKIP_BODY;
		}
	}

	@Override
	public void release() {
		super.release();

		_result = false;
		_scope = PageContext.PAGE_SCOPE;
		_var = null;
	}

	public void setScope(String scope) {
		String scopeLowerCase = StringUtil.toLowerCase(scope);

		if (scopeLowerCase.equals("application")) {
			_scope = PageContext.APPLICATION_SCOPE;
		}
		else if (scopeLowerCase.equals("page")) {
			_scope = PageContext.PAGE_SCOPE;
		}
		else if (scopeLowerCase.equals("request")) {
			_scope = PageContext.REQUEST_SCOPE;
		}
		else if (scopeLowerCase.equals("session")) {
			_scope = PageContext.SESSION_SCOPE;
		}
	}

	public void setVar(String var) {
		_var = var;
	}

	protected abstract boolean condition();

	private boolean _result;
	private int _scope = PageContext.PAGE_SCOPE;
	private String _var;

}