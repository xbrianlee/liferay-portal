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

<%@ include file="/html/portlet/portal_settings/init.jsp" %>

<%
long ldapServerId = ParamUtil.getLong(request, "ldapServerId", 0);

String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

String baseProviderURL = ParamUtil.getString(request, "baseProviderURL");
String baseDN = ParamUtil.getString(request, "baseDN");
String principal = ParamUtil.getString(request, "principal");

String credentials = request.getParameter("credentials");

if (credentials.equals(Portal.TEMP_OBFUSCATION_VALUE)) {
	credentials = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_SECURITY_CREDENTIALS + postfix);
}

LdapContext ldapContext = PortalLDAPUtil.getContext(themeDisplay.getCompanyId(), baseProviderURL, principal, credentials);

if (ldapContext == null) {
%>

	<liferay-ui:message key="liferay-has-failed-to-connect-to-the-ldap-server" />

<%
	return;
}

if (Validator.isNull(ParamUtil.getString(request, "groupMappingGroupName")) ||
	Validator.isNull(ParamUtil.getString(request, "groupMappingUser"))) {
%>

	<liferay-ui:message key="please-map-each-of-the-group-properties-group-name-and-user-to-an-ldap-attribute" />

<%
	return;
}

String groupFilter = ParamUtil.getString(request, "importGroupSearchFilter");

String groupMappingsParam =
	"groupName=" + ParamUtil.getString(request, "groupMappingGroupName") +
	"\ndescription=" + ParamUtil.getString(request, "groupMappingDescription") +
	"\nuser=" + ParamUtil.getString(request, "groupMappingUser");

Properties groupMappings = PropertiesUtil.load(groupMappingsParam);

String[] attributeIds = StringUtil.split(StringUtil.merge(groupMappings.values()));

List<SearchResult> searchResults = new ArrayList<SearchResult>();

PortalLDAPUtil.getGroups(themeDisplay.getCompanyId(), ldapContext, new byte[0], 20, baseDN, groupFilter, attributeIds, searchResults);
%>

<liferay-ui:message key="test-ldap-groups" />

<br /><br />

<liferay-ui:message key="a-subset-of-groups-has-been-displayed-for-you-to-review" />

<br /><br />

<table class="lfr-table" width="100%">

<%
boolean showMissingAttributeMessage = false;

int counter = 0;

for (SearchResult searchResult : searchResults) {
	Attributes attributes = searchResult.getAttributes();

	String name = StringUtil.toLowerCase(LDAPUtil.getAttributeString(attributes, groupMappings.getProperty("groupName")));
	String description = LDAPUtil.getAttributeString(attributes, groupMappings.getProperty("description"));
	Attribute attribute = attributes.get(groupMappings.getProperty("user"));

	if (Validator.isNull(name)) {
		showMissingAttributeMessage = true;
	}

	if (attribute != null) {
		StringBundler sb = new StringBundler(7);

		sb.append("(&");
		sb.append(groupFilter);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(groupMappings.getProperty("groupName"));
		sb.append("=");
		sb.append(name);
		sb.append("))");

		String filter = sb.toString();

		attribute = PortalLDAPUtil.getMultivaluedAttribute(themeDisplay.getCompanyId(), ldapContext, baseDN, filter, attribute);
	}

	if (counter == 0) {
%>

		<tr>
			<th>
				#
			</th>
			<th>
				<liferay-ui:message key="name" />
			</th>
			<th>
				<liferay-ui:message key="description" />
			</th>
			<th>
				<liferay-ui:message key="members" />
			</th>
		</tr>

<%
	}

	counter++;
%>

	<tr>
		<td>
			<%= counter %>
		</td>
		<td>
			<%= name %>
		</td>
		<td>
			<%= description %>
		</td>
		<td>
			<%= (attribute == null) ? "0" : String.valueOf(attribute.size()) %>
		</td>
	</tr>

<%
}

if (counter == 0) {
%>

	<tr>
		<td colspan="4">
			<liferay-ui:message key="no-groups-were-found" />
		</td>
	</tr>

<%
}
%>

</table>

<%
if (showMissingAttributeMessage) {
%>

	<div class="alert alert-info">
		<liferay-ui:message key="the-above-results-include-groups-which-are-missing-the-required-attributes-(group-name-and-user).-these-groups-will-not-be-imported-until-these-attributes-are-filled-in" />
	</div>

<%
}

if (ldapContext != null) {
	ldapContext.close();
}
%>