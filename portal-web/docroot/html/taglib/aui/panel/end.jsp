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

<%@ include file="/html/taglib/aui/panel/init.jsp" %>

</div>

<aui:script use="anim,aui-panel-deprecated">
	var container = new A.Panel(
		{
			bodyContent: A.one('#<%= id %>bodyContent'),
			collapsed: <%= collapsed %>,
			collapsible: <%= collapsible %>,
			contentBox: '#<%= id %>',
			headerContent: '<liferay-ui:message key="<%= label %>" />'

			<c:if test="<%= toolTags != null %>">
				,icons: [

				<%
				for (int i = 0; i < toolTags.size(); i++) {
					ToolTag toolTag = toolTags.get(i);
				%>

					{
						icon: '<%= toolTag.getIcon() %>',
						id: '<%= toolTag.getId() %>',
						on: {
							click: function(event) {
								<%= toolTag.getHandler() %>
							}
						}

					}

					<c:if test="<%= (i + 1) < toolTags.size() %>">
						,
					</c:if>

				<%
				}
				%>

				]
			</c:if>

		}
	).render();
</aui:script>