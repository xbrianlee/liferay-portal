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

<%@ include file="/html/taglib/aui/button/init.jsp" %>

<button class="<%= AUIUtil.buildCss(AUIUtil.BUTTON_PREFIX, disabled, false, false, cssClass) %>" <%= disabled ? "disabled" : StringPool.BLANK %> <%= Validator.isNotNull(name) ? "id=\"" + namespace + name + "\"" : StringPool.BLANK %> <%= Validator.isNotNull(onClick) ? "onClick=\"" + onClick + "\"" : StringPool.BLANK %> type='<%= type.equals("cancel") ? "button" : type %>' <%= AUIUtil.buildData(data) %> <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
	<c:if test='<%= Validator.isNotNull(icon) && iconAlign.equals("left") %>'>
		<i class="<%= icon %>"></i>
	</c:if>

	<%= LanguageUtil.get(pageContext, value) %>

	<c:if test='<%= Validator.isNotNull(icon) && iconAlign.equals("right") %>'>
		<i class="<%= icon %>"></i>
	</c:if>
</button>

<c:if test="<%= useDialog %>">
	<aui:script>
		Liferay.delegateClick('<%= namespace + name %>', Liferay.Util.openInDialog);
	</aui:script>
</c:if>