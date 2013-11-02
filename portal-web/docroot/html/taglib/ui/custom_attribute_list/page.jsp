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

<%@ include file="/html/taglib/ui/custom_attribute_list/init.jsp" %>

<%
String className = (String)request.getAttribute("liferay-ui:custom-attribute-list:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-ui:custom-attribute-list:classPK"));
boolean editable = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:custom-attribute-list:editable"));
String ignoreAttributeNames = GetterUtil.getString((String)request.getAttribute("liferay-ui:custom-attribute-list:ignoreAttributeNames"));
boolean label = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:custom-attribute-list:label"));

ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(company.getCompanyId(), className, classPK);

String modelResourceName = ResourceActionsUtil.getModelResource(pageContext, className);

List<String> attributeNames = ListUtil.remove(Collections.list(expandoBridge.getAttributeNames()), ListUtil.fromString(ignoreAttributeNames, StringPool.COMMA));
%>

<div class="taglib-custom-attributes-list">

	<%
	for (String attributeName : attributeNames) {
	%>

		<liferay-ui:custom-attribute
			className="<%= className %>"
			classPK="<%= classPK %>"
			editable="<%= editable %>"
			label="<%= label %>"
			name="<%= attributeName %>"
		/>

	<%
	}
	%>

	<c:if test="<%= attributeNames.isEmpty() %>">
		<span class="field">
			<span class="field-content">
				<label><%= LanguageUtil.format(pageContext, "no-custom-fields-are-defined-for-x", modelResourceName) %></label>
			</span>
		</span>
	</c:if>
</div>