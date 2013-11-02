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

<%@ include file="/html/taglib/aui/button_item/init.jsp" %>

<c:if test="<%= useMarkup %>">
	<c:if test="<%= !hasBoundingBox %>">
		<button class="buttonitem buttonitem-content component widget <%= (iconTypeClass != null) ? iconTypeClass : StringPool.BLANK %> <%= cssClass %>" id="<%= uniqueId %>BoundingBox" type="button">
	</c:if>

	<c:if test="<%= Validator.isNotNull(icon) %>">
		<span class="buttonitem-icon icon icon-<%= icon %>"></span>
	</c:if>

	<c:if test="<%= Validator.isNotNull(label) %>">
		<span class="buttonitem-label">
			<%= label %>
		</span>
	</c:if>

	<c:if test="<%= !hasBoundingBox %>">
		</button>
	</c:if>
</c:if>

<aui:component
	excludeAttributes="javaScriptAttributes,useJavaScript,useMarkup,var"
	module="aui-button-item"
	name="ButtonItem"
	options="<%= _options %>"
	scriptPosition='<%= GetterUtil.getString(_options.get("scriptPosition")) %>'
	tagPageContext="<%= pageContext %>"
	useJavaScript='<%= GetterUtil.getBoolean(_options.get("useJavaScript"), true) %>'
	var='<%= GetterUtil.getString(_options.get("var")) %>'
/>