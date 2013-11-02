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

<%@ include file="/html/taglib/ui/asset_tags_error/init.jsp" %>

<liferay-ui:error exception="<%= AssetTagException.class %>">

	<%
	AssetTagException ate = (AssetTagException)errorException;
	%>

	<c:choose>
		<c:when test="<%= ate.getType() == AssetTagException.AT_LEAST_ONE_TAG %>">
			<liferay-ui:message key="please-enter-at-least-one-tag" />
		</c:when>
		<c:when test="<%= ate.getType() == AssetTagException.INVALID_CHARACTER %>">
			<liferay-ui:message key="one-or-more-tags-contains-invalid-characters" />
		</c:when>
	</c:choose>
</liferay-ui:error>