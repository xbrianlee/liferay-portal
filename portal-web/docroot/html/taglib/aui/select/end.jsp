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

<%@ include file="/html/taglib/aui/select/init.jsp" %>

	</select>

	<c:if test="<%= Validator.isNotNull(suffix) %>">
		<span class="suffix">
			<liferay-ui:message key="<%= suffix %>" />
		</span>
	</c:if>

	<c:if test='<%= inlineLabel.equals("right") %>'>
		<label <%= AUIUtil.buildLabel("select", inlineField, true, namespace + id) %>>
			<liferay-ui:message key="<%= label %>" />

			<c:if test="<%= Validator.isNotNull(helpMessage) %>">
				<liferay-ui:icon-help message="<%= helpMessage %>" />
			</c:if>

			<c:if test="<%= changesContext %>">
				<span class="hide-accessible"><liferay-ui:message key="changing-the-value-of-this-field-will-reload-the-page" />)</span>
			</c:if>
		</label>
	</c:if>
</div>