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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:fieldset:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:fieldset:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

boolean column = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:fieldset:column")));
java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:fieldset:cssClass"));
java.lang.String helpMessage = GetterUtil.getString((java.lang.String)request.getAttribute("aui:fieldset:helpMessage"));
java.lang.String id = GetterUtil.getString((java.lang.String)request.getAttribute("aui:fieldset:id"));
java.lang.String label = GetterUtil.getString((java.lang.String)request.getAttribute("aui:fieldset:label"));

_updateOptions(_options, "column", column);
_updateOptions(_options, "cssClass", cssClass);
_updateOptions(_options, "helpMessage", helpMessage);
_updateOptions(_options, "id", id);
_updateOptions(_options, "label", label);
%>

<%@ include file="/html/taglib/aui/fieldset/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:fieldset:";
%>