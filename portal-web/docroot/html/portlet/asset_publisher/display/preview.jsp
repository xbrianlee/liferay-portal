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

<%@ include file="/html/portlet/asset_publisher/init.jsp" %>

<%
AssetEntry assetEntry = (AssetEntry)request.getAttribute("add_panel.jsp-assetEntry");
AssetRenderer assetRenderer = (AssetRenderer)request.getAttribute("add_panel.jsp-assetRenderer");
%>

<div class="asset-preview">

	<%
	String imagePreviewURL = assetRenderer.getURLImagePreview(liferayPortletRequest);
	%>

	<c:if test="<%= Validator.isNotNull(imagePreviewURL) %>">
		<div class="asset-image-preview">
			<img alt="<%= assetRenderer.getTitle(themeDisplay.getLocale()) %>" src="<%= imagePreviewURL %>" />
		</div>
	</c:if>

	<div class="asset-title">
		<%= assetRenderer.getTitle(themeDisplay.getLocale()) %>
	</div>

	<%
	String displayDateString = StringPool.BLANK;

	if (Validator.isNotNull(assetRenderer.getDisplayDate())) {
		Format displayFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat("MMMM d, yyyy", locale, timeZone);

		displayDateString = CharPool.OPEN_PARENTHESIS + displayFormatDate.format(assetRenderer.getDisplayDate()) + CharPool.CLOSE_PARENTHESIS;
	}
	%>

	<div class="asset-information">
		<span class="user-name"><%= assetRenderer.getUserName() %></span>&nbsp; <span class="display-date"><%= displayDateString %></span>
	</div>

	<div class="asset-summary">
		<%= StringUtil.shorten(assetRenderer.getSummary(themeDisplay.getLocale()), 320) %>
	</div>

	<div class="asset-metadata">
		<div class="categories">
			<liferay-ui:asset-categories-summary
				className="<%= assetEntry.getClassName() %>"
				classPK="<%= assetEntry.getClassPK () %>"
			/>
		</div>

		<div class="tags">
			<liferay-ui:asset-tags-summary
				className="<%= assetEntry.getClassName() %>"
				classPK="<%= assetEntry.getClassPK () %>"
			/>
		</div>
	</div>

	<%
	Map<String, Object> data = new HashMap<String, Object>();

	data.put("class-name", assetEntry.getClassName());
	data.put("class-pk", assetEntry.getClassPK());
	data.put("instanceable", Boolean.TRUE);
	data.put("portlet-id", assetRenderer.getAddToPagePortletId());
	%>

	<aui:button cssClass="add-button-preview" data="<%= data %>" value="add" />
</div>