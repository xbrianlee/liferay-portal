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

<%@ include file="/html/taglib/aui/nav/init.jsp" %>

<c:if test="<%= collapsible %>">
	<div class="collapse nav-collapse" id="<%= id %>NavbarCollapse">
</c:if>

<ul aria-label="<%= Validator.isNull(ariaLabel) ? portletDisplay.getTitle() : ariaLabel %>" class="nav <%= cssClass %>" id="<%= id %>" role="<%= Validator.isNull(ariaRole) ? "menubar" : ariaRole %>" <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>