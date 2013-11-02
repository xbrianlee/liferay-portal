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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Brian Wing Shun Chan
 */
public class MessageTag extends TagSupport {

	@Override
	public int doEndTag() throws JspException {
		try {
			String value = StringPool.BLANK;

			HttpServletRequest request =
				(HttpServletRequest)pageContext.getRequest();

			boolean unicode = GetterUtil.getBoolean(
				request.getAttribute(WebKeys.JAVASCRIPT_CONTEXT));

			if (unicode) {
				_unicode = unicode;
			}

			if (_arguments == null) {
				if (!_localizeKey) {
					value = _key;
				}
				else if (_unicode) {
					value = UnicodeLanguageUtil.get(pageContext, _key);
				}
				else {
					value = LanguageUtil.get(pageContext, _key);
				}
			}
			else {
				if (_unicode) {
					value = UnicodeLanguageUtil.format(
						pageContext, _key, _arguments, _translateArguments);
				}
				else {
					value = LanguageUtil.format(
						pageContext, _key, _arguments, _translateArguments);
				}
			}

			JspWriter jspWriter = pageContext.getOut();

			jspWriter.write(value);

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
		finally {
			if (!ServerDetector.isResin()) {
				_arguments = null;
				_key = null;
				_localizeKey = true;
				_translateArguments = true;
				_unicode = false;
			}
		}
	}

	public void setArguments(Object argument) {
		if (argument == null) {
			_arguments = null;

			return;
		}

		Class<?> clazz = argument.getClass();

		if (clazz.isArray()) {
			_arguments = (Object[])argument;
		}
		else {
			_arguments = new Object[] {argument};
		}
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setLocalizeKey(boolean localizeKey) {
		_localizeKey = localizeKey;
	}

	public void setTranslateArguments(boolean translateArguments) {
		_translateArguments = translateArguments;
	}

	public void setUnicode(boolean unicode) {
		_unicode = unicode;
	}

	private Object[] _arguments;
	private String _key;
	private boolean _localizeKey = true;
	private boolean _translateArguments = true;
	private boolean _unicode;

}