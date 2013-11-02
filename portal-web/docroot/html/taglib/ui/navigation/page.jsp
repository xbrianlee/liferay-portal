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

<%@ include file="/html/taglib/ui/navigation/init.jsp" %>

<c:if test="<%= layout != null %>">

	<%
	Layout rootLayout = null;
	boolean hidden = false;

	List<Layout> branchLayouts = new ArrayList<Layout>();

	branchLayouts.add(layout);
	branchLayouts.addAll(layout.getAncestors());

	if (rootLayoutType.equals("relative")) {
		if ((rootLayoutLevel >= 0) && (rootLayoutLevel < branchLayouts.size())) {
			rootLayout = branchLayouts.get(rootLayoutLevel);
		}
		else {
			rootLayout = null;
		}
	}
	else if (rootLayoutType.equals("absolute")) {
		int ancestorIndex = branchLayouts.size() - rootLayoutLevel;

		if ((ancestorIndex >= 0) && (ancestorIndex < branchLayouts.size())) {
			rootLayout = branchLayouts.get(ancestorIndex);
		}
		else if (ancestorIndex == branchLayouts.size()) {
			rootLayout = null;
		}
		else {
			hidden = true;
		}
	}

	StringBundler sb = new StringBundler();

	if (!hidden) {
		_buildNavigation(rootLayout, layout, branchLayouts, themeDisplay, 1, includedLayouts, nestedChildren, sb);
	}
	%>

	<div class="nav-menu nav-menu-style-<%= bulletStyle %>">
		<c:choose>
			<c:when test='<%= headerType.equals("root-layout") && (rootLayout != null) %>'>
				<h2>
					<a href="<%= PortalUtil.getLayoutURL(rootLayout, themeDisplay) %>" <%= PortalUtil.getLayoutTarget(rootLayout) %>><%= rootLayout.getName(locale) %></a>
				</h2>
			</c:when>
			<c:when test='<%= headerType.equals("portlet-title") %>'>
				<h2><%= portletDisplay.getTitle() %></h2>
			</c:when>
			<c:when test='<%= headerType.equals("breadcrumb") %>'>
				<liferay-ui:breadcrumb />
			</c:when>
			<c:when test="<%= preview && (sb.length() == 0) %>">
				<div class="alert alert-info">
					<liferay-ui:message key="there-are-no-pages-to-display-for-the-current-page-level" />
				</div>
			</c:when>
		</c:choose>

		<%= sb.toString() %>
	</div>
</c:if>

<%!
private void _buildNavigation(Layout rootLayout, Layout selLayout, List<Layout> branchLayouts, ThemeDisplay themeDisplay, int layoutLevel, String includedLayouts, boolean nestedChildren, StringBundler sb) throws Exception {
	List<Layout> childLayouts = null;

	if (rootLayout != null) {
		childLayouts = rootLayout.getChildren(themeDisplay.getPermissionChecker());
	}
	else {
		childLayouts = LayoutLocalServiceUtil.getLayouts(selLayout.getGroupId(), selLayout.isPrivateLayout(), LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);
	}

	if (childLayouts.isEmpty()) {
		return;
	}

	StringBundler tailSB = null;

	if (!nestedChildren) {
		tailSB = new StringBundler();
	}

	sb.append("<ul class=\"layouts level-");
	sb.append(layoutLevel);
	sb.append("\">");

	for (Layout childLayout : childLayouts) {
		if (!childLayout.isHidden() && LayoutPermissionUtil.contains(themeDisplay.getPermissionChecker(), childLayout, ActionKeys.VIEW)) {
			boolean open = false;

			if (includedLayouts.equals("auto") && branchLayouts.contains(childLayout) && !childLayout.getChildren().isEmpty()) {
				open = true;
			}

			if (includedLayouts.equals("all")) {
				open = true;
			}

			String className = StringPool.BLANK;

			if (open) {
				className += "open ";
			}

			if (selLayout.getLayoutId() == childLayout.getLayoutId()) {
				className += "selected ";
			}

			sb.append("<li ");

			if (Validator.isNotNull(className)) {
				sb.append("class=\"");
				sb.append(className);
				sb.append("\" ");
			}

			sb.append("><a ");

			if (Validator.isNotNull(className)) {
				sb.append("class=\"");
				sb.append(className);
				sb.append("\" ");
			}

			sb.append("href=\"");
			sb.append(HtmlUtil.escapeHREF(PortalUtil.getLayoutURL(childLayout, themeDisplay)));
			sb.append("\" ");
			sb.append(PortalUtil.getLayoutTarget(childLayout));
			sb.append("> ");
			sb.append(HtmlUtil.escape(childLayout.getName(themeDisplay.getLocale())));
			sb.append("</a>");

			if (open) {
				StringBundler childLayoutSB = null;

				if (nestedChildren) {
					childLayoutSB = sb;
				}
				else {
					childLayoutSB = tailSB;
				}

				_buildNavigation(childLayout, selLayout, branchLayouts, themeDisplay, layoutLevel + 1, includedLayouts, nestedChildren, childLayoutSB);
			}

			sb.append("</li>");
		}
	}

	sb.append("</ul>");

	if (!nestedChildren) {
		sb.append(tailSB);
	}
}
%>