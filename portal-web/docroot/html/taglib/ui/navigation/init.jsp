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
String bulletStyle = StringUtil.toLowerCase(((String)request.getAttribute("liferay-ui:navigation:bulletStyle")));
String displayStyle = (String)request.getAttribute("liferay-ui:navigation:displayStyle");
boolean preview = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:navigation:preview"));

String headerType = null;
String includedLayouts = null;
boolean nestedChildren = true;
int rootLayoutLevel = 0;
String rootLayoutType = null;

String[] displayStyleDefinition = _getDisplayStyleDefinition(displayStyle);

if ((displayStyleDefinition != null) && (displayStyleDefinition.length != 0)) {
	headerType = displayStyleDefinition[0];
	includedLayouts = displayStyleDefinition[3];

	if (displayStyleDefinition.length > 4) {
		nestedChildren = GetterUtil.getBoolean(displayStyleDefinition[4]);
	}

	rootLayoutLevel = GetterUtil.getInteger(displayStyleDefinition[2]);
	rootLayoutType = displayStyleDefinition[1];
}
else {
	headerType = (String)request.getAttribute("liferay-ui:navigation:headerType");
	includedLayouts = (String)request.getAttribute("liferay-ui:navigation:includedLayouts");
	nestedChildren = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:navigation:nestedChildren"));
	rootLayoutLevel = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:navigation:rootLayoutLevel"));
	rootLayoutType = (String)request.getAttribute("liferay-ui:navigation:rootLayoutType");
}
%>

<%!
private String[] _getDisplayStyleDefinition(String displayStyle) {
	return PropsUtil.getArray("navigation.display.style", new Filter(displayStyle));
}
%>