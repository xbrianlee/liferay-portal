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
WikiPage parentPage = (WikiPage)request.getAttribute(WebKeys.WIKI_TREE_WALKER_PARENT);
WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_TREE_WALKER_PAGE);
int depth = (Integer)request.getAttribute(WebKeys.WIKI_TREE_WALKER_DEPTH);

String preface = StringPool.BLANK;

for (int i = 0; i < depth; i++) {
	preface += "- ";
}

List<WikiPage> childPages = ListUtil.copy(parentPage.getViewableChildPages());

childPages.remove(wikiPage);

childPages = ListUtil.sort(childPages);
%>

<aui:option label="<%= preface + parentPage.getTitle() %>" selected="<%= parentPage.getTitle().equals(wikiPage.getParentTitle()) %>" value="<%= parentPage.getTitle() %>" />

<%
for (WikiPage childPage : childPages) {
	if (Validator.isNull(childPage.getRedirectTitle())) {
		request.setAttribute(WebKeys.WIKI_TREE_WALKER_PARENT, childPage);
		request.setAttribute(WebKeys.WIKI_TREE_WALKER_PAGE, wikiPage);
		request.setAttribute(WebKeys.WIKI_TREE_WALKER_DEPTH, depth + 1);
%>

		<liferay-util:include page="/html/portlet/wiki/page_tree.jsp" />

<%
	}
}
%>