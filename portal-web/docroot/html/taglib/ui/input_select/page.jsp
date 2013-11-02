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
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-select:cssClass"));
String param = (String)request.getAttribute("liferay-ui:input-select:param");
Boolean defaultValue = (Boolean)request.getAttribute("liferay-ui:input-select:defaultValue");
boolean disabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-select:disabled"));

boolean value = ParamUtil.getBoolean(request, param, defaultValue.booleanValue());
%>

<select class="<%= cssClass %>" <%= disabled ? "disabled=\"disabled\"" : "" %> name="<%= namespace %><%= param %>">
	<option <%= (value) ? "selected" : "" %> value="1"><liferay-ui:message key="yes" /></option>
	<option <%= (!value) ? "selected" : "" %> value="0"><liferay-ui:message key="no" /></option>
</select>