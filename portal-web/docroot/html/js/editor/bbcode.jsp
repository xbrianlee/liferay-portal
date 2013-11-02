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

<%@ include file="/html/taglib/init.jsp" %>

<%
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-editor:cssClass"));
String initMethod = (String)request.getAttribute("liferay-ui:input-editor:initMethod");
String name = namespace + GetterUtil.getString((String)request.getAttribute("liferay-ui:input-editor:name"));
%>

<div class="lfr-textarea message-edit <%= cssClass %>">
	<textarea id="<%= name %>" name="<%= name %>"></textarea>
</div>

<aui:script use="liferay-bbcode-editor">
	window['<%= name %>'] = new Liferay.Editor.bbCode(
		{
			textarea: '#<%= name %>'
		}
	);

	<c:if test="<%= Validator.isNotNull(initMethod) %>">
		window['<%= name %>'].setHTML(<%= HtmlUtil.escape(namespace + initMethod) %>());
	</c:if>
</aui:script>