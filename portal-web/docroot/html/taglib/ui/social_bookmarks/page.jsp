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

<%@ include file="/html/taglib/ui/social_bookmarks/init.jsp" %>

<c:if test="<%= typesArray.length > 0 %>">

	<%
	String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_social_bookmarks_page") + StringPool.UNDERLINE;
	%>

	<div class="taglib-social-bookmarks" id="<%= randomNamespace %>socialBookmarks">
		<ul class="unstyled">

			<%
			for (int i = 0; i < typesArray.length; i++) {
				String styleClass = "taglib-social-bookmark-" + typesArray[i];
			%>

				<li class="<%= styleClass %>">
					<liferay-ui:social-bookmark contentId="<%= contentId %>" target="<%= target %>" title="<%= title %>" type="<%= typesArray[i] %>" url="<%= url %>" />
				</li>

			<%
			}
			%>

		</ul>
	</div>
</c:if>