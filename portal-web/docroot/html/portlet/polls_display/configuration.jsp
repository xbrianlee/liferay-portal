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

<%@ include file="/html/portlet/polls_display/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

questionId = ParamUtil.getLong(request, "questionId", questionId);

List<PollsQuestion> questions = PollsQuestionLocalServiceUtil.getQuestions(scopeGroupId);

if (scopeGroupId != themeDisplay.getCompanyGroupId()) {
	questions = ListUtil.copy(questions);

	questions.addAll(PollsQuestionLocalServiceUtil.getQuestions(themeDisplay.getCompanyGroupId()));
}
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:error exception="<%= NoSuchQuestionException.class %>" message="the-question-could-not-be-found" />

	<c:choose>
		<c:when test="<%= !questions.isEmpty() %>">
			<aui:fieldset>
				<aui:select label="question" name="preferences--questionId--">
					<aui:option value="" />

					<%
					for (PollsQuestion question : questions) {
						question = question.toEscapedModel();
					%>

						<aui:option label="<%= question.getTitle(locale) %>" selected="<%= questionId == question.getQuestionId() %>" value="<%= question.getQuestionId() %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>
		</c:when>
		<c:otherwise>
			<div class="alert alert-info">
				<liferay-ui:message key="there-are-no-available-questions-for-selection" />
			</div>
		</c:otherwise>
	</c:choose>

	<aui:button-row>
		<aui:button disabled="<%= questions.isEmpty() %>" type="submit" />
	</aui:button-row>
</aui:form>