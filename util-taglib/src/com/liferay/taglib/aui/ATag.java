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

package com.liferay.taglib.aui;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.aui.base.BaseATag;
import com.liferay.taglib.util.InlineUtil;

import java.io.IOException;

import java.util.Map;

import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

/**
 * @author Julio Camarero
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ATag extends BaseATag {

	protected boolean isOpensNewWindow() {
		String target = getTarget();

		if ((target != null) &&
			(target.equals("_blank") || target.equals("_new"))) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	protected int processEndTag() throws Exception {
		JspWriter jspWriter = pageContext.getOut();

		if (Validator.isNotNull(getHref())) {
			if (isOpensNewWindow()) {
				jspWriter.write("<span class=\"opens-new-window-accessible\">");
				jspWriter.write(
					LanguageUtil.get(pageContext, "opens-new-window"));
				jspWriter.write("</span>");
			}

			jspWriter.write("</a>");
		}
		else {
			jspWriter.write("</span>");
		}

		return EVAL_PAGE;
	}

	@Override
	protected int processStartTag() throws Exception {
		JspWriter jspWriter = pageContext.getOut();

		String ariaRole = getAriaRole();
		String cssClass = getCssClass();
		Map<String, Object> data = getData();
		String href = getHref();
		String id = getId();
		String label = getLabel();
		String lang = getLang();
		String namespace = _getNamespace();
		String onClick = getOnClick();
		String target = getTarget();
		String title = getTitle();

		if (Validator.isNotNull(href)) {
			jspWriter.write("<a ");

			jspWriter.write("href=\"");
			jspWriter.write(HtmlUtil.escape(href));
			jspWriter.write("\" ");

			if (Validator.isNotNull(target)) {
				jspWriter.write("target=\"");
				jspWriter.write(target);
				jspWriter.write("\" ");
			}
		}
		else {
			jspWriter.write("<span ");
		}

		if (Validator.isNotNull(cssClass)) {
			jspWriter.write("class=\"");
			jspWriter.write(cssClass);
			jspWriter.write("\" ");
		}

		if (Validator.isNotNull(id)) {
			jspWriter.write("id=\"");
			jspWriter.write(namespace);
			jspWriter.write(id);
			jspWriter.write("\" ");
		}

		if (Validator.isNotNull(lang)) {
			jspWriter.write("lang=\"");
			jspWriter.write(lang);
			jspWriter.write("\" ");
		}

		if (Validator.isNotNull(onClick)) {
			jspWriter.write("onClick=\"");
			jspWriter.write(onClick);
			jspWriter.write("\" ");
		}

		if (Validator.isNotNull(ariaRole)) {
			jspWriter.write("role=\"");
			jspWriter.write(ariaRole);
			jspWriter.write("\" ");
		}

		if (Validator.isNotNull(title) || isOpensNewWindow()) {
			jspWriter.write("title=\"");

			if (Validator.isNotNull(title)) {
				jspWriter.write(LanguageUtil.get(pageContext, title));
			}

			if (isOpensNewWindow()) {
				jspWriter.write(
					LanguageUtil.get(pageContext, "opens-new-window"));
			}

			jspWriter.write("\" ");
		}

		if (data != null) {
			jspWriter.write(AUIUtil.buildData(data));
		}

		_writeDynamicAttributes(jspWriter);

		jspWriter.write(">");

		if (Validator.isNotNull(label)) {
			jspWriter.write(LanguageUtil.get(pageContext, label));
		}

		return EVAL_BODY_INCLUDE;
	}

	private String _getNamespace() {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		PortletResponse portletResponse = (PortletResponse)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);

		String namespace = StringPool.BLANK;

		boolean useNamespace = GetterUtil.getBoolean(
			(String)request.getAttribute("aui:form:useNamespace"), true);

		if ((portletResponse != null) && useNamespace) {
			namespace = portletResponse.getNamespace();
		}

		return namespace;
	}

	private void _writeDynamicAttributes(JspWriter jspWriter)
		throws IOException {

		String dynamicAttributesString = InlineUtil.buildDynamicAttributes(
			getDynamicAttributes());

		if (Validator.isNotNull(dynamicAttributesString)) {
			jspWriter.write(dynamicAttributesString);
		}
	}

}