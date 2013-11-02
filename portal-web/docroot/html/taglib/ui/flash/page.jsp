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
String randomNamespace = StringUtil.randomId() + StringPool.UNDERLINE;

String allowFullScreen = (String)request.getAttribute("liferay-ui:flash:allowFullScreen");
String allowScriptAccess = (String)request.getAttribute("liferay-ui:flash:allowScriptAccess");
String base = (String)request.getAttribute("liferay-ui:flash:base");
String bgcolor = (String)request.getAttribute("liferay-ui:flash:bgcolor");
String devicefont = (String)request.getAttribute("liferay-ui:flash:devicefont");
String flashvars = (String)request.getAttribute("liferay-ui:flash:flashvars");
String height = (String)request.getAttribute("liferay-ui:flash:height");
String id = (String)request.getAttribute("liferay-ui:flash:id");
String loop = (String)request.getAttribute("liferay-ui:flash:loop");
String menu = (String)request.getAttribute("liferay-ui:flash:menu");
String movie = (String)request.getAttribute("liferay-ui:flash:movie");
String play = (String)request.getAttribute("liferay-ui:flash:play");
String quality = (String)request.getAttribute("liferay-ui:flash:quality");
String salign = (String)request.getAttribute("liferay-ui:flash:salign");
String scale = (String)request.getAttribute("liferay-ui:flash:scale");
String swliveconnect = (String)request.getAttribute("liferay-ui:flash:swliveconnect");
String version = (String)request.getAttribute("liferay-ui:flash:version");
String width = (String)request.getAttribute("liferay-ui:flash:width");
String wmode = (String)request.getAttribute("liferay-ui:flash:wmode");
%>

<div id="<%= randomNamespace %>flashcontent" style="height: <%= height %>; width: <%= width %>;"></div>

<aui:script use="aui-swf-deprecated">
	new A.SWF(
		{
			boundingBox: '#<%= randomNamespace %>flashcontent',
			fixedAttributes: {
				allowFullScreen: '<%= allowFullScreen %>',
				allowScriptAccess: '<%= allowScriptAccess %>',
				base: '<%= base %>',
				bgcolor: '<%= bgcolor %>',
				devicefont: '<%= devicefont %>',
				loop: '<%= loop %>',
				menu: '<%= menu %>',
				play: '<%= play %>',
				quality: '<%= quality %>',
				salign: '<%= salign %>',
				scale: '<%= scale %>',
				swliveconnect: '<%= swliveconnect %>',
				wmode: '<%= wmode %>'
			},
			flashVars: '<%= flashvars %>',
			height: '<%= height %>',
			id: '<%= id %>',
			url: '<%= movie %>',
			version: <%= version %>,
			width: '<%= width %>'
		}
	).render();
</aui:script>