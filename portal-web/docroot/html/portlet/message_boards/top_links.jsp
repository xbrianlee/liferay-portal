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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
String topLink = ParamUtil.getString(request, "topLink", "message-boards-home");

MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = MBUtil.getCategoryId(request, category);

boolean viewCategory = GetterUtil.getBoolean((String)request.getAttribute("view.jsp-viewCategory"));

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/message_boards/view");
%>

<aui:nav-bar>
	<aui:nav>

		<%
		String label = "message-boards-home";

		portletURL.setParameter("topLink", label);
		portletURL.setParameter("tag", StringPool.BLANK);
		%>

		<aui:nav-item cssClass='<%= topLink.equals(label) ? "active" : StringPool.BLANK %>' href="<%= portletURL.toString() %>" label="<%= label %>" selected="<%= topLink.equals(label) %>" />

		<%
		label = "recent-posts";

		portletURL.setParameter("topLink", label);
		%>

		<aui:nav-item cssClass='<%= topLink.equals(label) ? "active" : StringPool.BLANK %>' href="<%= portletURL.toString() %>" label="<%= label %>" selected="<%= topLink.equals(label) %>" />

		<c:if test="<%= themeDisplay.isSignedIn() && !portletName.equals(PortletKeys.MESSAGE_BOARDS_ADMIN) %>">

			<%
			label = "my-posts";

			portletURL.setParameter("topLink", label);
			%>

			<aui:nav-item cssClass='<%= topLink.equals(label) ? "active" : StringPool.BLANK %>' href="<%= portletURL.toString() %>" label="<%= label %>" selected="<%= topLink.equals(label) %>" />

			<c:if test="<%= MBUtil.getEmailMessageAddedEnabled(portletPreferences) || MBUtil.getEmailMessageUpdatedEnabled(portletPreferences) %>">

				<%
				label = "my-subscriptions";

				portletURL.setParameter("topLink", label);
				%>

				<aui:nav-item cssClass='<%= topLink.equals(label) ? "active" : StringPool.BLANK %>' href="<%= portletURL.toString() %>" label="<%= label %>" selected="<%= topLink.equals(label) %>" />
			</c:if>
		</c:if>

		<%
		label = "statistics";

		portletURL.setParameter("topLink", label);
		%>

		<aui:nav-item cssClass='<%= topLink.equals(label) ? "active" : StringPool.BLANK %>' href="<%= portletURL.toString() %>" label="<%= label %>" selected="<%= topLink.equals(label) %>" />

		<c:if test="<%= MBPermission.contains(permissionChecker, scopeGroupId, ActionKeys.BAN_USER) %>">

			<%
			label = "banned-users";

			portletURL.setParameter("topLink", label);
			%>

			<aui:nav-item cssClass='<%= topLink.equals(label) ? "active" : StringPool.BLANK %>' href="<%= portletURL.toString() %>" label="<%= label %>" selected="<%= topLink.equals(label) %>" />
		</c:if>
	</aui:nav>

	<c:if test="<%= showSearch %>">
		<liferay-portlet:renderURL varImpl="searchURL">
			<portlet:param name="struts_action" value="/message_boards/search" />
		</liferay-portlet:renderURL>

		<aui:nav-bar-search cssClass="pull-right">
			<div class="form-search">
				<aui:form action="<%= searchURL %>" method="get" name="searchFm">
					<liferay-portlet:renderURLParams varImpl="searchURL" />
					<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
					<aui:input name="breadcrumbsCategoryId" type="hidden" value="<%= categoryId %>" />
					<aui:input name="searchCategoryId" type="hidden" value="<%= categoryId %>" />

					<liferay-ui:input-search id="keywords1" />
				</aui:form>
			</div>
		</aui:nav-bar-search>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) && !themeDisplay.isFacebook() %>">
			<aui:script>
				Liferay.Util.focusFormField(document.getElementById('<portlet:namespace />keywords1'));
			</aui:script>
		</c:if>
	</c:if>
</aui:nav-bar>

<c:if test="<%= layout.isTypeControlPanel() %>">
	<div id="breadcrumb">
		<liferay-ui:breadcrumb showCurrentGroup="<%= false %>" showCurrentPortlet="<%= false %>" showGuestGroup="<%= false %>" showLayout="<%= false %>" showPortletBreadcrumb="<%= true %>" />
	</div>
</c:if>