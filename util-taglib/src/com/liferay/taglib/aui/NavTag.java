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
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.taglib.aui.base.BaseNavTag;

import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 * @author Nathan Cavanaugh
 * @author Julio Camarero
 */
public class NavTag extends BaseNavTag {

	@Override
	public int doStartTag() throws JspException {
		NavBarTag navBarTag = (NavBarTag)findAncestorWithClass(
			this, NavBarTag.class);

		if ((navBarTag != null) &&
			(!_calledCollapsibleSetter || getCollapsible())) {

			setCollapsible(true);

			ThemeDisplay themeDisplay = (ThemeDisplay)pageContext.getAttribute(
				"themeDisplay");

			StringBundler sb = navBarTag.getResponsiveButtonsSB();

			sb.append("<a class=\"btn btn-navbar\" id=\"");
			sb.append(_getNamespacedId());
			sb.append("NavbarBtn\" ");
			sb.append("data-navId=\"");
			sb.append(_getNamespacedId());
			sb.append("\">");

			String icon = getIcon();

			if (Validator.isNull(icon)) {
				sb.append("<span class=\"icon-bar\"></span>");
				sb.append("<span class=\"icon-bar\"></span>");
				sb.append("<span class=\"icon-bar\"></span>");
			}
			else if (icon.equals("user") && themeDisplay.isSignedIn()) {
				try {
					User user = themeDisplay.getUser();

					sb.append("<img alt=\"");
					sb.append(LanguageUtil.get(pageContext, "my-account"));
					sb.append("\" class=\"user-avatar-image\" ");
					sb.append("src=\"");
					sb.append(user.getPortraitURL(themeDisplay));
					sb.append("\">");
				}
				catch (Exception e) {
					throw new JspException(e);
				}
			}
			else {
				sb.append("<i class=\"icon-");
				sb.append(icon);
				sb.append("\"></i>");
			}

			sb.append("</a>");
		}

		return super.doStartTag();
	}

	@Override
	public void setCollapsible(boolean collapsible) {
		super.setCollapsible(collapsible);

		_calledCollapsibleSetter = true;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_calledCollapsibleSetter = false;
		_namespacedId = null;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		super.setAttributes(request);

		setNamespacedAttribute(request, "id", _getNamespacedId());
	}

	private String _getNamespacedId() {
		if (Validator.isNotNull(_namespacedId)) {
			return _namespacedId;
		}

		_namespacedId = getId();

		if (Validator.isNull(_namespacedId)) {
			_namespacedId = StringUtil.randomId();
		}

		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		PortletResponse portletResponse = (PortletResponse)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);

		if ((portletResponse != null) && getUseNamespace()) {
			_namespacedId = portletResponse.getNamespace() + _namespacedId;
		}

		return _namespacedId;
	}

	private boolean _calledCollapsibleSetter;
	private String _namespacedId;

}