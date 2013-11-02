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

<%@ include file="/html/portlet/sites_admin/init.jsp" %>

<%
Group group = (Group)request.getAttribute("site.group");

LayoutSet privateLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(group.getGroupId(), true);
LayoutSet publicLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(group.getGroupId(), false);

LayoutSetPrototype privateLayoutSetPrototype = null;

boolean privateLayoutSetPrototypeLinkEnabled = false;

LayoutSetPrototype publicLayoutSetPrototype = null;

boolean publicLayoutSetPrototypeLinkEnabled = false;

if (Validator.isNotNull(privateLayoutSet.getLayoutSetPrototypeUuid())) {
	privateLayoutSetPrototype = LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototypeByUuidAndCompanyId(privateLayoutSet.getLayoutSetPrototypeUuid(), company.getCompanyId());

	privateLayoutSetPrototypeLinkEnabled = privateLayoutSet.isLayoutSetPrototypeLinkEnabled();
}

if (Validator.isNotNull(publicLayoutSet.getLayoutSetPrototypeUuid())) {
	publicLayoutSetPrototype = LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototypeByUuidAndCompanyId(publicLayoutSet.getLayoutSetPrototypeUuid(), company.getCompanyId());

	publicLayoutSetPrototypeLinkEnabled = publicLayoutSet.isLayoutSetPrototypeLinkEnabled();
}
%>

<liferay-ui:error-marker key="errorSection" value="siteTempate" />

<h3><liferay-ui:message key="site-template" /></h3>

<c:if test="<%= (publicLayoutSetPrototype == null) && (privateLayoutSetPrototype == null) %>">
	<div class="alert alert-info">
		<liferay-ui:message key="this-site-is-not-related-to-a-site-template" />
	</div>
</c:if>

<aui:fieldset>
	<c:if test="<%= publicLayoutSetPrototype != null %>">
		<aui:fieldset label="public-site-template">
			<c:choose>
				<c:when test="<%= publicLayoutSetPrototypeLinkEnabled %>">

					<%
					boolean layoutsUpdateable = GetterUtil.getBoolean(publicLayoutSetPrototype.getSettingsProperty("layoutsUpdateable"), true);
					%>

					<liferay-ui:message arguments="<%= new Object[] {HtmlUtil.escape(publicLayoutSetPrototype.getName(locale))} %>" key="these-pages-are-linked-to-site-template-x" />

					<aui:field-wrapper label="site-template-settings">
						<aui:input disabled="<%= true %>" name="active" type="checkbox" value="<%= publicLayoutSetPrototype.isActive() %>" />
						<aui:input disabled="<%= true %>" name="site-template-allows-modifications" type="checkbox" value="<%= layoutsUpdateable %>" />
					</aui:field-wrapper>
				</c:when>
				<c:otherwise>
					<liferay-ui:message arguments="<%= new Object[] {publicLayoutSetPrototype.getName(locale)} %>" key="this-site-was-cloned-from-site-template-x" />
				</c:otherwise>
			</c:choose>
		</aui:fieldset>
	</c:if>

	<c:if test="<%= privateLayoutSetPrototype != null %>">
		<aui:fieldset label="private-site-template">
			<c:choose>
				<c:when test="<%= privateLayoutSetPrototypeLinkEnabled %>">

					<%
					boolean layoutsUpdateable = GetterUtil.getBoolean(privateLayoutSetPrototype.getSettingsProperty("layoutsUpdateable"), true);
					%>

					<liferay-ui:message arguments="<%= new Object[] {HtmlUtil.escape(privateLayoutSetPrototype.getName(locale))} %>" key="these-pages-are-linked-to-site-template-x" />

					<aui:field-wrapper label="site-template-settings">
						<aui:input disabled="<%= true %>" name="active" type="checkbox" value="<%= privateLayoutSetPrototype.isActive() %>" />
						<aui:input disabled="<%= true %>" name="site-template-allows-modifications" type="checkbox" value="<%= layoutsUpdateable %>" />
					</aui:field-wrapper>
				</c:when>
				<c:otherwise>
					<liferay-ui:message arguments="<%= new Object[] {privateLayoutSetPrototype.getName(locale)} %>" key="this-site-was-cloned-from-site-template-x" />
				</c:otherwise>
			</c:choose>
		</aui:fieldset>
	</c:if>
</aui:fieldset>