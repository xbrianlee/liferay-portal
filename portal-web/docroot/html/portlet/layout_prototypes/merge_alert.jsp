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

<%@ include file="/html/portlet/layout_prototypes/init.jsp" %>

<%
LayoutPrototype layoutPrototype = (LayoutPrototype)request.getAttribute("edit_layout_prototype.jsp-layoutPrototype");
String redirect = (String)request.getAttribute("edit_layout_prototype.jsp-redirect");
long selPlid = GetterUtil.getLong((String)request.getAttribute("edit_layout_prototype.jsp-selPlid"));

int mergeFailCount = SitesUtil.getMergeFailCount(layoutPrototype);
%>

<c:if test="<%= mergeFailCount > PropsValues.LAYOUT_PROTOTYPE_MERGE_FAIL_THRESHOLD %>">

	<%
	boolean merge = false;

	String randomNamespace = PortalUtil.generateRandomKey(request, "portlet_layout_prototypes_merge_alert") + StringPool.UNDERLINE;

	PortletURL portletURL = liferayPortletResponse.createActionURL();

	portletURL.setParameter("redirect", redirect);
	portletURL.setParameter("layoutPrototypeId",String.valueOf(layoutPrototype.getLayoutPrototypeId()));

	if (selPlid > 0) {
		portletURL.setParameter("struts_action", "/layouts_admin/edit_layouts");
		portletURL.setParameter(Constants.CMD, "reset_merge_fail_count_and_merge");
		portletURL.setParameter("selPlid", String.valueOf(selPlid));

		merge = true;
	}
	else {
		portletURL.setParameter("struts_action", "/layout_prototypes/edit_layout_prototype");
		portletURL.setParameter(Constants.CMD, "reset_merge_fail_count");
	}
	%>

	<span class="alert alert-block">
		<liferay-ui:message arguments='<%= new Object[] {mergeFailCount, "page-template"} %>' key="the-propagation-of-changes-from-the-x-has-been-disabled-temporarily-after-x-errors" />

		<liferay-ui:message arguments="page-template" key='<%= merge ? "click-reset-and-propagate-to-reset-the-failure-count-and-propagate-changes-from-the-x" : "click-reset-to-reset-the-failure-count-and-reenable-propagation" %>' />

		<aui:button id='<%= randomNamespace + "resetButton" %>' value='<%= merge ? "reset-and-propagate" : "reset" %>' />
	</span>

	<aui:script use="aui-base">
		var resetButton= A.one('#<%= randomNamespace %>resetButton');

		resetButton.on(
			'click',
			function(event) {
				submitForm(document.hrefFm, '<%= portletURL.toString() %>');
			}
		);
	</aui:script>
</c:if>