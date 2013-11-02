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

<%@ include file="/html/portal/init.jsp" %>

<%
String referer = ParamUtil.getString(request, WebKeys.REFERER, themeDisplay.getPathMain());

if (referer.equals(themeDisplay.getPathMain() + "/portal/update_email_address")) {
	referer = themeDisplay.getPathMain() + "?doAsUserId=" + themeDisplay.getDoAsUserId();
}

PasswordPolicy passwordPolicy = user.getPasswordPolicy();

String ticketKey = ParamUtil.getString(request, "ticketKey");
%>

<aui:form action='<%= themeDisplay.getPathMain() + "/portal/verify_email_address" %>' method="post" name="fm">
	<aui:input name="p_l_id" type="hidden" value="<%= layout.getPlid() %>" />
	<aui:input name="p_auth" type="hidden" value="<%= AuthTokenUtil.getToken(request) %>" />
	<aui:input name="doAsUserId" type="hidden" value="<%= themeDisplay.getDoAsUserId() %>" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="<%= WebKeys.REFERER %>" type="hidden" value="<%= referer %>" />

	<div class="alert alert-info">
		<liferay-ui:message key="please-enter-your-verification-code" />
	</div>

	<c:if test="<%= !SessionErrors.isEmpty(request) %>">
		<c:choose>
			<c:when test="<%= SessionErrors.contains(request, DuplicateUserEmailAddressException.class.getName()) %>">
				<div class="alert alert-error">
					<liferay-ui:message key="the-email-address-you-requested-is-already-taken" />
				</div>
			</c:when>
			<c:when test="<%= SessionErrors.contains(request, ReservedUserEmailAddressException.class.getName()) %>">
				<div class="alert alert-error">
					<liferay-ui:message key="the-email-address-you-requested-is-reserved" />
				</div>
			</c:when>
			<c:when test="<%= SessionErrors.contains(request, UserEmailAddressException.class.getName()) %>">
				<div class="alert alert-error">
					<liferay-ui:message key="please-enter-a-valid-email-address" />
				</div>
			</c:when>
			<c:otherwise>
				<div class="alert alert-error">
					<liferay-ui:message key="please-enter-a-valid-verification-code" />
				</div>
			</c:otherwise>
		</c:choose>
	</c:if>

	<aui:input autoFocus="<%= true %>" class="lfr-input-text-container" label="email-verification-code" name="ticketKey" size="36" type="text" value="<%= ticketKey %>" />

	<aui:button-row>
		<aui:button type="submit" value="verify" />

		<c:if test="<%= themeDisplay.isSignedIn() && !user.isEmailAddressVerified() %>">
			<aui:button href='<%= themeDisplay.getPathMain() + "/portal/verify_email_address?cmd=" + Constants.SEND + "&referer=" + HttpUtil.encodeURL(referer) %>' value="send-new-verification-code" />

			<aui:button href='<%= themeDisplay.getPathMain() + "/portal/update_email_address?referer=" + HttpUtil.encodeURL(referer) %>' value="change-email-address" />
		</c:if>
	</aui:button-row>
</aui:form>