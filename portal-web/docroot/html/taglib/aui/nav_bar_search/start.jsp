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

<%@ include file="/html/taglib/aui/nav_bar_search/init.jsp" %>

<div class="collapse nav-collapse" id="<%= id %>NavbarSearchCollapse">
	<div class="navbar-search <%= cssClass %>" id="<%= id %>" <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
		<c:if test="<%= Validator.isNotNull(file) %>">
			<liferay-ui:search-form
				page="<%= file %>"
				searchContainer="<%= searchContainer %>"
			/>
		</c:if>

		<aui:script use="aui-base,event-outside">
			A.one('#<%= id %>NavbarBtn').on(
				'click',
				function(event) {
					var btnNavbar = event.currentTarget;

					var navbar = btnNavbar.ancestor('.navbar');

					var navbarCollapse = A.one('#<%= id %>NavbarSearchCollapse');

					var handle = Liferay.Data['<%= id %>Handle'];

					if (navbarCollapse.hasClass('open') && handle) {
						handle.detach();

						handle = null;
					}
					else {
						handle = navbarCollapse.on(
							'mousedownoutside',
							function(event) {
								if (!btnNavbar.contains(event.target)) {
									Liferay.Data['<%= id %>Handle'] = null;

									handle.detach();

									navbarCollapse.removeClass('open');

									if (navbar) {
										navbar.all('.btn-navbar, .nav').show();
									}
								}
							}
						);
					}

					navbarCollapse.toggleClass('open');

					if (navbar) {
						navbar.all('.btn-navbar, .nav').hide();
					}

					Liferay.Data['<%= id %>Handle'] = handle;
				}
			);
		</aui:script>