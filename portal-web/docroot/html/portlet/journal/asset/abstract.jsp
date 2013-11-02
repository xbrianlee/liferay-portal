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

<%@ include file="/html/portlet/journal/init.jsp" %>

<%
AssetRenderer assetRenderer = (AssetRenderer)request.getAttribute(WebKeys.ASSET_RENDERER);
int abstractLength = (Integer)request.getAttribute(WebKeys.ASSET_PUBLISHER_ABSTRACT_LENGTH);
String viewURL = (String)request.getAttribute(WebKeys.ASSET_PUBLISHER_VIEW_URL);

JournalArticle article = (JournalArticle)request.getAttribute(WebKeys.JOURNAL_ARTICLE);
JournalArticleResource articleResource = JournalArticleResourceLocalServiceUtil.getArticleResource(article.getResourcePrimKey());

String languageId = LanguageUtil.getLanguageId(request);

boolean workflowAssetPreview = GetterUtil.getBoolean((Boolean)request.getAttribute(WebKeys.WORKFLOW_ASSET_PREVIEW));

JournalArticleDisplay articleDisplay = null;

if (!workflowAssetPreview && article.isApproved()) {
	String xmlRequest = PortletRequestUtil.toXML(renderRequest, renderResponse);

	articleDisplay = JournalContentUtil.getDisplay(articleResource.getGroupId(), articleResource.getArticleId(), null, null, languageId, themeDisplay, 1, xmlRequest);
}
else {
	articleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(article, null, null, languageId, 1, null, themeDisplay);
}
%>

<c:if test="<%= articleDisplay.isSmallImage() %>">

	<%
	String src = StringPool.BLANK;

	if (Validator.isNotNull(articleDisplay.getSmallImageURL())) {
		src = articleDisplay.getSmallImageURL();
	}
	else {
		src = themeDisplay.getPathImage() + "/journal/article?img_id=" + articleDisplay.getSmallImageId() + "&t=" + WebServerServletTokenUtil.getToken(articleDisplay.getSmallImageId()) ;
	}
	%>

	<div class="asset-small-image">
		<c:choose>
			<c:when test="<%= Validator.isNotNull(viewURL) %>">
				<a href="<%= viewURL %>">
					<img alt="<%= articleDisplay.getTitle() %>" class="asset-small-image" src="<%= HtmlUtil.escape(src) %>" width="150" />
				</a>
			</c:when>
			<c:otherwise>
				<img alt="" class="asset-small-image" src="<%= HtmlUtil.escape(src) %>" width="150" />
			</c:otherwise>
		</c:choose>
	</div>
</c:if>

<%
String summary = HtmlUtil.escape(articleDisplay.getDescription());

summary = HtmlUtil.replaceNewLine(summary);

if (Validator.isNull(summary)) {
	summary = HtmlUtil.stripHtml(articleDisplay.getContent());
}
%>

<%= StringUtil.shorten(summary, abstractLength) %>