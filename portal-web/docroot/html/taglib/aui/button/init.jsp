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

@generated
--%>

<%@ include file="/html/taglib/taglib-init.jsp" %>

<%
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:button:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:button:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:button:cssClass"));
java.util.Map data = (java.util.Map)request.getAttribute("aui:button:data");
boolean disabled = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:button:disabled")));
java.lang.String href = GetterUtil.getString((java.lang.String)request.getAttribute("aui:button:href"));
java.lang.String icon = GetterUtil.getString((java.lang.String)request.getAttribute("aui:button:icon"));
java.lang.String iconAlign = GetterUtil.getString((java.lang.String)request.getAttribute("aui:button:iconAlign"), "left");
java.lang.String name = GetterUtil.getString((java.lang.String)request.getAttribute("aui:button:name"));
java.lang.String onClick = GetterUtil.getString((java.lang.String)request.getAttribute("aui:button:onClick"));
java.lang.Object primary = (java.lang.Object)request.getAttribute("aui:button:primary");
java.lang.String type = GetterUtil.getString((java.lang.String)request.getAttribute("aui:button:type"), "button");
boolean useDialog = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:button:useDialog")), false);
java.lang.String value = GetterUtil.getString((java.lang.String)request.getAttribute("aui:button:value"));

_updateOptions(_options, "cssClass", cssClass);
_updateOptions(_options, "data", data);
_updateOptions(_options, "disabled", disabled);
_updateOptions(_options, "href", href);
_updateOptions(_options, "icon", icon);
_updateOptions(_options, "iconAlign", iconAlign);
_updateOptions(_options, "name", name);
_updateOptions(_options, "onClick", onClick);
_updateOptions(_options, "primary", primary);
_updateOptions(_options, "type", type);
_updateOptions(_options, "useDialog", useDialog);
_updateOptions(_options, "value", value);
%>

<%@ include file="/html/taglib/aui/button/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:button:";
%>