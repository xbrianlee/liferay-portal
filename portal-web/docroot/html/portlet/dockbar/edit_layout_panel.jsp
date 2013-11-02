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

<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%@ include file="/html/portlet/layouts_admin/init_attributes.jspf" %>

<div id="<portlet:namespace />editLayoutContainer">
	<c:choose>
		<c:when test='<%= SessionMessages.contains(renderRequest, "requestProcessed") || ((selPlid == 0) && Validator.isNotNull(closeRedirect)) %>'>

			<%
			String refreshURL = null;

			if ((selPlid == 0) && Validator.isNotNull(closeRedirect)) {
				refreshURL = closeRedirect;
			}
			else {
				refreshURL = (String)SessionMessages.get(renderRequest, portletDisplay.getId() + SessionMessages.KEY_SUFFIX_CLOSE_REDIRECT);
			}

			if (Validator.isNull(refreshURL)) {
				refreshURL = PortalUtil.getLayoutURL(layout, themeDisplay);
			}
			%>

			<aui:script>
				window.location.href = '<%= HtmlUtil.escapeJS(refreshURL) %>';
			</aui:script>
		</c:when>
		<c:otherwise>
			<aui:button cssClass="close pull-right" name="closePanelEdit" value="&times;" />

			<h1><liferay-ui:message key="edit-page" /></h1>

			<c:if test="<%= selPlid > 0 %>">
				<liferay-util:include page="/html/portlet/layouts_admin/edit_layout.jsp">
					<liferay-util:param name="displayStyle" value="panel" />
					<liferay-util:param name="showAddAction" value="<%= Boolean.FALSE.toString() %>" />
				</liferay-util:include>

				<c:if test="<%= themeDisplay.isShowSiteAdministrationIcon() %>">
					<liferay-portlet:renderURL plid="<%= PortalUtil.getControlPanelPlid(company.getCompanyId()) %>" portletName="<%= PortletKeys.GROUP_PAGES %>" varImpl="siteAdministrationURL" windowState="<%= WindowState.NORMAL.toString() %>">
						<portlet:param name="struts_action" value="/group_pages/edit_layouts" />
						<portlet:param name="tabs1" value="public-pages" />
						<portlet:param name="groupId" value="<%= String.valueOf(liveGroupId) %>" />
						<portlet:param name="selPlid" value="<%= String.valueOf(selPlid) %>" />
						<portlet:param name="treeId" value="layoutsTree" />
						<portlet:param name="viewLayout" value="true" />
					</liferay-portlet:renderURL>

					<%
					String siteAdministrationURLString = HttpUtil.setParameter(siteAdministrationURL.toString(), "controlPanelCategory", "current_site");

					siteAdministrationURLString = HttpUtil.setParameter(siteAdministrationURLString, "doAsGroupId", String.valueOf(liveGroupId));
					siteAdministrationURLString = HttpUtil.setParameter(siteAdministrationURLString, "refererPlid", String.valueOf(selPlid));
					%>

					<aui:a cssClass="site-admin-link" href="<%= siteAdministrationURLString %>" label="site-administration" />
				</c:if>
			</c:if>

			<aui:script use="aui-io-request,aui-loading-mask-deprecated,liferay-dockbar">
				A.one('#<portlet:namespace />closePanelEdit').on('click', Liferay.Dockbar.toggleEditLayoutPanel, Liferay.Dockbar);

				var nameInput = A.one('#<portlet:namespace />name');

				if (nameInput) {
					nameInput.focus();
				}

				var loadingMask = A.getBody().plug(A.LoadingMask).loadingmask;

				Liferay.once(
					'submitForm',
					function(event) {
						var form = event.form;

						if (form.hasClass('edit-layout-form')) {
							event.preventDefault();

							<liferay-portlet:renderURL varImpl="redirectURL">
								<portlet:param name="struts_action" value="/layouts_admin/update_layout" />
								<portlet:param name="groupId" value="<%= String.valueOf(liveGroupId) %>" />
							</liferay-portlet:renderURL>

							form.get('<portlet:namespace />redirect').val('<%= HttpUtil.addParameter(redirectURL.toString(), liferayPortletResponse.getNamespace() + "selPlid", selPlid) %>');

							loadingMask.show();

							A.io.request(
								form.attr('action'),
								{
									dataType: 'json',
									form: {
										id: form.attr('id')
									},
									after: {
										success: function(event, id, obj) {
											var response = this.get('responseData');

											var panel = A.one('#<portlet:namespace />editLayoutContainer');

											panel.empty();

											panel.plug(A.Plugin.ParseContent);

											panel.setContent(response);

											loadingMask.hide();
										},
										failure: function(event) {
											loadingMask.hide();
										}
									}
								}
							);
						}
					}
				);
			</aui:script>
		</c:otherwise>
	</c:choose>
</div>