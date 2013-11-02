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
long groupId = ParamUtil.getLong(request, "groupId");
long[] selectedGroupIds = StringUtil.split(ParamUtil.getString(request, "selectedGroupIds"), 0L);
long refererAssetEntryId = ParamUtil.getLong(request, "refererAssetEntryId");
String typeSelection = ParamUtil.getString(request, "typeSelection");
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectAsset");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/asset_browser/view");
portletURL.setParameter("groupId", String.valueOf(groupId));
portletURL.setParameter("selectedGroupIds", StringUtil.merge(selectedGroupIds));
portletURL.setParameter("refererAssetEntryId", String.valueOf(refererAssetEntryId));
portletURL.setParameter("typeSelection", typeSelection);
portletURL.setParameter("eventName", eventName);

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<div class="asset-search">
	<aui:form action="<%= portletURL %>" method="post" name="selectAssetFm">
		<aui:input name="typeSelection" type="hidden" value="<%= typeSelection %>" />

		<liferay-ui:search-container
			searchContainer="<%= new AssetSearch(renderRequest, portletURL) %>"
		>
			<aui:nav-bar>
				<aui:nav>
					<liferay-util:include page="/html/portlet/asset_browser/toolbar.jsp">
						<liferay-util:param name="groupId" value="<%= String.valueOf(groupId) %>" />
						<liferay-util:param name="typeSelection" value="<%= typeSelection %>" />
					</liferay-util:include>
				</aui:nav>

				<aui:nav-bar-search cssClass="pull-right">
					<liferay-ui:search-form
						page="/html/portlet/asset_publisher/asset_search.jsp"
					/>
				</aui:nav-bar-search>
			</aui:nav-bar>

			<%
			AssetSearchTerms searchTerms = (AssetSearchTerms)searchContainer.getSearchTerms();

			long[] groupIds = selectedGroupIds;

			AssetRendererFactory assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(typeSelection);
			%>

			<liferay-ui:search-container-results>
				<%@ include file="/html/portlet/asset_publisher/asset_search_results.jspf" %>
			</liferay-ui:search-container-results>

			<div class="separator"><!-- --></div>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.search.Document"
				escapedModel="<%= true %>"
				modelVar="doc"
			>

				<%
				long assetEntryId = 0;

				if (typeSelection.equals(JournalArticle.class.getName())) {
					assetEntryId = GetterUtil.getLong(doc.get(Field.ROOT_ENTRY_CLASS_PK));
				}
				else {
					assetEntryId = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));
				}

				AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(typeSelection, assetEntryId);

				if ((assetEntry == null) || !assetEntry.isVisible()) {
					continue;
				}

				Group group = GroupLocalServiceUtil.getGroup(assetEntry.getGroupId());
				%>

				<liferay-ui:search-container-column-text
					name="title"
					value="<%= HtmlUtil.escape(assetEntry.getTitle(locale)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="description"
					value="<%= HtmlUtil.stripHtml(assetEntry.getDescription(locale)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="userName"
					value="<%= PortalUtil.getUserName(assetEntry) %>"
				/>

				<liferay-ui:search-container-column-date
					name="modifiedDate"
					value="<%= assetEntry.getModifiedDate() %>"
				/>

				<liferay-ui:search-container-column-text
					name="descriptiveName"
					value="<%= HtmlUtil.escape(group.getDescriptiveName(locale)) %>"
				/>

				<liferay-ui:search-container-column-text>
					<c:if test="<%= assetEntry.getEntryId() != refererAssetEntryId %>">

						<%
						Map<String, Object> data = new HashMap<String, Object>();

						data.put("assetentryid", assetEntry.getEntryId());
						data.put("assetclassname", assetEntry.getClassName());
						data.put("assettype", assetRendererFactory.getTypeName(locale, true));
						data.put("assettitle", HtmlUtil.escape(assetEntry.getTitle(locale)));
						data.put("groupdescriptivename", HtmlUtil.escape(group.getDescriptiveName(locale)));
						%>

						<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
					</c:if>
				</liferay-ui:search-container-column-text>

			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script use="aui-base">
	var Util = Liferay.Util;

	A.one('#<portlet:namespace />selectAssetFm').delegate(
		'click',
		function(event) {
			var result = Util.getAttributes(event.currentTarget, 'data-');

			Util.getOpener().Liferay.fire('<%= HtmlUtil.escapeJS(eventName) %>', result);

			Util.getWindow().hide();
		},
		'.selector-button'
	);
</aui:script>