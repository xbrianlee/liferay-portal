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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:option:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:option:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:option:cssClass"));
java.util.Map<java.lang.String, java.lang.Object> data = (java.util.Map<java.lang.String, java.lang.Object>)request.getAttribute("aui:option:data");
boolean disabled = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:option:disabled")));
java.lang.Object label = (java.lang.Object)request.getAttribute("aui:option:label");
boolean selected = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:option:selected")));
java.lang.String style = GetterUtil.getString((java.lang.String)request.getAttribute("aui:option:style"));
boolean useModelValue = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:option:useModelValue")), true);
java.lang.Object value = (java.lang.Object)request.getAttribute("aui:option:value");

_updateOptions(_options, "cssClass", cssClass);
_updateOptions(_options, "data", data);
_updateOptions(_options, "disabled", disabled);
_updateOptions(_options, "label", label);
_updateOptions(_options, "selected", selected);
_updateOptions(_options, "style", style);
_updateOptions(_options, "useModelValue", useModelValue);
_updateOptions(_options, "value", value);
%>

<%@ include file="/html/taglib/aui/option/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:option:";
%>