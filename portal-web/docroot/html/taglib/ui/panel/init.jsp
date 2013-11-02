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
boolean accordion = false;
boolean collapsible = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:panel:collapsible"));
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:panel:cssClass"));
String defaultState = (String)request.getAttribute("liferay-ui:panel:defaultState");
Boolean extended = (Boolean)request.getAttribute("liferay-ui:panel:extended");
String helpMessage = (String)request.getAttribute("liferay-ui:panel:helpMessage");
String iconCssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:panel:iconCssClass"));
String id = (String)request.getAttribute("liferay-ui:panel:id");
String parentId = (String)request.getAttribute("liferay-ui:panel:parentId");
boolean persistState = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:panel:persistState"));
String state = (String)request.getAttribute("liferay-ui:panel:state");
String title = (String)request.getAttribute("liferay-ui:panel:title");

IntegerWrapper panelCount = (IntegerWrapper)request.getAttribute("liferay-ui:panel-container:panelCount" + parentId);

if (panelCount != null) {
	panelCount.increment();

	if (extended == null) {
		Boolean panelContainerExtended = (Boolean)request.getAttribute("liferay-ui:panel-container:extended");

		if (panelContainerExtended != null) {
			extended = panelContainerExtended;
		}
	}
}

cssClass += " lfr-panel";

if ((extended != null) && extended) {
	cssClass += " lfr-panel-extended";
}

String contentCssClass = StringPool.BLANK;
String headerCssClass = StringPool.BLANK;

if (collapsible) {
	contentCssClass += "toggler-content";
	headerCssClass += "toggler-header";

	if (BrowserSnifferUtil.isMobile(request)) {
		state = "closed";
	}

	if (state == null) {
		state = GetterUtil.getString(SessionClicks.get(request, id, null), defaultState);
	}

	if (state.equals("open")) {
		contentCssClass += " toggler-content-expanded";
		headerCssClass += " toggler-header-expanded";
	}
	else {
		contentCssClass += " toggler-content-collapsed";
		headerCssClass += " toggler-header-collapsed";
	}
}
%>