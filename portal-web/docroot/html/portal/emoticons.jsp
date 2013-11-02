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

<%@ include file="/html/portal/init.jsp" %>

<%
String[][] emoticons = BBCodeTranslatorUtil.getEmoticons();

for (int i = 0; i < emoticons.length; i++) {
	String image = StringUtil.replace(emoticons[i][0], "@theme_images_path@", themeDisplay.getPathThemeImages());
%>

	<a class="lfr-button emoticon" emoticonCode="<%= emoticons[i][1] %>"><%= image %></a>

<%
}
%>