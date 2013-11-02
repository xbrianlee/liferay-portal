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

import com.liferay.portal.kernel.dao.search.SearchEntry;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.taglib.util.ParamAndPropertyAncestorTagImpl;

import javax.servlet.jsp.JspException;

/**
 * @author Raymond Augé
 */
public abstract class SearchContainerColumnTag
	extends ParamAndPropertyAncestorTagImpl {

	@Override
	@SuppressWarnings("unused")
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	public String getAlign() {
		return align;
	}

	public int getColspan() {
		return colspan;
	}

	public String getCssClass() {
		return cssClass;
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public String getValign() {
		return valign;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValign(String valign) {
		this.valign = valign;
	}

	protected String align = SearchEntry.DEFAULT_ALIGN;
	protected int colspan = SearchEntry.DEFAULT_COLSPAN;
	protected String cssClass = SearchEntry.DEFAULT_CSS_CLASS;
	protected int index = -1;
	protected String name = StringPool.BLANK;
	protected String valign = SearchEntry.DEFAULT_VALIGN;

}