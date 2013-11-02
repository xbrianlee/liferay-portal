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
List list = (List)request.getAttribute("liferay-ui:table-iterator:list");
int listPos = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:table-iterator:listPos"));
int rowLength = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:table-iterator:rowLength"));
String rowPadding = (String)request.getAttribute("liferay-ui:table-iterator:rowPadding");
String rowValign = (String)request.getAttribute("liferay-ui:table-iterator:rowValign");
String rowBreak = (String)request.getAttribute("liferay-ui:table-iterator:rowBreak");
String width = (String)request.getAttribute("liferay-ui:table-iterator:width");

// LEP-4752

if (rowLength == 0) {
	rowLength = 2;
}
%>