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

<%@ include file="/html/portlet/invitation/init.jsp" %>

<c:choose>
	<c:when test="<%= windowState.equals(WindowState.NORMAL) %>">
		<liferay-ui:success key="invitationSent" message="your-invitations-have-been-sent" />

		<portlet:renderURL var="viewURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="struts_action" value="/invitation/view" />
		</portlet:renderURL>

		<aui:a href="<%= viewURL %>" label="invite-friends" />
	</c:when>
	<c:otherwise>
		<portlet:actionURL var="portletURL">
			<portlet:param name="struts_action" value="/invitation/view" />
		</portlet:actionURL>

		<portlet:renderURL var="redirectURL" windowState="<%= WindowState.NORMAL.toString() %>" />

		<aui:form action="<%= portletURL %>" method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />

			<div class="alert alert-info">
				<liferay-ui:message arguments="<%= InvitationUtil.getEmailMessageMaxRecipients() %>" key="enter-up-to-x-email-addresses-of-friends-you-would-like-to-invite" />
			</div>

			<%
			Set invalidEmailAddresses = (Set)SessionErrors.get(renderRequest, "emailAddresses");

			int emailMessageMaxRecipients = InvitationUtil.getEmailMessageMaxRecipients();

			for (int i = 0; i < emailMessageMaxRecipients; i++) {
				String emailAddress = ParamUtil.getString(request, "emailAddress" + i);
			%>

				<c:if test='<%= (invalidEmailAddresses != null) && invalidEmailAddresses.contains("emailAddress" + i) %>'>
					<div class="alert alert-error">
						<liferay-ui:message key="please-enter-a-valid-email-address" />
					</div>
				</c:if>

				<aui:input cssClass="lfr-input-text-container" label="" name='<%= "emailAddress" + i %>' size="65" title='<%= LanguageUtil.get(pageContext, "email-address") + StringPool.SPACE + (i + 1) %>' type="text" value="<%= emailAddress %>" />

			<%
			}
			%>

			<aui:button-row>
				<aui:button type="submit" value="invite-friends" />
			</aui:button-row>
		</aui:form>
	</c:otherwise>
</c:choose>