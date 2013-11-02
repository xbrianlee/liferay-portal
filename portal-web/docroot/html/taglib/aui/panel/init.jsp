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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:panel:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:panel:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

boolean collapsed = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:panel:collapsed")));
boolean collapsible = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:panel:collapsible")));
java.lang.String id = GetterUtil.getString((java.lang.String)request.getAttribute("aui:panel:id"));
java.lang.String label = GetterUtil.getString((java.lang.String)request.getAttribute("aui:panel:label"));

_updateOptions(_options, "collapsed", collapsed);
_updateOptions(_options, "collapsible", collapsible);
_updateOptions(_options, "id", id);
_updateOptions(_options, "label", label);
%>

<%@ include file="/html/taglib/aui/panel/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:panel:";
%>