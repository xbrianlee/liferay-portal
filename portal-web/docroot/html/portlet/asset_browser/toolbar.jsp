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

<%@ include file="/html/portlet/asset_browser/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "browse");

long groupId = ParamUtil.getLong(request, "groupId");
String typeSelection = ParamUtil.getString(request, "typeSelection");

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");

AssetRendererFactory assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(typeSelection);

Map<Long, String> classTypes = assetRendererFactory.getClassTypes(new long[] {themeDisplay.getCompanyGroupId(), themeDisplay.getScopeGroupId()}, locale);
%>

<aui:nav-item href="<%= portletURL %>" label="browse" selected='<%= toolbarItem.equals("browse") %>' />

<c:choose>
	<c:when test="<%= classTypes.isEmpty() %>">

		<%
		PortletURL addPortletURL = AssetUtil.getAddPortletURL(liferayPortletRequest, liferayPortletResponse, typeSelection, 0, null, null, portletURL.toString());
		%>

		<c:if test="<%= addPortletURL != null %>">

			<%
			addPortletURL.setParameter("groupId", String.valueOf(groupId));

			String addPortletURLString = addPortletURL.toString();

			addPortletURLString = HttpUtil.addParameter(addPortletURLString, "doAsGroupId", groupId);
			addPortletURLString = HttpUtil.addParameter(addPortletURLString, "refererPlid", plid);
			%>

			<aui:nav-item href="<%= addPortletURLString %>" label='<%= LanguageUtil.format(pageContext, "add-x", assetRendererFactory.getTypeName(locale, false)) %>' />
		</c:if>
	</c:when>
	<c:otherwise>
		<aui:nav-item dropdown="<%= true %>" iconCssClass="icon-plus" label="add" selected='<%= toolbarItem.equals("add") %>'>

			<%
			PortletURL addPortletURL = AssetUtil.getAddPortletURL(liferayPortletRequest, liferayPortletResponse, typeSelection, 0, null, null, portletURL.toString());
			%>

			<c:if test="<%= addPortletURL != null %>">

				<%
				addPortletURL.setParameter("groupId", String.valueOf(groupId));

				String addPortletURLString = addPortletURL.toString();

				addPortletURLString = HttpUtil.addParameter(addPortletURLString, "doAsGroupId", groupId);
				addPortletURLString = HttpUtil.addParameter(addPortletURLString, "refererPlid", plid);
				%>

				<aui:nav-item href="<%= addPortletURLString %>" label="<%= assetRendererFactory.getTypeName(locale, true) %>" />
			</c:if>

			<%
			for (long classTypeId : classTypes.keySet()) {
				addPortletURL = AssetUtil.getAddPortletURL(liferayPortletRequest, liferayPortletResponse, typeSelection, classTypeId, null, null, portletURL.toString());

				if (addPortletURL == null) {
					continue;
				}

				addPortletURL.setParameter("groupId", String.valueOf(groupId));

				String addPortletURLString = addPortletURL.toString();

				addPortletURLString = HttpUtil.addParameter(addPortletURLString, "doAsGroupId", groupId);
				addPortletURLString = HttpUtil.addParameter(addPortletURLString, "refererPlid", plid);
			%>

				<aui:nav-item href="<%= addPortletURLString %>" label="<%= HtmlUtil.escape(classTypes.get(classTypeId)) %>" />

			<%
			}
			%>

		</aui:nav-item>
	</c:otherwise>
</c:choose>