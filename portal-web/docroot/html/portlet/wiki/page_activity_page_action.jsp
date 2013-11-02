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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

SocialActivity socialActivity = (SocialActivity)row.getObject();

JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject(HtmlUtil.unescape(socialActivity.getExtraData()));

double version = extraDataJSONObject.getDouble("version");

WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_PAGE);

WikiPage socialActivityPage = WikiPageLocalServiceUtil.getPage(wikiPage.getNodeId(), wikiPage.getTitle(), version);
%>

<liferay-ui:icon-menu>
	<c:if test="<%= (version != wikiPage.getVersion()) && (socialActivityPage.isApproved()) && (WikiPagePermission.contains(permissionChecker, wikiPage, ActionKeys.UPDATE)) %>">
		<portlet:actionURL var="revertURL">
			<portlet:param name="struts_action" value="/wiki/edit_page" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.REVERT %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="nodeId" value="<%= String.valueOf(wikiPage.getNodeId()) %>" />
			<portlet:param name="title" value="<%= HtmlUtil.unescape(wikiPage.getTitle()) %>" />
			<portlet:param name="version" value="<%= String.valueOf(version) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			image="undo"
			message='<%= LanguageUtil.get(pageContext, "restore-version") + " " + String.valueOf(version) %>'
			url="<%= revertURL %>"
		/>
	</c:if>

	<portlet:renderURL var="compareVersionsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="struts_action" value="/wiki/select_version" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="nodeId" value="<%= String.valueOf(wikiPage.getNodeId()) %>" />
		<portlet:param name="title" value="<%= HtmlUtil.unescape(wikiPage.getTitle()) %>" />
		<portlet:param name="sourceVersion" value="<%= String.valueOf(version) %>" />
	</portlet:renderURL>

	<%
	Map<String, Object> data = new HashMap<String, Object>();

	data.put("uri", compareVersionsURL);
	%>

	<liferay-ui:icon
		cssClass="compare-to-link"
		data="<%= data %>"
		image="copy"
		label="<%= true %>"
		message="compare-to"
		url="javascript:;"
	/>
</liferay-ui:icon-menu>