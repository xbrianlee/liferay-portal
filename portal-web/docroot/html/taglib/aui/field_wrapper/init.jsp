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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:field-wrapper:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:field-wrapper:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:field-wrapper:cssClass"));
java.util.Map data = (java.util.Map)request.getAttribute("aui:field-wrapper:data");
boolean first = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:field-wrapper:first")));
java.lang.String helpMessage = GetterUtil.getString((java.lang.String)request.getAttribute("aui:field-wrapper:helpMessage"));
boolean inlineField = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:field-wrapper:inlineField")));
java.lang.String inlineLabel = GetterUtil.getString((java.lang.String)request.getAttribute("aui:field-wrapper:inlineLabel"));
java.lang.String label = GetterUtil.getString((java.lang.String)request.getAttribute("aui:field-wrapper:label"));
boolean last = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:field-wrapper:last")));
java.lang.String name = GetterUtil.getString((java.lang.String)request.getAttribute("aui:field-wrapper:name"));
boolean required = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:field-wrapper:required")));

_updateOptions(_options, "cssClass", cssClass);
_updateOptions(_options, "data", data);
_updateOptions(_options, "first", first);
_updateOptions(_options, "helpMessage", helpMessage);
_updateOptions(_options, "inlineField", inlineField);
_updateOptions(_options, "inlineLabel", inlineLabel);
_updateOptions(_options, "label", label);
_updateOptions(_options, "last", last);
_updateOptions(_options, "name", name);
_updateOptions(_options, "required", required);
%>

<%@ include file="/html/taglib/aui/field_wrapper/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:field-wrapper:";
%>