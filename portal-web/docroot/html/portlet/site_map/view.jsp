<%--
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
--%>

<%@ include file="/html/portlet/site_map/init.jsp" %>

<%
List<Layout> rootLayouts = LayoutLocalServiceUtil.getLayouts(layout.getGroupId(), layout.isPrivateLayout(), rootLayoutId);

long portletDisplayDDMTemplateId = PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId(displayStyleGroupId, displayStyle);
%>

<c:choose>
	<c:when test="<%= portletDisplayDDMTemplateId > 0 %>">
		<%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, portletDisplayDDMTemplateId, rootLayouts) %>
	</c:when>
	<c:otherwise>

		<%
		StringBundler sb = new StringBundler();

		_buildSiteMap(layout, rootLayouts, rootLayout, includeRootInTree, displayDepth, showCurrentPage, useHtmlTitle, showHiddenPages, 1, themeDisplay, sb);
		%>

		<%= sb.toString() %>
	</c:otherwise>
</c:choose>

<%!
private void _buildLayoutView(Layout layout, String cssClass, boolean useHtmlTitle, ThemeDisplay themeDisplay, StringBundler sb) throws Exception {
	String layoutURL = PortalUtil.getLayoutURL(layout, themeDisplay);
	String target = PortalUtil.getLayoutTarget(layout);

	sb.append("<a href=\"");
	sb.append(layoutURL);
	sb.append("\" ");
	sb.append(target);

	if (Validator.isNotNull(cssClass)) {
		sb.append(" class=\"");
		sb.append(cssClass);
		sb.append("\" ");
	}

	sb.append("> ");

	String layoutName = layout.getName(themeDisplay.getLocale());

	if (useHtmlTitle) {
		layoutName = layout.getHTMLTitle(themeDisplay.getLocale());
	}

	sb.append(layoutName);
	sb.append("</a>");
}

private void _buildSiteMap(Layout layout, List<Layout> layouts, Layout rootLayout, boolean includeRootInTree, int displayDepth, boolean showCurrentPage, boolean useHtmlTitle, boolean showHiddenPages, int curDepth, ThemeDisplay themeDisplay, StringBundler sb) throws Exception {
	if (layouts.isEmpty()) {
		return;
	}

	sb.append("<ul>");

	if (includeRootInTree && (rootLayout != null) && (curDepth == 1)) {
		sb.append("<li>");

		String cssClass = "root";

		if (rootLayout.getPlid() == layout.getPlid()) {
			cssClass += " current";
		}

		_buildLayoutView(rootLayout, cssClass, useHtmlTitle, themeDisplay, sb);

		_buildSiteMap(layout, layouts, rootLayout, includeRootInTree, displayDepth, showCurrentPage, useHtmlTitle, showHiddenPages, curDepth +1, themeDisplay, sb);

		sb.append("</li>");
	}
	else {
		for (Layout curLayout : layouts) {
			if ((showHiddenPages || !curLayout.isHidden()) && LayoutPermissionUtil.contains(themeDisplay.getPermissionChecker(), curLayout, ActionKeys.VIEW)) {
				sb.append("<li>");

				String cssClass = StringPool.BLANK;

				if (curLayout.getPlid() == layout.getPlid()) {
					cssClass = "current";
				}

				_buildLayoutView(curLayout, cssClass, useHtmlTitle, themeDisplay, sb);

				if ((displayDepth == 0) || (displayDepth > curDepth)) {
					if (showHiddenPages) {
						_buildSiteMap(layout, curLayout.getChildren(), rootLayout, includeRootInTree, displayDepth, showCurrentPage, useHtmlTitle, showHiddenPages, curDepth + 1, themeDisplay, sb);
					}
					else {
						_buildSiteMap(layout, curLayout.getChildren(themeDisplay.getPermissionChecker()), rootLayout, includeRootInTree, displayDepth, showCurrentPage, useHtmlTitle, showHiddenPages, curDepth + 1, themeDisplay, sb);
					}
				}

				sb.append("</li>");
			}
		}
	}

	sb.append("</ul>");
}
%>