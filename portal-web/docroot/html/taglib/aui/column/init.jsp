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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:column:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:column:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

int columnWidth = GetterUtil.getInteger(String.valueOf(request.getAttribute("aui:column:columnWidth")));
java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:column:cssClass"));
boolean first = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:column:first")));
java.lang.String id = GetterUtil.getString((java.lang.String)request.getAttribute("aui:column:id"));
boolean last = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:column:last")));

_updateOptions(_options, "columnWidth", columnWidth);
_updateOptions(_options, "cssClass", cssClass);
_updateOptions(_options, "first", first);
_updateOptions(_options, "id", id);
_updateOptions(_options, "last", last);
%>

<%@ include file="/html/taglib/aui/column/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:column:";
%>