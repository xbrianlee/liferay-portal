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

<%@ include file="/html/portlet/recent_documents/init.jsp" %>

<%
List fileRanks = DLAppLocalServiceUtil.getFileRanks(scopeGroupId, user.getUserId());
%>

<c:choose>
	<c:when test="<%= fileRanks.isEmpty() %>">
		<liferay-ui:message key="there-are-no-recent-downloads" />
	</c:when>
	<c:otherwise>
		<table class="lfr-table">

		<%
		for (int i = 0; i < fileRanks.size(); i++) {
			DLFileRank fileRank = (DLFileRank)fileRanks.get(i);

			try {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileRank.getFileEntryId());

				fileEntry = fileEntry.toEscapedModel();

				PortletURL rowURL = renderResponse.createActionURL();

				rowURL.setParameter("struts_action", "/recent_documents/get_file");
				rowURL.setParameter("fileEntryId", String.valueOf(fileRank.getFileEntryId()));
				rowURL.setWindowState(LiferayWindowState.EXCLUSIVE);
		%>

				<tr>
					<td>
						<a href="<%= rowURL.toString() %>"><img align="left" alt="" border="0" src="<%= themeDisplay.getPathThemeImages() %>/file_system/small/<%= fileEntry.getIcon() %>.png" /><%= fileEntry.getTitle() %></a>
					</td>
				</tr>

		<%
			}
			catch (Exception e) {
			}
		}
		%>

		</table>
	</c:otherwise>
</c:choose>