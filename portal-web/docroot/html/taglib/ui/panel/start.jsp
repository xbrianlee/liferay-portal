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

<%@ include file="/html/taglib/ui/panel/init.jsp" %>

<div class="accordion-group <%= cssClass %>" id="<%= id %>">
	<div class="accordion-heading <%= headerCssClass %>" data-persist-id="<%= persistState ? id : StringPool.BLANK %>">
		<div class="accordion-toggle">
			<c:if test="<%= Validator.isNotNull(iconCssClass) %>">
				<i class="<%= iconCssClass %>"></i>
			</c:if>

			<span class="title-text">
				<liferay-ui:message key="<%= title %>" />
			</span>

			<c:if test="<%= Validator.isNotNull(helpMessage) %>">
				<liferay-ui:icon-help message="<%= helpMessage %>" />
			</c:if>
		</div>
	</div>
	<div class="<%= contentCssClass %>" id="<%= id %>Content">
		<div class="accordion-inner">