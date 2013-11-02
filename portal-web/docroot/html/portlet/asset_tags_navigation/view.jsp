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

<%@ include file="/html/portlet/asset_tags_navigation/init.jsp" %>

<%
long portletDisplayDDMTemplateId = PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplateId(displayStyleGroupId, displayStyle);
%>

<c:choose>
	<c:when test="<%= portletDisplayDDMTemplateId > 0 %>">

		<%
		List<AssetTag> assetTags = null;

		if (showAssetCount && (classNameId > 0)) {
			assetTags = AssetTagServiceUtil.getTags(scopeGroupId, classNameId, null, 0, maxAssetTags, new AssetTagCountComparator());
		}
		else {
			assetTags = AssetTagServiceUtil.getGroupTags(themeDisplay.getSiteGroupId(), 0, maxAssetTags, new AssetTagCountComparator());
		}

		assetTags = ListUtil.sort(assetTags);

		Map<String, Object> contextObjects = new HashMap<String, Object>();

		contextObjects.put("scopeGroupId", new Long(scopeGroupId));
		%>

		<%= PortletDisplayTemplateUtil.renderDDMTemplate(pageContext, portletDisplayDDMTemplateId, assetTags, contextObjects) %>
	</c:when>
	<c:otherwise>
		<liferay-ui:asset-tags-navigation
			classNameId="<%= classNameId %>"
			displayStyle="<%= displayStyle %>"
			hidePortletWhenEmpty="<%= true %>"
			maxAssetTags="<%= maxAssetTags %>"
			showAssetCount="<%= showAssetCount %>"
			showZeroAssetCount="<%= showZeroAssetCount %>"
		/>
	</c:otherwise>
</c:choose>