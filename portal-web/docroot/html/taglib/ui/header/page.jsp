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

<%@ include file="/html/taglib/ui/header/init.jsp" %>

<%
if (Validator.isNull(backLabel)) {
	backLabel = LanguageUtil.get(pageContext, "back");
}

if (Validator.isNotNull(backURL) && !backURL.equals("javascript:history.go(-1);")) {
	backURL = HtmlUtil.escapeHREF(PortalUtil.escapeRedirect(backURL));
}

String headerTitle = (localizeTitle) ? LanguageUtil.get(pageContext, title) : title;
%>

<div class="taglib-header <%= cssClass %>">
	<c:if test="<%= showBackURL && Validator.isNotNull(backURL) %>">
		<span class="header-back-to">
			<a class="icon-circle-arrow-left previous-level" href="<%= backURL %>" id="<%= namespace %>TabsBack" title="<%= HtmlUtil.escapeAttribute(backLabel) %>">
				<span class="helper-hidden-accessible">
					<c:choose>
						<c:when test="<%= escapeXml %>">
							<%= HtmlUtil.escape(backLabel) %>
						</c:when>
						<c:otherwise>
							<%= backLabel %>
						</c:otherwise>
					</c:choose>
				</span>
			</a>
		</span>
	</c:if>

	<h3 class="header-title">
		<span>
			<c:choose>
				<c:when test="<%= escapeXml %>">
					<%= HtmlUtil.escape(headerTitle) %>
				</c:when>
				<c:otherwise>
					<%= headerTitle %>
				</c:otherwise>
			</c:choose>
		</span>
	</h3>
</div>