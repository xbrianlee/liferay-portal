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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("ddm:html-field:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("ddm:html-field:scopedAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

if ((scopedAttributes != null) && !scopedAttributes.isEmpty()) {
	_options.putAll(scopedAttributes);
}

if ((dynamicAttributes != null) && !dynamicAttributes.isEmpty()) {
	_options.putAll(dynamicAttributes);
}

long classNameId = GetterUtil.getLong(String.valueOf(request.getAttribute("ddm:html-field:classNameId")));
long classPK = GetterUtil.getLong(String.valueOf(request.getAttribute("ddm:html-field:classPK")));
com.liferay.portlet.dynamicdatamapping.storage.Field field = (com.liferay.portlet.dynamicdatamapping.storage.Field)request.getAttribute("ddm:html-field:field");
java.lang.String fieldsNamespace = GetterUtil.getString((java.lang.String)request.getAttribute("ddm:html-field:fieldsNamespace"));
boolean readOnly = GetterUtil.getBoolean(String.valueOf(request.getAttribute("ddm:html-field:readOnly")));
boolean repeatable = GetterUtil.getBoolean(String.valueOf(request.getAttribute("ddm:html-field:repeatable")), true);
java.util.Locale requestedLocale = (java.util.Locale)request.getAttribute("ddm:html-field:requestedLocale");

_updateOptions(_options, "classNameId", classNameId);
_updateOptions(_options, "classPK", classPK);
_updateOptions(_options, "field", field);
_updateOptions(_options, "fieldsNamespace", fieldsNamespace);
_updateOptions(_options, "readOnly", readOnly);
_updateOptions(_options, "repeatable", repeatable);
_updateOptions(_options, "requestedLocale", requestedLocale);
%>

<%@ include file="/html/taglib/ddm/html_field/init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "ddm:html-field:";
%>