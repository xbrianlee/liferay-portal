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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:col:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:col:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:col:cssClass"));
java.lang.String id = GetterUtil.getString((java.lang.String)request.getAttribute("aui:col:id"));
int offset = GetterUtil.getInteger(String.valueOf(request.getAttribute("aui:col:offset")));
int offsetWidth = GetterUtil.getInteger(String.valueOf(request.getAttribute("aui:col:offsetWidth")));
int span = GetterUtil.getInteger(String.valueOf(request.getAttribute("aui:col:span")), 12);
int width = GetterUtil.getInteger(String.valueOf(request.getAttribute("aui:col:width")));

_updateOptions(_options, "cssClass", cssClass);
_updateOptions(_options, "id", id);
_updateOptions(_options, "offset", offset);
_updateOptions(_options, "offsetWidth", offsetWidth);
_updateOptions(_options, "span", span);
_updateOptions(_options, "width", width);
%>

<%@ include file="/html/taglib/aui/col/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:col:";
%>