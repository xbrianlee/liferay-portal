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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:nav:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:nav:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

java.lang.String ariaLabel = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav:ariaLabel"));
java.lang.String ariaRole = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav:ariaRole"));
boolean collapsible = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:nav:collapsible")));
java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav:cssClass"));
java.lang.String icon = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav:icon"));
java.lang.String id = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav:id"));
boolean useNamespace = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:nav:useNamespace")), true);

_updateOptions(_options, "ariaLabel", ariaLabel);
_updateOptions(_options, "ariaRole", ariaRole);
_updateOptions(_options, "collapsible", collapsible);
_updateOptions(_options, "cssClass", cssClass);
_updateOptions(_options, "icon", icon);
_updateOptions(_options, "id", id);
_updateOptions(_options, "useNamespace", useNamespace);
%>

<%@ include file="/html/taglib/aui/nav/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:nav:";
%>