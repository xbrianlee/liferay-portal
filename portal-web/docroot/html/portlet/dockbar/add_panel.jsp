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

<%@ include file="/html/portlet/dockbar/init.jsp" %>

<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">
		<c:if test="<%= layout != null %>">

			<%
			Group group = layout.getGroup();

			boolean hasLayoutAddPermission = false;

			if (layout.getParentLayoutId() == LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {
				hasLayoutAddPermission = GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.ADD_LAYOUT);
			}
			else {
				hasLayoutAddPermission = LayoutPermissionUtil.contains(permissionChecker, layout, ActionKeys.ADD_LAYOUT);
			}

			boolean hasLayoutCustomizePermission = LayoutPermissionUtil.contains(permissionChecker, layout, ActionKeys.CUSTOMIZE);
			boolean hasLayoutUpdatePermission = LayoutPermissionUtil.contains(permissionChecker, layout, ActionKeys.UPDATE);
			%>

			<c:if test="<%= !group.isControlPanel() && (hasLayoutAddPermission || hasLayoutUpdatePermission || (layoutTypePortlet.isCustomizable() && layoutTypePortlet.isCustomizedView() && hasLayoutCustomizePermission)) %>">
				<div class="add-content-menu" id="<portlet:namespace />addPanelContainer">
					<aui:button cssClass="close pull-right" name="closePanelAdd" value="&times;" />

					<%
					String[] tabs1Names = new String[0];

					boolean stateMaximized = ParamUtil.getBoolean(request, "stateMaximized");

					boolean hasAddContentAndApplicationsPermission = !stateMaximized && layout.isTypePortlet() && !layout.isLayoutPrototypeLinkActive() && (hasLayoutUpdatePermission || (layoutTypePortlet.isCustomizable() && layoutTypePortlet.isCustomizedView() && hasLayoutCustomizePermission));

					if (hasAddContentAndApplicationsPermission) {
						tabs1Names = ArrayUtil.append(tabs1Names, "content,applications");
					}

					if (hasLayoutAddPermission) {
						tabs1Names = ArrayUtil.append(tabs1Names, "page");
					}

					String selectedTab = GetterUtil.getString(SessionClicks.get(request, "liferay_addpanel_tab", "content"));

					if (stateMaximized) {
						selectedTab = "page";
					}
					%>

					<h1><liferay-ui:message key="add" /></h1>

					<liferay-ui:tabs
						names="<%= StringUtil.merge(tabs1Names) %>"
						refresh="<%= false %>"
						type="pills"
						value="<%= selectedTab %>"
					>
						<c:if test="<%= hasAddContentAndApplicationsPermission %>">
							<liferay-ui:section>
								<liferay-util:include page="/html/portlet/dockbar/add_content.jsp" />
							</liferay-ui:section>

							<liferay-ui:section>
								<liferay-util:include page="/html/portlet/dockbar/add_application.jsp" />
							</liferay-ui:section>
						</c:if>

						<c:if test="<%= hasLayoutAddPermission %>">
							<liferay-ui:section>
								<liferay-util:include page="/html/portlet/layouts_admin/add_layout.jsp" />
							</liferay-ui:section>
						</c:if>
					</liferay-ui:tabs>
				</div>
			</c:if>
		</c:if>
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="please-sign-in-to-continue" />
	</c:otherwise>
</c:choose>

<aui:script use="liferay-dockbar">
	A.one('#<portlet:namespace />closePanelAdd').on('click', Liferay.Dockbar.toggleAddPanel, Liferay.Dockbar);
</aui:script>