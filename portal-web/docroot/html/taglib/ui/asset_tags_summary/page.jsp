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
String[] assetTagNames = StringUtil.split((String)request.getAttribute("liferay-ui:asset-tags-summary:assetTagNames"));
String className = (String)request.getAttribute("liferay-ui:asset-tags-summary:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-ui:asset-tags-summary:classPK"));
String message = GetterUtil.getString((String)request.getAttribute("liferay-ui:asset-tags-summary:message"), StringPool.BLANK);
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-ui:asset-tags-summary:portletURL");

if (assetTagNames.length == 0) {
	List<AssetTag> tags = AssetTagServiceUtil.getTags(className, classPK);

	assetTagNames = StringUtil.split(ListUtil.toString(tags, AssetTag.NAME_ACCESSOR));
}
%>

<c:if test="<%= assetTagNames.length > 0 %>">
	<span class="taglib-asset-tags-summary">
		<%= Validator.isNotNull(message) ? (LanguageUtil.get(pageContext, message) + ": ") : "" %>

		<c:choose>
			<c:when test="<%= portletURL != null %>">

				<%
				for (int i = 0; i < assetTagNames.length; i++) {
					portletURL.setParameter("tag", assetTagNames[i]);
				%>

					<a class="tag" href="<%= HtmlUtil.escape(portletURL.toString()) %>"><%= assetTagNames[i] %></a>

				<%
				}
				%>

			</c:when>
			<c:otherwise>

				<%
				for (int i = 0; i < assetTagNames.length; i++) {
				%>

					<span class="tag"><%= assetTagNames[i] %></span>

				<%
				}
				%>

			</c:otherwise>
		</c:choose>
	</span>
</c:if>