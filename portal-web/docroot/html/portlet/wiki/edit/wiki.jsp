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

String format = BeanParamUtil.getString(wikiPage, request, "format", WikiPageConstants.DEFAULT_FORMAT);

String content = BeanParamUtil.getString(wikiPage, request, "content");

String toggleId = renderResponse.getNamespace() + "toggle_id_wiki_editor_help";

String toggleValue = SessionClicks.get(request, toggleId, null);

boolean showSyntaxHelp = ((toggleValue != null) && toggleValue.equals("block"));
%>

<div align="right">
	<liferay-ui:toggle
		defaultShowContent="<%= false %>"
		hideMessage='<%= LanguageUtil.get(pageContext, "hide-syntax-help") + " &raquo;" %>'
		id="<%= toggleId %>"
		showMessage='<%= "&laquo; " + LanguageUtil.get(pageContext, "show-syntax-help") %>'
	/>
</div>

<div>
	<aui:row>
		<aui:col id="wikiEditorContainer" width="<%= showSyntaxHelp ? 70 : 100 %>">

		<%@ include file="/html/portlet/wiki/edit/editor_config.jspf" %>

		<c:choose>
			<c:when test='<%= format.equals("creole") %>'>
				<liferay-ui:input-editor
					configParams="<%= configParams %>"
					editorImpl="<%= EDITOR_WYSIWYG_IMPL_KEY %>"
					fileBrowserParams="<%= fileBrowserParams %>"
					toolbarSet="creole"
					width="100%"
				/>
			</c:when>
			<c:otherwise>
				<liferay-ui:input-editor
					configParams="<%= configParams %>"
					editorImpl="<%= EDITOR_SIMPLE_IMPL_KEY %>"
					fileBrowserParams="<%= fileBrowserParams %>"
					name="content"
					resizable="<%= false %>"
					width="100%"
				/>
			</c:otherwise>
		</c:choose>

			<aui:input name="content" type="hidden" />
		</aui:col>

		<aui:col cssClass="syntax-help" id="toggle_id_wiki_editor_help" style='<%= showSyntaxHelp ? StringPool.BLANK : "display: none" %>' width="<%= 30 %>">
			<h3>
				<liferay-ui:message key="syntax-help" />
			</h3>

			<liferay-util:include page="<%= WikiUtil.getHelpPage(format) %>" />

			<aui:a href="<%= WikiUtil.getHelpURL(format) %>" target="_blank"><liferay-ui:message key="learn-more" /> &raquo;</aui:a>
		</aui:col>
	</aui:row>
</div>

<aui:script>
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(content) %>";
	}
</aui:script>

<aui:script use="aui-base">
	var CSS_EDITOR_WIDTH = 'span8';

	var CSS_EDITOR_WIDTH_EXPANDED = 'span12';

	Liferay.on(
		'toggle:stateChange',
		function(event) {
			var id = event.id;

			if (id === '<%= toggleId %>') {
				var state = event.state;

				var classSrc = CSS_EDITOR_WIDTH;
				var classDest = CSS_EDITOR_WIDTH_EXPANDED;

				var visible = (state === 1);

				if (visible) {
					classSrc = CSS_EDITOR_WIDTH_EXPANDED;
					classDest = CSS_EDITOR_WIDTH;
				}

				var editorContainer = A.one('#<portlet:namespace />wikiEditorContainer');

				editorContainer.replaceClass(classSrc, classDest);

				if (visible && A.UA.webkit) {
					var editorFrame = editorContainer.one('iframe');

					if (editorFrame) {
						editorFrame.hide();

						A.later(0, editorFrame, 'show');
					}
				}

				var editorInstance = window['<portlet:namespace />editor'];

				if (editorInstance) {
					editorInstance.focus();
				}
			}
		}
	);
</aui:script>

<%!
public static final String EDITOR_SIMPLE_IMPL_KEY = "editor.wysiwyg.portal-web.docroot.html.portlet.wiki.edit.mediawiki.jsp";

public static final String EDITOR_WYSIWYG_IMPL_KEY = "editor.wysiwyg.portal-web.docroot.html.portlet.wiki.edit.creole.jsp";
%>