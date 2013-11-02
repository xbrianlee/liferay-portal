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
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String contentId = GetterUtil.getString((String)request.getAttribute("liferay-ui:social-bookmark:contentId"));
String types = GetterUtil.getString((String)request.getAttribute("liferay-ui:social-bookmark:types"));
String url = GetterUtil.getString((String)request.getAttribute("liferay-ui:social-bookmark:url"));
String title = GetterUtil.getString((String)request.getAttribute("liferay-ui:social-bookmark:title"));
String target = GetterUtil.getString((String)request.getAttribute("liferay-ui:social-bookmark:target"));

String[] typesArray = null;

if (Validator.isNotNull(types)) {
	typesArray = StringUtil.split(types);
}
else {
	typesArray = PropsUtil.getArray(PropsKeys.SOCIAL_BOOKMARK_TYPES);
}
%>