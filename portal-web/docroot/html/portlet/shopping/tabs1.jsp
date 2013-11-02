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

<%@ include file="/html/portlet/shopping/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "categories");

String tabs1Names = "categories,cart";

if (!user.isDefaultUser()) {
	tabs1Names += ",orders";
}

if (ShoppingPermission.contains(permissionChecker, scopeGroupId, ActionKeys.MANAGE_COUPONS)) {
	tabs1Names += ",coupons";
}

// View

PortletURL viewURL = renderResponse.createRenderURL();

viewURL.setParameter("struts_action", "/shopping/view");

// Cart

PortletURL cartURL = renderResponse.createRenderURL();

cartURL.setParameter("struts_action", "/shopping/cart");

if (!tabs1.equals("cart")) {
	cartURL.setParameter("redirect", currentURL);
}

// Back URL

String backURL = ParamUtil.getString(request, "backURL");
%>

<liferay-ui:tabs
	backURL="<%= backURL %>"
	names="<%= tabs1Names %>"
	url="<%= viewURL.toString() %>"
	url1="<%= cartURL.toString() %>"
/>