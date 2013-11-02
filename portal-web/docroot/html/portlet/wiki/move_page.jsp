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

<%
String redirect = ParamUtil.getString(request, "redirect");

WikiNode node = (WikiNode)request.getAttribute(WebKeys.WIKI_NODE);
WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_PAGE);

String title = wikiPage.getTitle();
String newTitle = ParamUtil.get(request, "newTitle", StringPool.BLANK);
%>

<liferay-util:include page="/html/portlet/wiki/top_links.jsp" />

<liferay-ui:error exception="<%= DuplicatePageException.class %>" message="there-is-already-a-page-with-the-specified-title" />
<liferay-ui:error exception="<%= PageTitleException.class %>" message="please-enter-a-valid-title" />

<%@ include file="/html/portlet/wiki/page_name.jspf" %>

<portlet:actionURL var="movePageURL">
	<portlet:param name="struts_action" value="/wiki/move_page" />
</portlet:actionURL>

<aui:form action="<%= movePageURL %>" method="post" name="fm" onSubmit='<%= renderResponse.getNamespace() + "changeParent();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="nodeId" type="hidden" value="<%= node.getNodeId() %>" />
	<aui:input name="title" type="hidden" value="<%= title %>" />
	<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_PUBLISH %>" />

	<liferay-ui:tabs
		names="rename,change-parent"
		refresh="<%= false %>"
	>

		<%
		boolean pending = false;

		if (WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(themeDisplay.getCompanyId(), scopeGroupId, WikiPage.class.getName())) {
			WikiPage latestWikiPage = WikiPageServiceUtil.getPage(wikiPage.getNodeId(), wikiPage.getTitle(), null);

			pending = latestWikiPage.isPending();
		}
		%>

		<liferay-ui:section>
			<div class="alert alert-info">
				<liferay-ui:message key="use-the-form-below-to-rename-a-page,-moving-all-of-its-history-to-the-new-name" />
			</div>

			<aui:fieldset>
				<aui:field-wrapper label="current-title">
					<liferay-ui:input-resource url="<%= wikiPage.getTitle() %>" />
				</aui:field-wrapper>

				<aui:input name="newTitle" value="<%= newTitle %>" />

				<c:if test="<%= pending %>">
					<div class="alert alert-info">
						<liferay-ui:message key="there-is-a-publication-workflow-in-process" />
					</div>
				</c:if>

				<aui:button-row>
					<aui:button disabled="<%= pending %>" onClick='<%= renderResponse.getNamespace() + "renamePage();" %>' value="rename" />

					<aui:button href="<%= redirect %>" type="cancel" />
				</aui:button-row>
			</aui:fieldset>
		</liferay-ui:section>
		<liferay-ui:section>
			<div class="alert alert-info">
				<liferay-ui:message key="use-the-form-below-to-move-a-page-and-all-of-its-history-to-be-the-child-of-a-new-parent-page" />
			</div>

			<%
			String parentText = StringPool.BLANK;

			WikiPage parentPage = wikiPage.getViewableParentPage();

			if (parentPage == null) {
				parentText = StringPool.OPEN_PARENTHESIS + LanguageUtil.get(pageContext, "none") + StringPool.CLOSE_PARENTHESIS;
			}
			else {
				parentText = parentPage.getTitle();

				parentPage = parentPage.getViewableParentPage();

				while (parentPage != null) {
					parentText = parentPage.getTitle() + " &raquo; " + parentText;

					parentPage = parentPage.getViewableParentPage();
				}
			}

			List<WikiPage> childPages = WikiPageLocalServiceUtil.getChildren(node.getNodeId(), true, StringPool.BLANK);

			childPages = ListUtil.sort(childPages);

			childPages.remove(wikiPage);
			%>

			<aui:fieldset>
				<aui:field-wrapper label="current-parent">
					<liferay-ui:input-resource url="<%= parentText %>" />
				</aui:field-wrapper>

				<%
				boolean newParentAvailable = true;

				if (childPages.isEmpty()) {
					newParentAvailable = false;
				%>

					<aui:select disabled="<%= true %>" label="new-parent" name="newParentTitle">
						<aui:option label="not-available" value="" />
					</aui:select>

				<%
				}
				else {
				%>

					<aui:select label="new-parent" name="newParentTitle">
						<aui:option label="none" selected="<%= Validator.isNull(wikiPage.getParentTitle()) %>" value="" />

						<%
						for (WikiPage childPage : childPages) {
							if (Validator.isNull(childPage.getRedirectTitle())) {
								request.setAttribute(WebKeys.WIKI_TREE_WALKER_PARENT, childPage);
								request.setAttribute(WebKeys.WIKI_TREE_WALKER_PAGE, wikiPage);
								request.setAttribute(WebKeys.WIKI_TREE_WALKER_DEPTH, 1);
						%>

								<liferay-util:include page="/html/portlet/wiki/page_tree.jsp" />

						<%
							}
						}
						%>

					</aui:select>

				<%
				}
				%>

				<c:if test="<%= pending %>">
					<div class="alert alert-info">
						<liferay-ui:message key="there-is-a-publication-workflow-in-process" />
					</div>
				</c:if>

				<aui:button-row>
					<c:choose>
						<c:when test="<%= WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(themeDisplay.getCompanyId(), scopeGroupId, WikiPage.class.getName()) %>">
							<aui:button disabled="<%= pending %>" name="publishButton" onClick='<%= renderResponse.getNamespace() + "publishPage();" %>' value="submit-for-publication" />
						</c:when>
						<c:otherwise>
							<aui:button disabled="<%= !newParentAvailable %>" type="submit" value="change-parent" />
						</c:otherwise>
					</c:choose>

					<aui:button href="<%= redirect %>" type="cancel" />
				</aui:button-row>
			</aui:fieldset>
		</liferay-ui:section>
	</liferay-ui:tabs>
</aui:form>

<aui:script>
	function <portlet:namespace />changeParent() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "changeParent";

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />publishPage() {
		document.<portlet:namespace />fm.<portlet:namespace />workflowAction.value = "<%= WorkflowConstants.ACTION_PUBLISH %>";

		<portlet:namespace />changeParent();
	}

	function <portlet:namespace />renamePage() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "rename";

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>