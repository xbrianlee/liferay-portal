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

<%@ include file="/html/taglib/ui/progress/init.jsp" %>

<%
Integer height = (Integer)request.getAttribute("liferay-ui:progress:height");
String id = (String)request.getAttribute("liferay-ui:progress:id");
String message = (String)request.getAttribute("liferay-ui:progress:message");
String sessionKey = GetterUtil.getString(request.getAttribute("liferay-ui:progress:sessionKey"), LiferayFileUpload.PERCENT);
%>

<div id="<%= id %>Bar"></div>

<aui:script use="liferay-progress">
	A.config.win['<%= id %>'] = new Liferay.Progress(
		{
			boundingBox: '#<%= id %>Bar',

			<c:if test="<%= Validator.isNotNull(height) %>">
				height: <%= height %>,
			</c:if>

			id: '<%= id %>',
			label: '<%= UnicodeLanguageUtil.get(pageContext, message) %>',
			sessionKey: '<%= HtmlUtil.escapeJS(sessionKey) %>'
		}
	);
</aui:script>