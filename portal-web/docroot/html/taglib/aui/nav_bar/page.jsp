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

<%@ include file="/html/taglib/aui/nav_bar/init.jsp" %>

<div class="navbar <%= cssClass %>" id="<%= id %>" <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
	<div class="navbar-inner">
		<div class="container">
			<%= responsiveButtons %>

			<%= bodyContentString %>

			<aui:script use="aui-base,event-outside">
				A.one('#<%= id %>').delegate(
					'click',
					function(event) {
						var btnNavbar = event.currentTarget;

						var navId = btnNavbar.attr('data-navId');

						var navbarCollapse = A.one('#' + navId + 'NavbarCollapse');

						if (navbarCollapse) {
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
										}
									}
								);
							}

							navbarCollapse.toggleClass('open');

							Liferay.Data['<%= id %>Handle'] = handle;
						}
					},
					'.btn-navbar'
				);
			</aui:script>
		</div>
	</div>
</div>