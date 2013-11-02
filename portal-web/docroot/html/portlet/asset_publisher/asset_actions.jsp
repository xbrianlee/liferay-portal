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
boolean showIconLabel = ((Boolean)request.getAttribute("view.jsp-showIconLabel")).booleanValue();

AssetRenderer assetRenderer = (AssetRenderer)request.getAttribute("view.jsp-assetRenderer");

boolean showEditURL = ParamUtil.getBoolean(request, "showEditURL", true);

PortletURL editPortletURL = null;

if (showEditURL && assetRenderer.hasEditPermission(permissionChecker)) {
	PortletURL redirectURL = liferayPortletResponse.createLiferayPortletURL(plid, portletDisplay.getId(), PortletRequest.RENDER_PHASE, false);

	redirectURL.setParameter("struts_action", "/asset_publisher/add_asset_redirect");

	String fullContentRedirect = (String)request.getAttribute("view.jsp-fullContentRedirect");

	if (fullContentRedirect != null) {
		redirectURL.setParameter("redirect", fullContentRedirect);
	}
	else {
		redirectURL.setParameter("redirect", currentURL);
	}

	redirectURL.setWindowState(LiferayWindowState.POP_UP);

	editPortletURL = assetRenderer.getURLEdit(liferayPortletRequest, liferayPortletResponse, LiferayWindowState.POP_UP, redirectURL);
}
%>

<c:if test="<%= editPortletURL != null %>">
	<div class="lfr-meta-actions asset-actions">

		<%
		String taglibEditURL = "javascript:Liferay.Util.openWindow({id: '" + renderResponse.getNamespace() + "editAsset', title: '" + LanguageUtil.format(pageContext, "edit-x", HtmlUtil.escape(assetRenderer.getTitle(locale))) + "', uri:'" + HtmlUtil.escapeURL(editPortletURL.toString()) + "'});";
		%>

		<liferay-ui:icon
			image="edit"
			label="<%= showIconLabel %>"
			message='<%= showIconLabel ? LanguageUtil.format(pageContext, "edit-x-x", new Object[] {"hide-accessible", HtmlUtil.escape(assetRenderer.getTitle(locale))}) : LanguageUtil.format(pageContext, "edit-x", HtmlUtil.escape(assetRenderer.getTitle(locale))) %>'
			url="<%= taglibEditURL %>"
		/>
	</div>
</c:if>