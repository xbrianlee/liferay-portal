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

<%@ include file="/html/taglib/aui/form/init.jsp" %>

<form action="<%= HtmlUtil.escape(action) %>" class="form <%= cssClass %> <%= inlineLabels ? "field-labels-inline" : StringPool.BLANK %>" id="<%= namespace + name %>" method="<%= method %>" name="<%= namespace + name %>" <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
	<input name="<%= namespace %>formDate" type="hidden" value="<%= System.currentTimeMillis() %>" />