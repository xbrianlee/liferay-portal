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

<%@ include file="/html/taglib/init.jsp" %>

<%
String randomNamespace = StringUtil.randomId() + StringPool.UNDERLINE;

String className = (String)request.getAttribute("liferay-ui:flags:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-ui:flags:classPK"));
String contentTitle = GetterUtil.getString((String)request.getAttribute("liferay-ui:flags:contentTitle"));
boolean label = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:flags:label"), true);
String message = GetterUtil.getString((String)request.getAttribute("liferay-ui:flags:message"), "flag[action]");
long reportedUserId = GetterUtil.getLong((String)request.getAttribute("liferay-ui:flags:reportedUserId"));
%>

<div class="taglib-flags" title="<liferay-ui:message key='<%= !TrashUtil.isInTrash(className, classPK) ? message : "flags-are-disabled-because-this-entry-is-in-the-recycle-bin" %>' />">
	<liferay-ui:icon
		cssClass="<%= randomNamespace %>"
		image="../ratings/flagged_icon"
		imageHover='<%= !TrashUtil.isInTrash(className, classPK) ? "../ratings/flagged_icon_hover" : StringPool.BLANK %>'
		label="<%= label %>"
		message="<%= message %>"
		url='<%= !TrashUtil.isInTrash(className, classPK) ? "javascript:;" : null %>'
	/>
</div>

<c:if test="<%= !TrashUtil.isInTrash(className, classPK) %>">
	<c:choose>
		<c:when test="<%= PropsValues.FLAGS_GUEST_USERS_ENABLED || themeDisplay.isSignedIn() %>">
			<aui:script use="aui-io-plugin-deprecated,aui-modal">
				var icon = A.one('.<%= randomNamespace %>');

				if (icon) {
					icon.on(
						'click',
						function() {
							var popup = Liferay.Util.Window.getWindow(
								{
									dialog: {
										destroyOnHide: true,
										height: 300,
										width: 400
									},
									title: '<%= UnicodeLanguageUtil.get(pageContext, "report-inappropriate-content") %>'
								}
							);

							var data = Liferay.Util.ns(
								'<%= PortalUtil.getPortletNamespace(PortletKeys.FLAGS) %>',
								{
									className: '<%= className %>',
									classPK: '<%= classPK %>',
									contentTitle: '<%= HtmlUtil.escapeJS(contentTitle) %>',
									contentURL: '<%= HtmlUtil.escapeJS(PortalUtil.getPortalURL(request) + currentURL) %>',
									reportedUserId: '<%= reportedUserId %>'
								}
							);

							popup.plug(
								A.Plugin.IO, {
									data: data,
									uri: '<liferay-portlet:renderURL portletName="<%= PortletKeys.FLAGS %>" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/flags/edit_entry" /></liferay-portlet:renderURL>'
								}
							);
						}
					);
				}
			</aui:script>
		</c:when>
		<c:otherwise>
			<div id="<%= randomNamespace %>signIn" style="display:none">
				<liferay-ui:message key="please-sign-in-to-flag-this-as-inappropriate" />
			</div>

			<aui:script use="aui-modal">
				var icon = A.one('.<%= randomNamespace %>');

				if (icon) {
					icon.on(
						'click',
						function(event) {
							var popup = Liferay.Util.Window.getWindow(
								{
									dialog: {
										bodyContent: A.one('#<%= randomNamespace %>signIn').html(),
										destroyOnHide: true,
										height: 300,
										width: 400
									},
									title: '<%= UnicodeLanguageUtil.get(pageContext, "report-inappropriate-content") %>'
								}
							);

							event.preventDefault();
						}
					);
				}
			</aui:script>
		</c:otherwise>
	</c:choose>
</c:if>