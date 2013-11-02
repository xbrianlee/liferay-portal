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
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-checkbox:cssClass"));
String param = (String)request.getAttribute("liferay-ui:input-checkbox:param");
String id = (String)request.getAttribute("liferay-ui:input-checkbox:id");
Boolean defaultValue = (Boolean)request.getAttribute("liferay-ui:input-checkbox:defaultValue");
String onClick = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-checkbox:onClick"));
boolean disabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-checkbox:disabled"));

boolean value = ParamUtil.getBoolean(request, param, defaultValue.booleanValue());

if (Validator.isNull(id)) {
	id = namespace + param;
}
%>

<input id="<%= id %>" name="<%= namespace %><%= param %>" type="hidden" value="<%= value %>" />

<input <%= value ? "checked" : "" %> class="<%= cssClass %>" <%= disabled ? "disabled=\"disabled\"" : "" %> id="<%= id %>Checkbox" name="<%= namespace %><%= param %>Checkbox" type="checkbox" onClick="Liferay.Util.updateCheckboxValue(this); <%= onClick %>" />