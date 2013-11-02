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

<%@ include file="/html/taglib/ui/diff/init.jsp" %>

<%
String sourceName = (String)request.getAttribute("liferay-ui:diff:sourceName");
String targetName = (String)request.getAttribute("liferay-ui:diff:targetName");
List<DiffResult>[] diffResults = (List<DiffResult>[])request.getAttribute("liferay-ui:diff:diffResults");

List<DiffResult> sourceResults = diffResults[0];
List<DiffResult> targetResults = diffResults[1];
%>

<c:choose>
	<c:when test="<%= !sourceResults.isEmpty() %>">
		<table class="table table-bordered table-hover table-striped" id="taglib-diff-results">
		<tr>
			<td class="table-cell">
				<%= sourceName %>
			</td>
			<td class="table-cell">
				<%= targetName %>
			</td>
		</tr>

		<%
		for (int i = 0; i < sourceResults.size(); i++) {
			DiffResult sourceResult = sourceResults.get(i);
			DiffResult targetResult = targetResults.get(i);
		%>

			<tr>
				<th class="table-header">
					<liferay-ui:message key="line" /> <%= sourceResult.getLineNumber() %>
				</th>
				<th class="table-header">
					<liferay-ui:message key="line" /> <%= targetResult.getLineNumber() %>
				</th>
			</tr>

			<tr>
				<td class="table-cell" width="50%">
					<table class="taglib-diff-table">

					<%
					for (String changedLine : sourceResult.getChangedLines()) {
					%>

						<tr class="lfr-top">
							<%= _processColumn(changedLine) %>
						</tr>

					<%
					}
					%>

					</table>
				</td>
				<td class="lfr-top" width="50%">
					<table class="taglib-diff-table">

					<%
					for (String changedLine : targetResult.getChangedLines()) {
					%>

						<tr class="lfr-top">
							<%= _processColumn(changedLine) %>
						</tr>

					<%
					}
					%>

					</table>
				</td>
			</tr>

		<%
		}
		%>

		</table>
	</c:when>
	<c:otherwise>
		<%= LanguageUtil.format(pageContext, "there-are-no-differences-between-x-and-x", new Object[] {sourceName, targetName}) %>
	</c:otherwise>
</c:choose>

<%!
private static String _processColumn(String changedLine) {
	changedLine = changedLine.replaceAll(" ", "&nbsp;");
	changedLine = changedLine.replaceAll("\t", "&nbsp;&nbsp;&nbsp;");

	String column = "<td>" + changedLine + "</td>";

	if (changedLine.equals(StringPool.BLANK)) {
		return "<td>&nbsp;</td>";
	}
	else if (changedLine.equals(Diff.CONTEXT_LINE)) {
		return "<td class=\"taglib-diff-context\">&nbsp;</td>";
	}
	else if (changedLine.equals(Diff.OPEN_INS + Diff.CLOSE_INS)) {
		return "<td class=\"taglib-diff-addedline\">&nbsp;</td>";
	}
	else if (changedLine.equals(Diff.OPEN_DEL + Diff.CLOSE_DEL)) {
		return "<td class=\"taglib-diff-deletedline\">&nbsp;</td>";
	}

	return column;
}
%>