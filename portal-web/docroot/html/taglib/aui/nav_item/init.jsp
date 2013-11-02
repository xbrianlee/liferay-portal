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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:nav-item:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:nav-item:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

java.lang.String anchorCssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-item:anchorCssClass"));
java.util.Map anchorData = (java.util.Map)request.getAttribute("aui:nav-item:anchorData");
java.lang.String anchorId = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-item:anchorId"));
java.lang.String ariaLabel = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-item:ariaLabel"));
java.lang.String ariaRole = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-item:ariaRole"));
java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-item:cssClass"));
java.util.Map data = (java.util.Map)request.getAttribute("aui:nav-item:data");
boolean dropdown = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:nav-item:dropdown")));
java.lang.String href = GetterUtil.getString((java.lang.Object)request.getAttribute("aui:nav-item:href"), "javascript:void(0);");
java.lang.String iconCssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-item:iconCssClass"));
java.lang.String id = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-item:id"));
java.lang.String label = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-item:label"));
boolean selected = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:nav-item:selected")));
java.lang.String state = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-item:state"));
java.lang.String title = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-item:title"));
boolean toggle = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:nav-item:toggle")));
boolean useDialog = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:nav-item:useDialog")), false);
boolean wrapDropDownMenu = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:nav-item:wrapDropDownMenu")), true);

_updateOptions(_options, "anchorCssClass", anchorCssClass);
_updateOptions(_options, "anchorData", anchorData);
_updateOptions(_options, "anchorId", anchorId);
_updateOptions(_options, "ariaLabel", ariaLabel);
_updateOptions(_options, "ariaRole", ariaRole);
_updateOptions(_options, "cssClass", cssClass);
_updateOptions(_options, "data", data);
_updateOptions(_options, "dropdown", dropdown);
_updateOptions(_options, "href", href);
_updateOptions(_options, "iconCssClass", iconCssClass);
_updateOptions(_options, "id", id);
_updateOptions(_options, "label", label);
_updateOptions(_options, "selected", selected);
_updateOptions(_options, "state", state);
_updateOptions(_options, "title", title);
_updateOptions(_options, "toggle", toggle);
_updateOptions(_options, "useDialog", useDialog);
_updateOptions(_options, "wrapDropDownMenu", wrapDropDownMenu);
%>

<%@ include file="/html/taglib/aui/nav_item/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:nav-item:";
%>