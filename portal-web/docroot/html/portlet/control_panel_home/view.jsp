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

<%@ include file="/html/portlet/control_panel_home/init.jsp" %>

<aui:container cssClass="control-panel-home-menu">
	<aui:row>

		<%
		Map<String, List<Portlet>> categoriesMap = PortalUtil.getControlPanelCategoriesMap(request);

		for (String category : categoriesMap.keySet()) {
			String categoryHeaderId = "control-panel-home-category-header" + category;
		%>

			<aui:col width="<%= 25 %>">
				<h3 class="control-panel-home-category-header" id="<%= categoryHeaderId %>">
					<aui:a href='<%= HttpUtil.setParameter(themeDisplay.getURLControlPanel(), "controlPanelCategory", category) %>'>
						<%= LanguageUtil.get(pageContext, "category." + category) %>
					</aui:a>
				</h3>

				<ul aria-labelledby="<%= categoryHeaderId %>" class="unstyled" role="menu">

					<%
					List<Portlet> categoryPortlets = categoriesMap.get(category);

					for (Portlet categoryPortlet : categoryPortlets) {
						String categoryPortletId = categoryPortlet.getPortletId();

						String urlCategoryPortlet = HttpUtil.setParameter(themeDisplay.getURLControlPanel(), "p_p_id", categoryPortletId);

						String portletDescription = PortalUtil.getPortletDescription(categoryPortlet, application, locale);
					%>

							<li role="presentation">
								<liferay-ui:icon
									ariaRole="menuitem"
									cssClass="control-panel-home-link"
									id='<%= "controlPanelPortletLink_" + categoryPortletId %>'
									label="<%= true %>"
									message="<%= PortalUtil.getPortletTitle(categoryPortlet, application, locale) %>"
									src='<%= Validator.isNull(categoryPortlet.getIcon())? themeDisplay.getPathContext() + "/html/icons/default.png" : categoryPortlet.getStaticResourcePath().concat(categoryPortlet.getIcon()) %>'
									url="<%= urlCategoryPortlet %>"
								/>

							<c:if test='<%= Validator.isNotNull(portletDescription) && !portletDescription.startsWith("javax.portlet.description") %>'>
								<liferay-ui:icon-help message="<%= portletDescription %>" />
							</c:if>
						</li>

					<%
					}
					%>

				</ul>
			</aui:col>

		<%
		}
		%>

		<c:if test="<%= categoriesMap.isEmpty() %>">
			<div class="alert alert-info">
				<liferay-ui:message key="you-do-not-have-permission-to-access-any-control-panel-applications" />
			</div>
		</c:if>
	</aui:row>
	<aui:row>
		<liferay-util:include page="/html/portlet/control_panel_home/view_actions.jsp" />
	</aui:row>
</aui:container>