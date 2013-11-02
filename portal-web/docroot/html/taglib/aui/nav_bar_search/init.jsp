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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:nav-bar-search:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:nav-bar-search:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-bar-search:cssClass"));
java.lang.String id = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-bar-search:id"));
java.lang.String file = GetterUtil.getString((java.lang.String)request.getAttribute("aui:nav-bar-search:file"));
com.liferay.portal.kernel.dao.search.SearchContainer<?> searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer<?>)request.getAttribute("aui:nav-bar-search:searchContainer");

_updateOptions(_options, "cssClass", cssClass);
_updateOptions(_options, "id", id);
_updateOptions(_options, "file", file);
_updateOptions(_options, "searchContainer", searchContainer);
%>

<%@ include file="/html/taglib/aui/nav_bar_search/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:nav-bar-search:";
%>