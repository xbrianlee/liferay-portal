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
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-textarea:cssClass"));
String param = (String)request.getAttribute("liferay-ui:input-textarea:param");
String paramId = (String)request.getAttribute("liferay-ui:input-textarea:paramId");
String defaultValue = (String)request.getAttribute("liferay-ui:input-textarea:defaultValue");
boolean disabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-textarea:disabled"));

String value = ParamUtil.getString(request, param, defaultValue);
%>

<textarea class="lfr-textarea <%= cssClass %>" <%= disabled ? "disabled=\"disabled\"" : "" %> id="<%= namespace %><%= paramId %>" name="<%= namespace %><%= param %>" wrap="soft" onKeyDown="Liferay.Util.disableEsc();"><%= HtmlUtil.escape(value) %></textarea>

<aui:script use="aui-char-counter">
	new A.CharCounter(
		{
			input: '#<%= namespace %><%= paramId %>',
			maxLength: <%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %>
		}
	);
</aui:script>