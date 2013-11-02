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

<%@ include file="/html/portlet/dynamic_data_lists/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

DDLRecord record = (DDLRecord)request.getAttribute(WebKeys.DYNAMIC_DATA_LISTS_RECORD);

long formDDMTemplateId = ParamUtil.getLong(request, "formDDMTemplateId");
%>

<div class="record-toolbar" id="<portlet:namespace />recordToolbar"></div>

<aui:script use="aui-toolbar,aui-dialog-iframe-deprecated,liferay-util-window">
	var permissionPopUp = null;

	var toolbarChildren = [
		<c:if test="<%= record != null %>">
			<portlet:renderURL var="viewHistoryURL">
				<portlet:param name="struts_action" value="/dynamic_data_lists/view_record_history" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="recordId" value="<%= String.valueOf(record.getRecordId()) %>" />
				<portlet:param name="formDDMTemplateId" value="<%= String.valueOf(formDDMTemplateId) %>" />
			</portlet:renderURL>

			{
				icon: 'icon-time',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "view-history") %>',
				on: {
					click: function(event) {
						event.domEvent.preventDefault();

						window.location.href = '<%= viewHistoryURL %>';
					}
				}
			}
		</c:if>
	];

	new A.Toolbar(
		{
			boundingBox: '#<portlet:namespace />recordToolbar',
			children: toolbarChildren
		}
	).render();
</aui:script>