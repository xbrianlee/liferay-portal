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

<%@ include file="/html/portlet/asset_category_admin/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "portlet_asset_category_admin_edit_vocabulary") + StringPool.UNDERLINE;

AssetVocabulary vocabulary = (AssetVocabulary)request.getAttribute(WebKeys.ASSET_VOCABULARY);

long vocabularyId = BeanParamUtil.getLong(vocabulary, request, "vocabularyId");
%>

<portlet:actionURL var="editVocabularyURL">
	<portlet:param name="struts_action" value="/asset_category_admin/edit_vocabulary" />
</portlet:actionURL>

<aui:form action="<%= editVocabularyURL %>" cssClass="update-vocabulary-form" method="get" name='<%= randomNamespace + "fm" %>'>
	<div class="hide lfr-message-response" id="vocabularyMessagesEdit"></div>

	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= vocabulary == null ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="vocabularyId" type="hidden" value="<%= vocabularyId %>" />

	<aui:model-context bean="<%= vocabulary %>" model="<%= AssetVocabulary.class %>" />

	<aui:fieldset>
		<div>
			<div class="add-vocabulary-layer asset-category-layer">
				<aui:input autoFocus="<%= true %>" cssClass="vocabulary-name" label="name" name="title" />

				<aui:input name="description" />

				<%@ include file="/html/portlet/asset_category_admin/edit_vocabulary_settings.jspf" %>

				<c:choose>
					<c:when test="<%= vocabulary == null %>">
						<aui:field-wrapper cssClass="vocabulary-permissions-actions" label="permissions">
							<liferay-ui:input-permissions
								modelName="<%= AssetVocabulary.class.getName() %>"
							/>
						</aui:field-wrapper>
					</c:when>
				</c:choose>

				<aui:button-row>
					<aui:button type="submit" />

					<c:if test="<%= vocabulary != null %>">
						<c:if test="<%= AssetVocabularyPermission.contains(permissionChecker, vocabulary, ActionKeys.DELETE) %>">
							<aui:button id="deleteVocabularyButton" value="delete" />
						</c:if>

						<c:if test="<%= AssetVocabularyPermission.contains(permissionChecker, vocabulary, ActionKeys.PERMISSIONS) %>">
							<liferay-security:permissionsURL
								modelResource="<%= AssetVocabulary.class.getName() %>"
								modelResourceDescription="<%= vocabulary.getTitle(locale) %>"
								resourcePrimKey="<%= String.valueOf(vocabulary.getVocabularyId()) %>"
								var="permissionsURL"
								windowState="<%= LiferayWindowState.POP_UP.toString() %>"
							/>

							<aui:button data-url="<%= permissionsURL %>" id="vocabulary-change-permissions" value="permissions" />
						</c:if>
					</c:if>

					<aui:button cssClass="close-panel" type="cancel" value="close" />
				</aui:button-row>
			</div>
		</div>
	</aui:fieldset>
</aui:form>