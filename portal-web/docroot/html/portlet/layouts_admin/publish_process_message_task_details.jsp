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

<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%
long backgroundTaskId = ParamUtil.getLong(request, "backgroundTaskId");

BackgroundTask backgroundTask = BackgroundTaskLocalServiceUtil.fetchBackgroundTask(backgroundTaskId);

JSONObject jsonObject = null;

try {
	jsonObject = JSONFactoryUtil.createJSONObject(backgroundTask.getStatusMessage());
}
catch (Exception e) {
}
%>

<c:choose>
	<c:when test="<%= jsonObject == null %>">
		<div class="alert <%= backgroundTask.getStatus() == BackgroundTaskConstants.STATUS_FAILED ? "alert-error" : StringPool.BLANK %> publish-error">
			<liferay-ui:message arguments="<%= backgroundTask.getStatusMessage() %>" key="unable-to-execute-process-x" />
		</div>
	</c:when>
	<c:otherwise>
		<div class="alert alert-error publish-error">
			<h4 class="upload-error-message">

				<%
				boolean exported = MapUtil.getBoolean(backgroundTask.getTaskContextMap(), "exported");
				boolean validated = MapUtil.getBoolean(backgroundTask.getTaskContextMap(), "validated");
				%>

				<c:choose>
					<c:when test="<%= exported && !validated %>">
						<liferay-ui:message key="the-publication-process-did-not-start-due-to-validation-errors" /></h4>
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="an-unexpected-error-occurred-with-the-publication-process.-please-check-your-portal-and-publishing-configuration" /></h4>
					</c:otherwise>
				</c:choose>

			<span class="error-message"><%= jsonObject.getString("message") %></span>

			<%
			JSONArray messageListItemsJSONArray = jsonObject.getJSONArray("messageListItems");
			%>

			<c:if test="<%= (messageListItemsJSONArray != null) && (messageListItemsJSONArray.length() > 0) %>">
				<ul class="error-list-items">

					<%
					for (int i = 0; i < messageListItemsJSONArray.length(); i++) {
						JSONObject messageListItemJSONArray = messageListItemsJSONArray.getJSONObject(i);

						String info = messageListItemJSONArray.getString("info");
					%>

						<li>
							<%= messageListItemJSONArray.getString("type") %>:

							<strong><%= messageListItemJSONArray.getString("name") %></strong>

							<c:if test="<%= Validator.isNotNull(info) %>">
								<span class="error-info">(<%= messageListItemJSONArray.getString("info") %>)</span>
							</c:if>
						</li>

					<%
					}
					%>

				</ul>
			</c:if>
		</div>

		<%
		JSONArray warningMessagesJSONArray = jsonObject.getJSONArray("warningMessages");
		%>

		<c:if test="<%= (warningMessagesJSONArray != null) && (warningMessagesJSONArray.length() > 0) %>">
			<div class="alert upload-error">
				<span class="error-message"><liferay-ui:message key='<%= ((messageListItemsJSONArray != null) && (messageListItemsJSONArray.length() > 0)) ? "consider-that-the-following-data-would-not-have-been-published-either" : "the-following-data-has-not-been-published" %>' /></span>

				<ul class="error-list-items">

					<%
					for (int i = 0; i < warningMessagesJSONArray.length(); i++) {
						JSONObject warningMessageJSONArray = warningMessagesJSONArray.getJSONObject(i);

						String info = warningMessageJSONArray.getString("info");
					%>

						<li>
							<%= warningMessageJSONArray.getString("type") %>:

							<strong><%= warningMessageJSONArray.getString("size") %></strong>

							<c:if test="<%= Validator.isNotNull(info) %>">
								<span class="error-info">(<%= warningMessageJSONArray.getString("info") %>)</span>
							</c:if>
						</li>

					<%
					}
					%>

				</ul>
			</div>
		</c:if>
	</c:otherwise>
</c:choose>