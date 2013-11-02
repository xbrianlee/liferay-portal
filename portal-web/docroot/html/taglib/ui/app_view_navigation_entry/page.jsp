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

<%@ include file="/html/taglib/init.jsp" %>

<%
String actionJsp = (String)request.getAttribute("liferay-ui:app-view-navigation-entry:actionJsp");
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:app-view-navigation-entry:cssClass"));
Map<String, Object> dataView = (Map<String, Object>)request.getAttribute("liferay-ui:app-view-navigation-entry:dataView");
String entryTitle = (String)request.getAttribute("liferay-ui:app-view-navigation-entry:entryTitle");
String iconImage = (String)request.getAttribute("liferay-ui:app-view-navigation-entry:iconImage");
boolean selected = GetterUtil.getBoolean(request.getAttribute("liferay-ui:app-view-navigation-entry:selected"));
String viewURL = (String)request.getAttribute("liferay-ui:app-view-navigation-entry:viewURL");

Map<String, Object> data = new HashMap<String, Object>();

data.putAll(dataView);

if (!data.containsKey("view-folders")) {
	data.put("view-folders", Boolean.FALSE);
}
%>

<aui:nav-item anchorCssClass='<%= "browse-" + cssClass %>' anchorData="<%= data %>" cssClass='<%= "app-view-navigation-entry " + cssClass %>' href="<%= viewURL.toString() %>" iconCssClass="<%= iconImage %>" label="<%= entryTitle %>" selected="<%= selected %>">

	<%
	request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	%>

	<c:if test="<%= Validator.isNotNull(actionJsp) %>">
		<liferay-util:include page="<%= actionJsp %>" />
	</c:if>
</aui:nav-item>