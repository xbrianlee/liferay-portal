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

<%@ include file="/html/portlet/roles_admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] objArray = (Object[])row.getObject();

Role role = (Role)objArray[0];
String target = (String)objArray[3];
Boolean supportsFilterByGroup = (Boolean)objArray[5];
List groups = (List)objArray[6];
long[] groupIdsArray = (long[])objArray[7];
List groupNames = (List)objArray[8];
%>

<aui:input name='<%= "groupIds" + target %>' type="hidden" value="<%= StringUtil.merge(groupIdsArray) %>" />
<aui:input name='<%= "groupNames" + target %>' type="hidden" value='<%= StringUtil.merge(groupNames, "@@") %>' />

<div id="<portlet:namespace />groupDiv<%= target %>">
	<span class="permission-scopes" id="<portlet:namespace />groupHTML<%= target %>">

		<%
		if (supportsFilterByGroup && !groups.isEmpty()) {
			for (int i = 0; i < groups.size(); i++) {
				Group group = (Group)groups.get(i);

				String taglibHREF = "javascript:" + liferayPortletResponse.getNamespace() + "removeGroup(" + i + ", '" + target + "');";
		%>

				<span class="lfr-token">
					<span class="lfr-token-text"><%= HtmlUtil.escape(group.getDescriptiveName(locale)) %></span>

					<aui:a cssClass="icon icon-remove lfr-token-close" href="<%= taglibHREF %>" />
				</span>

		<%
			}
		}
		else if (role.getType() == RoleConstants.TYPE_REGULAR) {
		%>

			<%= LanguageUtil.get(pageContext, "all-sites") %>

		<%
		}
		%>

	</span>

	<%
	String targetId = target.replace(".", "");
	%>

	<c:if test="<%= supportsFilterByGroup %>">
		<portlet:renderURL var="selectCommunityURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="struts_action" value="/roles_admin/select_site" />
			<portlet:param name="includeCompany" value="<%= Boolean.TRUE.toString() %>" />
			<portlet:param name="includeUserPersonalSite" value="<%= Boolean.TRUE.toString() %>" />
			<portlet:param name="target" value="<%= target %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			id="<%= targetId %>"
			image="configuration"
			label="<%= true %>"
			message="change"
			url="javascript:;"
		/>

		<aui:script use="aui-base">
			A.one('#<portlet:namespace /><%= targetId %>').on(
				'click',
				function(event) {
					Liferay.Util.selectEntity(
						{
							dialog: {
								constrain: true,
								modal: true,
								width: 600
							},
							id: '<portlet:namespace />selectGroup<%= targetId %>',
							title: '<liferay-ui:message arguments="site" key="select-x" />',
							uri: '<%= selectCommunityURL.toString() %>'
						}
					);
				}
			);
		</aui:script>
	</c:if>
</div>