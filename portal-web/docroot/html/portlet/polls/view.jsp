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

<%@ include file="/html/portlet/polls/init.jsp" %>

<aui:form method="post" name="fm">

	<%
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("struts_action", "/polls/view");

	List<String> headerNames = new ArrayList<String>();

	headerNames.add("question");
	headerNames.add("num-of-votes");
	headerNames.add("last-vote-date");
	headerNames.add("expiration-date");
	headerNames.add(StringPool.BLANK);

	SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

	int total = PollsQuestionLocalServiceUtil.getQuestionsCount(scopeGroupId);

	searchContainer.setTotal(total);

	List results = PollsQuestionLocalServiceUtil.getQuestions(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd());

	searchContainer.setResults(results);

	List resultRows = searchContainer.getResultRows();

	for (int i = 0; i < results.size(); i++) {
		PollsQuestion question = (PollsQuestion)results.get(i);

		question = question.toEscapedModel();

		ResultRow row = new ResultRow(question, question.getQuestionId(), i);

		PortletURL rowURL = renderResponse.createRenderURL();

		rowURL.setParameter("struts_action", "/polls/view_question");
		rowURL.setParameter("redirect", currentURL);
		rowURL.setParameter("questionId", String.valueOf(question.getQuestionId()));

		// Title

		row.addText(question.getTitle(locale), rowURL);

		// Number of votes

		int votesCount = PollsVoteLocalServiceUtil.getQuestionVotesCount(question.getQuestionId());

		row.addText(String.valueOf(votesCount), rowURL);

		// Last vote date

		if (question.getLastVoteDate() == null) {
			row.addText(LanguageUtil.get(pageContext, "never"), rowURL);
		}
		else {
			row.addDate(question.getLastVoteDate(), rowURL);
		}

		// Expiration date

		if (question.getExpirationDate() == null) {
			row.addText(LanguageUtil.get(pageContext, "never"), rowURL);
		}
		else {
			row.addDate(question.getExpirationDate(), rowURL);
		}

		// Action

		row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/polls/question_action.jsp");

		// Add result row

		resultRows.add(row);
	}

	boolean showAddPollButton = PollsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_QUESTION);
	boolean showPermissionsButton = PollsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);
	%>

	<aui:fieldset>
		<c:if test="<%= showAddPollButton || showPermissionsButton %>">
			<aui:button-row>
				<c:if test="<%= showAddPollButton %>">
					<portlet:renderURL var="editQuestionURL">
						<portlet:param name="struts_action" value="/polls/edit_question" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</portlet:renderURL>

					<aui:button href="<%= editQuestionURL %>" value="add-question" />
				</c:if>

				<c:if test="<%= showPermissionsButton %>">
					<liferay-security:permissionsURL
						modelResource="com.liferay.portlet.polls"
						modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
						resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
						var="permissionsURL"
						windowState="<%= LiferayWindowState.POP_UP.toString() %>"
					/>

					<aui:button href="<%= permissionsURL %>" useDialog="<%= true %>" value="permissions" />
				</c:if>
			</aui:button-row>
		</c:if>

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
	</aui:fieldset>
</aui:form>