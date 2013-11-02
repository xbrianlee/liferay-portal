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

<%@ include file="/html/taglib/ui/table_iterator/init.jsp" %>

</td>

<c:if test="<%= ((listPos + 1) % rowLength) == 0 %>">
	</tr>

	<c:if test="<%= Validator.isNotNull(rowBreak) %>">
		<tr>
			<td colspan="<%= rowLength * 2 - 1 %>">
				<%= rowBreak %>
			</td>
		</tr>
	</c:if>

	<c:if test="<%= (listPos + 1) < list.size() %>">
		<tr>
	</c:if>
</c:if>

<c:if test="<%= (listPos + 1) < list.size() %>">
	<c:if test="<%= (listPos % rowLength) + 1 < rowLength %>">
		<td style="padding-left: <%= rowPadding %>px;"></td>
	</c:if>

	<td valign="<%= rowValign %>">
</c:if>