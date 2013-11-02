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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.parsers.bbcode.BBCodeTranslatorUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ContentTypes" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.LocaleUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portlet.messageboards.model.MBThreadConstants" %>

<%@ page import="java.util.Locale" %>

<%
String contentsLanguageId = ParamUtil.getString(request, "contentsLanguageId");
String cssPath = ParamUtil.getString(request, "cssPath");
String cssClasses = ParamUtil.getString(request, "cssClasses");
String imagesPath = ParamUtil.getString(request, "imagesPath");
String languageId = ParamUtil.getString(request, "languageId");
String emoticonsPath = ParamUtil.getString(request, "emoticonsPath");
boolean resizable = ParamUtil.getBoolean(request, "resizable");

response.setContentType(ContentTypes.TEXT_JAVASCRIPT);
%>

CKEDITOR.config.height = 265;

CKEDITOR.config.removePlugins = [
	'elementspath',
	'save',
	'bidi',
	'div',
	'flash',
	'forms',
	'keystrokes',
	'link',
	'maximize',
	'newpage',
	'pagebreak',
	'preview',
	'print',
	'save',
	'showblocks',
	'templates',
	'video'
].join(',');

CKEDITOR.config.toolbar_bbcode = [
	['Bold', 'Italic', 'Underline', 'Strike', '-', 'Link', 'Unlink'],
	['Image', 'Smiley', '-', 'TextColor', '-', 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent'],
	['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'Blockquote', '-', 'Code'],
	'/',
	['Font', 'FontSize', '-', 'Format', '-', 'Undo', 'Redo', '-', 'Source']
];

CKEDITOR.config.toolbar_phone = [
	['Bold', 'Italic', 'Underline'],
	['NumberedList', 'BulletedList'],
	['Image', 'Link', 'Unlink']
];

CKEDITOR.config.toolbar_tablet = [
	['Bold', 'Italic', 'Underline', 'Strike'],
	['NumberedList', 'BulletedList'],
	['Image', 'Link', 'Unlink'],
	['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
	['Styles', 'FontSize']
];

CKEDITOR.config.bodyClass = 'html-editor <%= HtmlUtil.escapeJS(cssClasses) %>';

CKEDITOR.config.contentsCss = '<%= HtmlUtil.escapeJS(cssPath) %>/main.css';

<%
Locale contentsLocale = LocaleUtil.fromLanguageId(contentsLanguageId);

String contentsLanguageDir = LanguageUtil.get(contentsLocale, "lang.dir");
%>

CKEDITOR.config.contentsLangDirection = '<%= HtmlUtil.escapeJS(contentsLanguageDir) %>';

CKEDITOR.config.contentsLanguage = '<%= HtmlUtil.escapeJS(contentsLanguageId.replace("iw_", "he_")) %>';

CKEDITOR.config.enterMode = CKEDITOR.ENTER_BR;

CKEDITOR.config.extraPlugins = 'bbcode,wikilink';

CKEDITOR.config.filebrowserBrowseUrl = '';

CKEDITOR.config.filebrowserImageBrowseLinkUrl = '';

CKEDITOR.config.filebrowserImageBrowseUrl = '';

CKEDITOR.config.filebrowserImageUploadUrl = '';

CKEDITOR.config.filebrowserUploadUrl = '';

CKEDITOR.config.fontSize_sizes = '10/10px;12/12px;16/16px;18/18px;24/24px;32/32px;48/48px';

CKEDITOR.config.format_tags = 'p;pre';

CKEDITOR.config.imagesPath = '<%= HtmlUtil.escapeJS(imagesPath) %>/message_boards/';

CKEDITOR.config.language = '<%= HtmlUtil.escapeJS(languageId.replace("iw_", "he_")) %>';

CKEDITOR.config.newThreadURL = '<%= MBThreadConstants.NEW_THREAD_URL %>';

<c:if test="<%= resizable %>">
	CKEDITOR.config.resize_dir = 'vertical';
</c:if>

CKEDITOR.config.resize_enabled = <%= resizable %>;

CKEDITOR.config.smiley_descriptions = ['<%= StringUtil.merge(BBCodeTranslatorUtil.getEmoticonDescriptions(), "','") %>'];

CKEDITOR.config.smiley_images = ['<%= StringUtil.merge(BBCodeTranslatorUtil.getEmoticonFiles(), "','") %>'];

CKEDITOR.config.smiley_path = '<%= HtmlUtil.escapeJS(emoticonsPath) %>' + '/';

CKEDITOR.config.smiley_symbols = ['<%= StringUtil.merge(BBCodeTranslatorUtil.getEmoticonSymbols(), "','") %>'];