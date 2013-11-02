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

<%@ include file="/html/portlet/dockbar/init.jsp" %>

<%
String redirect = request.getParameter("redirect");

Portlet selPortlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());

String className = ParamUtil.getString(request, "className");
long classPK = ParamUtil.getLong(request, "classPK");

Map<String, Object> data = new HashMap<String, Object>();

if (Validator.isNotNull(className) && (classPK > 0)) {
	AssetRendererFactory assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(className);

	AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(classPK);

	data.put("class-name", className);
	data.put("class-pk", classPK);
	data.put("instanceable", true);
	data.put("portlet-id", assetRenderer.getAddToPagePortletId());
}
%>

<span <%= AUIUtil.buildData(data) %> class="aui-helper-hidden portlet-item"></span>

<aui:script use="aui-base">
	<c:if test="<%= Validator.isNotNull(className) && (classPK > 0) %>">
		var Util = Liferay.Util;

		Util.getOpener().Liferay.fire(
			'AddContent:addPortlet',
			{
				node: A.one('.portlet-item')
			}
		);

		Util.getOpener().Liferay.fire('AddContent:refreshContentList');
	</c:if>

	Liferay.fire(
		'closeWindow',
		{
			id: '<portlet:namespace />editAsset',
			portletAjaxable: <%= selPortlet.isAjaxable() %>,

			<c:choose>
				<c:when test="<%= Validator.isNotNull(redirect) %>">
					redirect: '<%= HtmlUtil.escapeJS(redirect) %>'
				</c:when>
				<c:otherwise>
					refresh: '<%= portletDisplay.getId() %>'
				</c:otherwise>
			</c:choose>
		}
	);
</aui:script>