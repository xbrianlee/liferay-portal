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

<%@ include file="/html/taglib/aui/component/init.jsp" %>

<c:if test="<%= useJavaScript %>">
	<aui:script position="<%= scriptPosition %>" use="<%= module %>">
		<c:if test="<%= Validator.isNotNull(var) %>">
			<%= var %> =
		</c:if>

		(new A.<%= name %>(<%= _serialize(jsonifiedOptions, javaScriptAttributes) %>));
	</aui:script>
</c:if>