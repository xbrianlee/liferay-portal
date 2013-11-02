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

package com.liferay.taglib.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Brian Wing Shun Chan
 */
public class GetUrlTag extends TagSupport {

	@Override
	public int doEndTag() throws JspException {
		try {
			WebCacheItem wci = new GetUrlWebCacheItem(_url, _expires);

			String content = (String)WebCachePoolUtil.get(
				GetUrlTag.class.getName() + StringPool.PERIOD + _url, wci);

			if (Validator.isNotNull(_var)) {
				pageContext.setAttribute(_var, content);
			}
			else {
				JspWriter jspWriter = pageContext.getOut();

				jspWriter.print(content);
			}

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setExpires(long expires) {
		_expires = expires;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public void setVar(String var) {
		_var = var;
	}

	private long _expires = Time.WEEK;
	private String _url;
	private String _var;

}