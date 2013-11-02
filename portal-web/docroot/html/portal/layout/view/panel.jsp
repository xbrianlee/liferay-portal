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

<%@ include file="/html/portal/init.jsp" %>

<c:choose>
	<c:when test="<%= !themeDisplay.isStatePopUp() %>">
		<aui:container class="lfr-panel-page" id="main-content">
			<aui:row>

				<%
				String panelBodyCssClass = "panel-page-body";

				if (!layoutTypePortlet.hasStateMax()) {
					panelBodyCssClass += " panel-page-frontpage";
				}
				else {
					panelBodyCssClass += " panel-page-application";
				}
				%>

				<aui:col cssClass="panel-page-menu" width="<%= 20 %>">

					<%
					PortletCategory portletCategory = (PortletCategory)WebAppPool.get(company.getCompanyId(), WebKeys.PORTLET_CATEGORY);

					portletCategory = PortletCategoryUtil.getRelevantPortletCategory(permissionChecker, user.getCompanyId(), layout, portletCategory, layoutTypePortlet);

					List<PortletCategory> portletCategories = ListUtil.fromCollection(portletCategory.getCategories());

					portletCategories = ListUtil.sort(portletCategories, new PortletCategoryComparator(locale));

					for (PortletCategory curPortletCategory : portletCategories) {
					%>

						<c:if test="<%= !curPortletCategory.isHidden() %>">

							<%
							request.setAttribute(WebKeys.PORTLET_CATEGORY, curPortletCategory);
							%>

							<liferay-util:include page="/html/portal/layout/view/view_category.jsp" />
						</c:if>

					<%
					}
					%>

				</aui:col>
				<aui:col cssClass="<%= panelBodyCssClass %>"  width="<%= 80 %>">
					<%@ include file="/html/portal/layout/view/panel_description.jspf" %>
				</aui:col>
			</aui:row>
		</aui:container>
	</c:when>
	<c:otherwise>
		<%@ include file="/html/portal/layout/view/panel_description.jspf" %>
	</c:otherwise>
</c:choose>

<%@ include file="/html/portal/layout/view/common.jspf" %>