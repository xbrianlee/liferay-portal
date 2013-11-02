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

<%@ include file="/html/taglib/aui/column/init.jsp" %>

<div class="column <%= (columnWidth > 0) ? "w" + columnWidth : StringPool.BLANK %> <%= cssClass %> <%= first ? "column-first" : StringPool.BLANK %> <%= last ? "column-last" : StringPool.BLANK %>" <%= Validator.isNotNull(id) ? "id=\"" + namespace + id + "\"" : StringPool.BLANK %> <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
	<div class="column-content <%= first ? "column-content-first" : StringPool.BLANK %> <%= last ? "column-content-last" : StringPool.BLANK %> <%= cssClasses %>" <%= Validator.isNotNull(id) ? "id=\"" + namespace + id + "Content\"" : StringPool.BLANK %>>