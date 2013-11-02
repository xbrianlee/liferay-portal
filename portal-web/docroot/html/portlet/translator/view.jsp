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

<%@ include file="/html/portlet/translator/init.jsp" %>

<%
Translation translation = (Translation)request.getAttribute(WebKeys.TRANSLATOR_TRANSLATION);

Map<String, String> languageIdsMap = TranslatorUtil.getLanguageIdsMap(locale);

if (translation == null) {
	String translationId = PropsUtil.get(PropsKeys.TRANSLATOR_DEFAULT_LANGUAGES);

	String[] fromAndTolanguageIds = TranslatorUtil.getFromAndToLanguageIds(translationId, languageIdsMap);

	if (fromAndTolanguageIds != null) {
		String fromLanguageId = fromAndTolanguageIds[0];
		String toLanguageId = fromAndTolanguageIds[1];

		translation = new Translation(fromLanguageId, toLanguageId, StringPool.BLANK, StringPool.BLANK);
	}
}
%>

<c:choose>
	<c:when test="<%= translation == null %>">
		<div class="alert alert-error">
			<liferay-ui:message key="please-configure-valid-default-languages" />
		</div>
	</c:when>
	<c:otherwise>
		<portlet:actionURL var="portletURL" />

		<aui:form accept-charset="UTF-8" action="<%= portletURL %>" method="post" name="fm">
			<liferay-ui:error exception="<%= MicrosoftTranslatorException.class %>">

				<%
				MicrosoftTranslatorException mte = (MicrosoftTranslatorException)errorException;

				String message = mte.getMessage();

				if (message.startsWith("ACS50012") || message.startsWith("ACS70002") || message.startsWith("ACS90011")) {
				%>

					<liferay-ui:message key="please-configure-a-valid-microsoft-translator-license" />

				<%
				}
				%>

			</liferay-ui:error>

			<c:if test="<%= Validator.isNotNull(translation.getToText()) %>">
				<%= HtmlUtil.escape(translation.getToText()) %>
			</c:if>

			<aui:fieldset>
				<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" cssClass="lfr-textarea-container" label="" name="text" type="textarea" value="<%= translation.getFromText() %>" wrap="soft" />

				<aui:select label="language-from" name="fromLanguageId">

					<%
					for (Map.Entry<String, String> entry : languageIdsMap.entrySet()) {
						String languageId = entry.getKey();
						String languageName = entry.getValue();
					%>

						<aui:option label="<%= languageName %>" selected="<%= languageId.equals(translation.getFromLanguageId()) %>" value="<%= languageId %>" />

					<%
					}
					%>

				</aui:select>

				<aui:select label="language-to" name="toLanguageId">

					<%
					for (Map.Entry<String, String> entry : languageIdsMap.entrySet()) {
						String languageId = entry.getKey();
						String languageName = entry.getValue();
					%>

						<aui:option label="<%= languageName %>" selected="<%= languageId.equals(translation.getToLanguageId()) %>" value="<%= languageId %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>

			<aui:button-row>
				<aui:button type="submit" value="translate" />
			</aui:button-row>
		</aui:form>
	</c:otherwise>
</c:choose>