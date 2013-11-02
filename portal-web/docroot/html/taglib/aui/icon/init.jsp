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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:icon:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:icon:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:icon:cssClass"));
java.util.Map<java.lang.String, java.lang.Object> data = (java.util.Map<java.lang.String, java.lang.Object>)request.getAttribute("aui:icon:data");
java.lang.String id = GetterUtil.getString((java.lang.String)request.getAttribute("aui:icon:id"));
java.lang.String image = GetterUtil.getString((java.lang.String)request.getAttribute("aui:icon:image"));
java.lang.String label = GetterUtil.getString((java.lang.String)request.getAttribute("aui:icon:label"));
java.lang.String target = GetterUtil.getString((java.lang.String)request.getAttribute("aui:icon:target"));
java.lang.String url = GetterUtil.getString((java.lang.String)request.getAttribute("aui:icon:url"));

_updateOptions(_options, "cssClass", cssClass);
_updateOptions(_options, "data", data);
_updateOptions(_options, "id", id);
_updateOptions(_options, "image", image);
_updateOptions(_options, "label", label);
_updateOptions(_options, "target", target);
_updateOptions(_options, "url", url);
%>

<%@ include file="/html/taglib/aui/icon/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:icon:";
%>