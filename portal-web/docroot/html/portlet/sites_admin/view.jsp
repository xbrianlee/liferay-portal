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

<%@ include file="/html/portlet/sites_admin/init.jsp" %>

<%
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "browse");

String sitesListView = ParamUtil.get(request, "sitesListView", SiteConstants.LIST_VIEW_TREE);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/sites_admin/view");
portletURL.setParameter("sitesListView", sitesListView);

String portletURLString = portletURL.toString();

PortletURL searchURL = renderResponse.createRenderURL();

searchURL.setParameter("struts_action", "/sites_admin/view");
searchURL.setParameter("sitesListView", SiteConstants.LIST_VIEW_FLAT_SITES);
searchURL.setParameter("toolbarItem", "view-all-sites");

pageContext.setAttribute("searchURL", searchURL);

String searchURLString = searchURL.toString();
%>

<liferay-ui:success key="membershipRequestSent" message="your-request-was-sent-you-will-receive-a-reply-by-email" />

<aui:form action="<%= searchURLString %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= portletURLString %>" />
	<aui:input name="toolbarItem" type="hidden" value="<%= toolbarItem %>" />

	<liferay-ui:error exception="<%= NoSuchLayoutSetException.class %>">

		<%
		NoSuchLayoutSetException nslse = (NoSuchLayoutSetException)errorException;

		PKParser pkParser = new PKParser(nslse.getMessage());

		long groupId = pkParser.getLong("groupId");

		Group group = GroupLocalServiceUtil.getGroup(groupId);
		%>

		<liferay-ui:message arguments="<%= HtmlUtil.escape(group.getDescriptiveName(locale)) %>" key="site-x-does-not-have-any-private-pages" />
	</liferay-ui:error>

	<liferay-ui:error exception="<%= RequiredGroupException.class %>">

		<%
		RequiredGroupException rge = (RequiredGroupException)errorException;
		%>

		<c:if test="<%= rge.getType() == RequiredGroupException.CURRENT_GROUP %>">
			<liferay-ui:message key="you-cannot-delete-this-site-because-you-are-currently-accessing-this-site" />
		</c:if>

		<c:if test="<%= rge.getType() == RequiredGroupException.PARENT_GROUP %>">
			<liferay-ui:message key="you-cannot-delete-sites-that-have-subsites" />
		</c:if>

		<c:if test="<%= rge.getType() == RequiredGroupException.SYSTEM_GROUP %>">
			<liferay-ui:message key="the-site-cannot-be-deleted-or-deactivated-because-it-is-a-required-system-site" />
		</c:if>
	</liferay-ui:error>

	<c:choose>
		<c:when test="<%= sitesListView.equals(SiteConstants.LIST_VIEW_FLAT_SITES) %>">
			<%@ include file="/html/portlet/sites_admin/view_flat_sites.jspf" %>
		</c:when>
		<c:otherwise>
			<%@ include file="/html/portlet/sites_admin/view_tree.jspf" %>
		</c:otherwise>
	</c:choose>
</aui:form>

<aui:script>
	Liferay.Util.toggleSearchContainerButton('#<portlet:namespace />delete', '#<portlet:namespace /><%= searchContainerReference.getId() %>SearchContainer', document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

	Liferay.provide(
		window,
		'<portlet:namespace />deleteSites',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this") %>')) {
				document.<portlet:namespace />fm.method = "post";
				document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.DELETE %>";
				document.<portlet:namespace />fm.<portlet:namespace />redirect.value = document.<portlet:namespace />fm.<portlet:namespace />sitesRedirect.value;
				document.<portlet:namespace />fm.<portlet:namespace />deleteGroupIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

				submitForm(document.<portlet:namespace />fm, '<portlet:actionURL><portlet:param name="struts_action" value="/sites_admin/edit_site" /></portlet:actionURL>');
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>