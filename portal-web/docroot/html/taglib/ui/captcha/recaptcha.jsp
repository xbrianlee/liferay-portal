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

<noscript>
	<iframe frameborder="0" height="300" src="<%= HttpUtil.protocolize(PropsValues.CAPTCHA_ENGINE_RECAPTCHA_URL_NOSCRIPT, request.isSecure()) %><%= PrefsPropsUtil.getString(PropsKeys.CAPTCHA_ENGINE_RECAPTCHA_KEY_PUBLIC, PropsValues.CAPTCHA_ENGINE_RECAPTCHA_KEY_PUBLIC) %>" width="500"></iframe><br />

	<textarea cols="40" name="recaptcha_challenge_field" rows="3"></textarea>

	<input name="recaptcha_response_field" type="hidden" value="manual_challenge" />
</noscript>

<aui:script position="inline">
	var RecaptchaOptions = {
		lang : '<%= locale.getLanguage() %>',
		theme : 'white'
	};
</aui:script>

<script src="<%= HttpUtil.protocolize(PropsValues.CAPTCHA_ENGINE_RECAPTCHA_URL_SCRIPT, request.isSecure()) %><%= PrefsPropsUtil.getString(PropsKeys.CAPTCHA_ENGINE_RECAPTCHA_KEY_PUBLIC, PropsValues.CAPTCHA_ENGINE_RECAPTCHA_KEY_PUBLIC) %>" type="text/javascript"></script>