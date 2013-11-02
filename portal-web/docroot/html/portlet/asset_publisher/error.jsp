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

<%@ include file="/html/portlet/asset_publisher/init.jsp" %>

<portlet:renderURL var="portletURL">
	<portlet:param name="struts_action" value="/asset_publisher/view" />
</portlet:renderURL>

<liferay-ui:header
	backURL="<%= portletURL.toString() %>"
	title="error"
/>

<liferay-ui:error exception="<%= NoSuchModelException.class %>" message="the-asset-could-not-be-found" />