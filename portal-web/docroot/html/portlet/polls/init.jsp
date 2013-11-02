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

<%@ include file="/html/portlet/init.jsp" %>

<%@ page import="com.liferay.portlet.polls.DuplicateVoteException" %><%@
page import="com.liferay.portlet.polls.NoSuchChoiceException" %><%@
page import="com.liferay.portlet.polls.NoSuchQuestionException" %><%@
page import="com.liferay.portlet.polls.QuestionChoiceException" %><%@
page import="com.liferay.portlet.polls.QuestionDescriptionException" %><%@
page import="com.liferay.portlet.polls.QuestionExpirationDateException" %><%@
page import="com.liferay.portlet.polls.QuestionTitleException" %><%@
page import="com.liferay.portlet.polls.action.EditQuestionAction" %><%@
page import="com.liferay.portlet.polls.model.PollsChoice" %><%@
page import="com.liferay.portlet.polls.model.PollsQuestion" %><%@
page import="com.liferay.portlet.polls.model.PollsVote" %><%@
page import="com.liferay.portlet.polls.service.PollsChoiceLocalServiceUtil" %><%@
page import="com.liferay.portlet.polls.service.PollsQuestionLocalServiceUtil" %><%@
page import="com.liferay.portlet.polls.service.PollsVoteLocalServiceUtil" %><%@
page import="com.liferay.portlet.polls.service.permission.PollsPermission" %><%@
page import="com.liferay.portlet.polls.service.permission.PollsQuestionPermission" %><%@
page import="com.liferay.portlet.polls.util.PollsUtil" %>

<%
Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
%>

<%@ include file="/html/portlet/polls/init-ext.jsp" %>