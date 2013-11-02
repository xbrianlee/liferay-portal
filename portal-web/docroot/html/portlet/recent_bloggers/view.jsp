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

<%@ include file="/html/portlet/recent_bloggers/init.jsp" %>

<%
List statsUsers = null;

if (selectionMethod.equals("users")) {
	if (organizationId > 0) {
		statsUsers = BlogsStatsUserLocalServiceUtil.getOrganizationStatsUsers(organizationId, 0, max, new StatsUserLastPostDateComparator());
	}
	else {
		statsUsers = BlogsStatsUserLocalServiceUtil.getGroupsStatsUsers(company.getCompanyId(), scopeGroupId, 0, max);
	}
}
else {
	statsUsers = BlogsStatsUserLocalServiceUtil.getGroupStatsUsers(scopeGroupId, 0, max);
}
%>

<c:choose>
	<c:when test="<%= statsUsers.isEmpty() %>">
		<liferay-ui:message key="there-are-no-recent-bloggers" />
	</c:when>
	<c:otherwise>

		<%
		SearchContainer searchContainer = new SearchContainer();

		List<String> headerNames = new ArrayList<String>();

		headerNames.add("user");
		headerNames.add("posts");
		headerNames.add("date");

		if (displayStyle.equals("user-name")) {
			searchContainer.setHeaderNames(headerNames);
		}

		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < statsUsers.size(); i++) {
			BlogsStatsUser statsUser = (BlogsStatsUser)statsUsers.get(i);

			try {
				Group group = GroupLocalServiceUtil.getGroup(statsUser.getGroupId());
				User user2 = UserLocalServiceUtil.getUserById(statsUser.getUserId());

				Date now = new Date();

				int entryCount = BlogsEntryLocalServiceUtil.getGroupUserEntriesCount(group.getGroupId(), user2.getUserId(), now, WorkflowConstants.STATUS_APPROVED);

				List entries = BlogsEntryLocalServiceUtil.getGroupUserEntries(group.getGroupId(), user2.getUserId(), now, WorkflowConstants.STATUS_APPROVED, 0, 1);

				if (entries.size() == 1) {
					BlogsEntry entry = (BlogsEntry)entries.get(0);

					StringBundler sb = new StringBundler(4);

					sb.append(themeDisplay.getPathMain());
					sb.append("/blogs/find_entry?entryId=");
					sb.append(entry.getEntryId());
					sb.append("&showAllEntries=0");

					String rowHREF = sb.toString();

					ResultRow row = new ResultRow(new Object[] {statsUser, rowHREF}, statsUser.getStatsUserId(), i);

					if (displayStyle.equals("user-name")) {

						// User

						row.addText(HtmlUtil.escape(user2.getFullName()), rowHREF);

						// Number of posts

						row.addText(String.valueOf(entryCount), rowHREF);

						// Last post date

						row.addDate(entry.getModifiedDate(), rowHREF);
					}
					else {

						// User display

						row.addJSP("/html/portlet/recent_bloggers/user_display.jsp");
					}

					// Add result row

					resultRows.add(row);
				}
			}
			catch (Exception e) {
			}
		}
		%>

		<liferay-ui:search-iterator paginate="<%= false %>" searchContainer="<%= searchContainer %>" />
	</c:otherwise>
</c:choose>