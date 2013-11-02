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
<%@ include file="/html/portlet/wiki/init.jsp" %>

<%
WikiPage wikiPage = (WikiPage)request.getAttribute("edit_page.jsp-wikiPage");

String content = BeanParamUtil.getString(wikiPage, request, "content");

String format = "html";
%>

<%@ include file="/html/portlet/wiki/edit/editor_config.jspf" %>

<liferay-ui:input-editor
	configParams="<%= configParams %>"
	editorImpl="<%= EDITOR_WYSIWYG_IMPL_KEY %>"
	fileBrowserParams="<%= fileBrowserParams %>"
	name="content"
	resizable="<%= false %>"
	width="100%"
/>

<aui:input name="content" type="hidden" />

<aui:script>
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(content) %>";
	}
</aui:script>

<%!
public static final String EDITOR_WYSIWYG_IMPL_KEY = "editor.wysiwyg.portal-web.docroot.html.portlet.wiki.edit.html.jsp";
%>