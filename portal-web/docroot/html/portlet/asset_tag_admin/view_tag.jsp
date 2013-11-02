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

<%@ include file="/html/portlet/asset_tag_admin/init.jsp" %>

<%
long tagId = ParamUtil.getLong(request, "tagId");

AssetTag tag = AssetTagServiceUtil.getTag(tagId);

List<AssetTagProperty> tagProperties = AssetTagPropertyServiceUtil.getTagProperties(tag.getTagId());
%>

<div class="view-tag">
	<liferay-ui:header
		localizeTitle="<%= false %>"
		title="<%= tag.getName() %>"
	/>

	<c:if test="<%= tag != null %>">
		<c:if test="<%= AssetTagPermission.contains(permissionChecker, tag, ActionKeys.UPDATE) %>">
			<aui:button id="editTagButton" value="edit" />
		</c:if>

		<c:if test="<%= AssetTagPermission.contains(permissionChecker, tag, ActionKeys.DELETE) %>">
			<aui:button id="deleteTagButton" value="delete" />
		</c:if>

		<c:if test="<%= PropsValues.ASSET_TAG_PERMISSIONS_ENABLED && AssetTagPermission.contains(permissionChecker, tag, ActionKeys.PERMISSIONS) %>">
			<liferay-security:permissionsURL
				modelResource="<%= AssetTag.class.getName() %>"
				modelResourceDescription="<%= tag.getName() %>"
				resourcePrimKey="<%= String.valueOf(tag.getTagId()) %>"
				var="permissionsURL"
				windowState="<%= LiferayWindowState.POP_UP.toString() %>"
			/>

			<aui:button data-url="<%= permissionsURL %>" id="updateTagPermissions" value="permissions" />
		</c:if>
	</c:if>

	<div class="tag-field">
		<c:choose>
			<c:when test="<%= tag.getAssetCount() > 0 %>">
				<label><liferay-ui:message key="count" />:</label> <liferay-ui:message key="used-in-x-assets" arguments="<%= tag.getAssetCount() %>" />
			</c:when>
			<c:otherwise>
				<div class="alert alert-info">
					<liferay-ui:message key="this-tag-is-not-used" />
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<c:if test="<%= !tagProperties.isEmpty() %>">
		<div class="tag-field">
			<label><liferay-ui:message key="properties" />:</label>

			<%
			for (AssetTagProperty tagProperty : tagProperties) {
			%>

				<span class="property-key"><%= tagProperty.getKey() %></span>: <span class="property-value"><%= tagProperty.getValue() %></span><br />

			<%
			}
			%>

		</div>
	</c:if>
</div>