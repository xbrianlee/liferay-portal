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

<%
String className = ParamUtil.getString(request, "className");
long classPK = ParamUtil.getLong(request, "classPK");
String contentTitle = ParamUtil.getString(request, "contentTitle");
String contentURL = ParamUtil.getString(request, "contentURL");
long reportedUserId = ParamUtil.getLong(request, "reportedUserId");
%>

<style type="text/css">
	.portlet-flags .form fieldset {
		border: none;
		padding: 0;
		width: 100%;
	}
</style>

<div class="portlet-flags" id="<portlet:namespace />flagsPopup">
	<aui:form method="post" name="flagsForm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "flag();" %>'>
		<p>
			<%= LanguageUtil.format(pageContext, "you-are-about-to-report-a-violation-of-our-x-terms-of-use.-all-reports-are-strictly-confidential", themeDisplay.getPathMain() + "/portal/terms_of_use") %>
		</p>

		<aui:fieldset>
			<aui:select label="reason-for-the-report" name="reason">
				<aui:option value="" />

				<%
				for (String reason : PropsValues.FLAGS_REASONS) {
				%>

					<aui:option label="<%= reason %>" />

				<%
				}
				%>

				<aui:option label="other" />
			</aui:select>

			<span class="hide" id="<portlet:namespace />otherReasonContainer">
				<aui:input name="otherReason" />
			</span>

			<c:if test="<%= !themeDisplay.isSignedIn() %>">
				<aui:input label="email-address" name="reporterEmailAddress" />
			</c:if>
		</aui:fieldset>

		<aui:button-row>
			<aui:button name="flagsSubmit" type="submit" />
		</aui:button-row>
	</aui:form>
</div>

<div class="hide" id="<portlet:namespace />confirmation">
	<p><strong><liferay-ui:message key="thank-you-for-your-report" /></strong></p>

	<p><%= LanguageUtil.format(pageContext, "although-we-cannot-disclose-our-final-decision,-we-do-review-every-report-and-appreciate-your-effort-to-make-sure-x-is-a-safe-environment-for-everyone", HtmlUtil.escape(company.getName())) %></p>
</div>

<div class="hide" id="<portlet:namespace />error">
	<p><strong><liferay-ui:message key="an-error-occurred-while-sending-the-report.-please-try-again-in-a-few-minutes" /></strong></p>
</div>

<aui:script use="liferay-util-window">
	function <portlet:namespace />flag() {
		var reasonNode = A.one('#<portlet:namespace />reason');
		var reason = (reasonNode && reasonNode.val()) || '';

		if (reason == 'other') {
			var otherReasonNode = A.one('#<portlet:namespace />otherReason');

			reason = (otherReasonNode && otherReasonNode.val()) || '<%= UnicodeLanguageUtil.get(pageContext, "no-reason-specified") %>';
		}

		var reporterEmailAddressNode = A.one('#<portlet:namespace />reporterEmailAddress');
		var reporterEmailAddress = (reporterEmailAddressNode && reporterEmailAddressNode.val()) || '';

		var flagsPopupNode = A.one('#<portlet:namespace />flagsPopup');
		var errorMessageNode = A.one('#<portlet:namespace />error');
		var confirmationMessageNode = A.one('#<portlet:namespace />confirmation');

		var errorMessage = (errorMessageNode && errorMessageNode.html()) || '';
		var confirmationMessage = (confirmationMessageNode && confirmationMessageNode.html()) || '';

		var setDialogContent = function(message) {
			var dialog = Liferay.Util.Window.getByChild(flagsPopupNode);

			dialog.setStdModContent('body', message);
		};

		var data = Liferay.Util.ns(
			'<portlet:namespace />',
			{
				className: '<%= HtmlUtil.escape(className) %>',
				classPK: '<%= classPK %>',
				contentTitle: '<%= HtmlUtil.escape(contentTitle) %>',
				contentURL: '<%= HtmlUtil.escape(contentURL) %>',
				reason: reason,
				reportedUserId: '<%= reportedUserId %>',
				reporterEmailAddress: reporterEmailAddress
			}
		);

		A.io.request(
			'<liferay-portlet:actionURL portletName="<%= PortletKeys.FLAGS %>" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/flags/edit_entry" /></liferay-portlet:actionURL>',
			{
				data: data,
				on: {
					failure: function() {
						setDialogContent(errorMessage);
					},
					success: function() {
						setDialogContent(confirmationMessage);
					}
				}
			}
		);
	}

	Liferay.Util.toggleSelectBox('<portlet:namespace />reason', 'other', '<portlet:namespace />otherReasonContainer');

	A.one('#<portlet:namespace />flagsSubmit').on(
		'click',
		function(event) {
			<portlet:namespace />flag();

			event.halt();
		}
	);
</aui:script>