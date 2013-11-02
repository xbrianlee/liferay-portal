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

package com.liferay.portal.kernel.dao.search;

import com.liferay.portal.kernel.util.StringPool;

import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class SearchEntry implements Cloneable {

	public static final String DEFAULT_ALIGN = "left";

	public static final int DEFAULT_COLSPAN = 1;

	public static final String DEFAULT_CSS_CLASS = StringPool.BLANK;

	public static final String DEFAULT_VALIGN = "middle";

	public String getAlign() {
		return _align;
	}

	public int getColspan() {
		return _colspan;
	}

	public String getCssClass() {
		return _cssClass;
	}

	public int getIndex() {
		return _index;
	}

	public String getValign() {
		return _valign;
	}

	public abstract void print(PageContext pageContext) throws Exception;

	public void setAlign(String align) {
		_align = align;
	}

	public void setColspan(int colspan) {
		_colspan = colspan;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setIndex(int index) {
		_index = index;
	}

	public void setValign(String valign) {
		_valign = valign;
	}

	private String _align = DEFAULT_ALIGN;
	private int _colspan = DEFAULT_COLSPAN;
	private String _cssClass = DEFAULT_CSS_CLASS;
	private int _index;
	private String _valign = DEFAULT_VALIGN;

}