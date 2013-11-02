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

<%@ include file="/html/taglib/init.jsp" %>

<%
Object bean = request.getAttribute("liferay-ui:write:bean");
String property = (String)request.getAttribute("liferay-ui:write:property");
%>

<c:choose>
	<c:when test="<%= bean instanceof Organization %>">

		<%
		Organization organization = (Organization)bean;
		%>

		<c:choose>
			<c:when test='<%= property.equals("country") %>'>

				<%
				Address address = organization.getAddress();

				Country country = address.getCountry();

				String countryName = country.getName(locale);

				if (Validator.isNull(countryName)) {
					try {
						country = CountryServiceUtil.getCountry(organization.getCountryId());

						countryName = country.getName(locale);
					}
					catch (Exception e) {
					}
				}
				%>

				<%= countryName %>
			</c:when>
			<c:when test='<%= property.equals("region") %>'>

				<%
				Address address = organization.getAddress();

				Region region = address.getRegion();

				String regionName = region.getName();

				if (Validator.isNull(regionName)) {
					try {
						region = RegionServiceUtil.getRegion(organization.getRegionId());

						regionName = LanguageUtil.get(pageContext, region.getName());
					}
					catch (Exception e) {
					}
				}
				%>

				<%= regionName %>
			</c:when>
		</c:choose>
	</c:when>
	<c:when test="<%= bean instanceof User %>">

		<%
		User user2 = (User)bean;
		%>

		<c:choose>
			<c:when test='<%= property.equals("organizations") %>'>

				<%
				List<Organization> organizations = user2.getOrganizations();
				%>

				<%= HtmlUtil.escape(ListUtil.toString(organizations, Organization.NAME_ACCESSOR, StringPool.COMMA_AND_SPACE)) %>
			</c:when>
			<c:when test='<%= property.equals("user-groups") %>'>

				<%
				List<UserGroup> userGroups = user2.getUserGroups();
				%>

				<%= HtmlUtil.escape(ListUtil.toString(userGroups, UserGroup.NAME_ACCESSOR, StringPool.COMMA_AND_SPACE)) %>
			</c:when>
		</c:choose>
	</c:when>
</c:choose>