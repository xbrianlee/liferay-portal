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

<%@ include file="/html/taglib/aui/field_wrapper/init.jsp" %>

<%
String fieldCss = AUIUtil.buildCss("field-wrapper", false, first, last, cssClass);
%>

<div class="<%= controlGroupCss %> <%= fieldCss %>" <%= AUIUtil.buildData(data) %>>
	<c:if test='<%= Validator.isNotNull(label) && !inlineLabel.equals("right") %>'>
		<label <%= AUIUtil.buildLabel("wrapper", inlineField, showForLabel, name) %>>
			<liferay-ui:message key="<%= label %>" />

			<c:if test="<%= required %>">
				<span class="label-required">(<liferay-ui:message key="required" />)</span>
			</c:if>

			<c:if test="<%= Validator.isNotNull(helpMessage) %>">
				<liferay-ui:icon-help message="<%= helpMessage %>" />
			</c:if>
		</label>
	</c:if>