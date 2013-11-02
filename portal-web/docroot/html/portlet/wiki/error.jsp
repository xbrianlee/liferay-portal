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

<%@ include file="/html/portlet/wiki/init.jsp" %>

<liferay-ui:header
	backURL="javascript:history.go(-1);"
	title="error"
/>

<liferay-ui:error exception="<%= NoSuchNodeException.class %>" message="please-enter-a-valid-page-title" />

<c:if test="<%= SessionErrors.contains(renderRequest, NoSuchPageException.class.getName()) %>">

	<%
	long nodeId = ParamUtil.getLong(request, "nodeId");

	if (nodeId == 0) {
		WikiNode node = (WikiNode)request.getAttribute(WebKeys.WIKI_NODE);

		if (node != null) {
			nodeId = node.getNodeId();
		}
	}

	String title = ParamUtil.getString(request, "title");

	boolean hasDraftPage = false;

	if (nodeId > 0) {
		hasDraftPage = WikiPageLocalServiceUtil.hasDraftPage(nodeId, title);
	}

	PortletURL searchURL = renderResponse.createRenderURL();

	searchURL.setParameter("struts_action", "/wiki/search");
	searchURL.setParameter("redirect", currentURL);
	searchURL.setParameter("nodeId", String.valueOf(nodeId));
	searchURL.setParameter("keywords", title);

	PortletURL editPageURL = renderResponse.createRenderURL();

	editPageURL.setParameter("struts_action", "/wiki/edit_page");
	editPageURL.setParameter("redirect", currentURL);
	editPageURL.setParameter("nodeId", String.valueOf(nodeId));
	editPageURL.setParameter("title", title);
	%>

	<c:choose>
		<c:when test="<%= hasDraftPage %>">

			<%
			WikiPage draftPage = WikiPageLocalServiceUtil.getDraftPage(nodeId, title);

			boolean editableDraft = false;

			if (permissionChecker.isCompanyAdmin() || permissionChecker.isGroupAdmin(scopeGroupId) || (draftPage.getUserId() == user.getUserId())) {
				editableDraft = true;
			}
			%>

			<c:choose>
				<c:when test="<%= editableDraft %>">
					<div class="alert alert-info">
						<liferay-ui:message key="this-page-has-an-associated-draft-that-is-not-yet-published" />
					</div>

					<div class="btn-toolbar">

						<%
						String taglibEditPage = "location.href = '" + editPageURL.toString() + "';";
						%>

						<aui:button onClick="<%= taglibEditPage %>" value="edit-draft" />
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-info">
						<liferay-ui:message key="this-page-has-already-been-started-by-another-author" />
					</div>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<div class="alert alert-info">
				<liferay-ui:message key="this-page-is-empty.-use-the-buttons-below-to-create-it-or-to-search-for-the-words-in-the-title" />
			</div>

			<div class="btn-toolbar">

				<%
				String taglibSearch = "location.href = '" + searchURL.toString() + "';";
				%>

				<aui:button onClick="<%= taglibSearch %>" value='<%= LanguageUtil.format(pageContext, "search-for-x", HtmlUtil.escapeAttribute(title)) %>' />

				<%
				String taglibEditPage = "location.href = '" + editPageURL.toString() + "';";
				%>

				<aui:button onClick="<%= taglibEditPage %>" value='<%= LanguageUtil.format(pageContext, "create-page-x", HtmlUtil.escapeAttribute(title)) %>' />
			</div>
		</c:otherwise>
	</c:choose>
</c:if>

<liferay-ui:error exception="<%= PageTitleException.class %>" message="please-enter-a-valid-page-title" />
<liferay-ui:error exception="<%= PrincipalException.class %>" message="you-do-not-have-the-required-permissions" />