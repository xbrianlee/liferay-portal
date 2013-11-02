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

<%@ include file="/html/common/themes/init.jsp" %>

<%
if ((PropsValues.LOCALE_PREPEND_FRIENDLY_URL_STYLE == 3) && !user.isDefaultUser() && (user.getLocale() != locale)) {
	PortalUtil.addUserLocaleOptionsMessage(request);
}
%>

<c:if test="<%= ShutdownUtil.isInProcess() %>">
	<div class="alert alert-block" id="lfrShutdownMessage">
		<span class="notice-label"><liferay-ui:message key="maintenance-alert" /></span> <span class="notice-date"><%= FastDateFormatFactoryUtil.getTime(locale).format(Time.getDate(CalendarFactoryUtil.getCalendar(timeZone))) %> <%= timeZone.getDisplayName(false, TimeZone.SHORT, locale) %></span>
		<span class="notice-message"><%= LanguageUtil.format(pageContext, "the-portal-will-shutdown-for-maintenance-in-x-minutes", String.valueOf((ShutdownUtil.getInProcess() / Time.MINUTE) + 1), false) %></span>

		<c:if test="<%= Validator.isNotNull(ShutdownUtil.getMessage()) %>">
			<span class="custom-shutdown-message"><%= HtmlUtil.escape(ShutdownUtil.getMessage()) %></span>
		</c:if>
	</div>
</c:if>

<%
String jspPath = (String)PortalMessages.get(request, PortalMessages.KEY_JSP_PATH);
String message = (String)PortalMessages.get(request, PortalMessages.KEY_MESSAGE);

if (Validator.isNotNull(jspPath) || Validator.isNotNull(message)) {
	String cssClass = GetterUtil.getString(PortalMessages.get(request, PortalMessages.KEY_CSS_CLASS), "alert-info");
	String portletId = (String)PortalMessages.get(request, PortalMessages.KEY_PORTLET_ID);
	int timeout = GetterUtil.getInteger(PortalMessages.get(request, PortalMessages.KEY_TIMEOUT), 10000);
	boolean useAnimation = GetterUtil.getBoolean(PortalMessages.get(request, PortalMessages.KEY_ANIMATION), true);

	if (useAnimation) {
		cssClass = cssClass + " hide";
	}
%>

	<div class="alert alert-block taglib-portal-message <%= cssClass %>" id="portalMessageContainer">
		<c:choose>
			<c:when test="<%= Validator.isNotNull(jspPath) %>">
				<liferay-util:include page="<%= jspPath %>" portletId="<%= portletId %>" />
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="<%= message %>" /><button type="button" class="close">&times;</button>
			</c:otherwise>
		</c:choose>
	</div>

	<aui:script use="liferay-notice">
		var portalMessageContainer = A.one('#portalMessageContainer');

		var banner = new Liferay.Notice(
			{
				animationConfig:
					{
						duration: 2,
						top: '0px'
					},
				closeText: false,
				node: '#portalMessageContainer',
				noticeClass: 'hide <%= cssClass %>',
				timeout: <%= timeout %>,
				toggleText: false,
				useAnimation: <%= useAnimation %>
			}
		);

		banner.show();
	</aui:script>

<%
}
%>