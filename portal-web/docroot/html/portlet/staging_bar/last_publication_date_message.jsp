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

<%@ include file="/html/portlet/staging_bar/init.jsp" %>

<%
UnicodeProperties typeSettingsProperties = (UnicodeProperties)request.getAttribute("view.jsp-typeSettingsProperties");

long lastImportDate = GetterUtil.getLong(typeSettingsProperties.getProperty("last-import-date"));

String lastImportLayoutSetBranchName = null;

long lastImportLayoutSetBranchId = GetterUtil.getLong(typeSettingsProperties.getProperty("last-import-layout-set-branch-id"));

if (lastImportLayoutSetBranchId > 0) {

	try {
		LayoutSetBranch lastImportLayoutSetBranch = LayoutSetBranchLocalServiceUtil.getLayoutSetBranch(lastImportLayoutSetBranchId);

		lastImportLayoutSetBranchName = lastImportLayoutSetBranch.getName();
	}
	catch (Exception e) {
	}
}

if (Validator.isNull(lastImportLayoutSetBranchName)) {
	lastImportLayoutSetBranchName = typeSettingsProperties.getProperty("last-import-layout-set-branch-name");
}

if (Validator.isNull(lastImportLayoutSetBranchName)) {
	lastImportLayoutSetBranchName = LanguageUtil.get(pageContext, "staging");
}

String lastImportLayoutBranchName = null;

List<LayoutRevision> layoutRevisions = new ArrayList<LayoutRevision>();

long lastImportLayoutRevisionId = GetterUtil.getLong(typeSettingsProperties.getProperty("last-import-layout-revision-id"));

if (lastImportLayoutRevisionId > 0) {
	try {
		LayoutRevision lastImportLayoutRevision = LayoutRevisionLocalServiceUtil.getLayoutRevision(lastImportLayoutRevisionId);

		lastImportLayoutBranchName = lastImportLayoutRevision.getLayoutBranch().getName();

		layoutRevisions = LayoutRevisionLocalServiceUtil.getChildLayoutRevisions(lastImportLayoutRevision.getLayoutSetBranchId(), LayoutRevisionConstants.DEFAULT_PARENT_LAYOUT_REVISION_ID, lastImportLayoutRevision.getPlid());
	}
	catch (Exception e) {
	}
}

if (Validator.isNull(lastImportLayoutBranchName)) {
	lastImportLayoutBranchName = typeSettingsProperties.getProperty("last-import-layout-branch-name");
}

String publisherName = null;

String lastImportUserUuid = GetterUtil.getString(typeSettingsProperties.getProperty("last-import-user-uuid"));

if (Validator.isNotNull(lastImportUserUuid)) {
	try {
		User publisher = UserLocalServiceUtil.getUserByUuidAndCompanyId(lastImportUserUuid, company.getCompanyId());

		publisherName = publisher.getFullName();
	}
	catch (Exception e) {
	}
}

if (Validator.isNull(publisherName)) {
	publisherName = typeSettingsProperties.getProperty("last-import-user-name");
}
%>

<c:choose>
	<c:when test="<%= lastImportDate > 0 %>">
		<c:if test="<%= Validator.isNotNull(lastImportLayoutSetBranchName) && Validator.isNotNull(publisherName) %>">
			<span class="last-publication-branch">
				<liferay-ui:message arguments='<%= new String[] {"<strong>" + HtmlUtil.escape(layout.getName(locale)) + "</strong>", "<em>" + LanguageUtil.get(pageContext, HtmlUtil.escape(lastImportLayoutSetBranchName)) + "</em>"} %>' key='<%= (group.isStagingGroup() || group.isStagedRemotely()) ? "page-x-was-last-published-to-live" : "page-x-was-last-published-from-x" %>' />

				<c:if test="<%= (Validator.isNotNull(lastImportLayoutBranchName) && (layoutRevisions.size() > 1)) || Validator.isNotNull(lastImportLayoutRevisionId) %>">
					<span class="last-publication-variation-details">(
						<c:if test="<%= Validator.isNotNull(lastImportLayoutBranchName) && (layoutRevisions.size() > 1) %>">
							<span class="variation-name">
								<liferay-ui:message key="variation" />: <strong><liferay-ui:message key="<%= HtmlUtil.escape(lastImportLayoutBranchName) %>" /></strong>
							</span>
						</c:if>

						<c:if test="<%= Validator.isNotNull(lastImportLayoutRevisionId) %>">
							<span class="layout-version">
								<liferay-ui:message key="version" />: <strong><%= lastImportLayoutRevisionId %></strong>
							</span>
						</c:if>
					)</span>
				</c:if>
			</span>

			<span class="last-publication-user">
				<liferay-ui:message arguments="<%= new String[] {LanguageUtil.getTimeDescription(pageContext, (System.currentTimeMillis() - lastImportDate), true), publisherName} %>" key="x-ago-by-x" />
			</span>
		</c:if>
	</c:when>
	<c:otherwise>
		<span class="staging-live-group-name">
			<liferay-ui:message arguments="<%= HtmlUtil.escape(liveGroup.getDescriptiveName(locale)) %>" key="x-is-staged" />
		</span>

		<span class="staging-live-help">
			<liferay-ui:message arguments="<%= HtmlUtil.escape(liveGroup.getDescriptiveName(locale)) %>" key='<%= (group.isStagingGroup() || group.isStagedRemotely()) ? "staging-staging-help-x" : "staging-live-help-x" %>' />
		</span>
	</c:otherwise>
</c:choose>