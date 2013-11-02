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

<%@ include file="/html/taglib/ui/captcha/init.jsp" %>

<%
String url = (String)request.getAttribute("liferay-ui:captcha:url");

boolean captchaEnabled = false;

try {
	if (portletRequest != null) {
		captchaEnabled = CaptchaUtil.isEnabled(portletRequest);
	}
	else {
		captchaEnabled = CaptchaUtil.isEnabled(request);
	}
}
catch (CaptchaMaxChallengesException cmce) {
	captchaEnabled = true;
}
%>

<c:if test="<%= captchaEnabled %>">
	<div class="taglib-captcha">
		<img alt="<liferay-ui:message key="text-to-identify" />" class="captcha" id="<portlet:namespace />captcha" src="<%= url %>" />

		<liferay-ui:icon cssClass="refresh" id="refreshCaptcha" image="../portlet/refresh" label="<%= false %>" localizeMessage="<%= true %>" message="refresh-captcha" url="javascript:;" />

		<aui:input label="text-verification" name="captchaText" size="10" type="text" value="">
			<aui:validator name="required" />
		</aui:input>
	</div>

	<aui:script use="aui-base">
		A.one('#<portlet:namespace />refreshCaptcha').on(
			'click',
			function() {
				var url = Liferay.Util.addParams('t=' + A.Lang.now(), '<%= url %>');

				A.one('#<portlet:namespace />captcha').attr('src', url);
			}
		);
	</aui:script>
</c:if>