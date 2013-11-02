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

<%@ include file="/html/portlet/trash/init.jsp" %>

<%
String className = ParamUtil.getString(request, "className");
long classPK = ParamUtil.getLong(request, "classPK");
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectFolder");

TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(className);

TrashRenderer trashRenderer = trashHandler.getTrashRenderer(classPK);

ContainerModel containerModel = (ContainerModel)request.getAttribute(WebKeys.TRASH_CONTAINER_MODEL);

long containerModelId = 0;

if (containerModel != null) {
	containerModelId = containerModel.getContainerModelId();
}

PortletURL containerURL = renderResponse.createRenderURL();

containerURL.setParameter("struts_action", "/trash/view_container_model");
containerURL.setParameter("redirect", currentURL);
containerURL.setParameter("className", className);
containerURL.setParameter("classPK", String.valueOf(classPK));
containerURL.setParameter("containerModelClassName", trashHandler.getContainerModelClassName());

TrashUtil.addContainerModelBreadcrumbEntries(request, trashHandler.getContainerModelClassName(), containerModelId, containerURL);
%>

<div class="alert alert-block">
	<liferay-ui:message arguments="<%= new Object[] {trashHandler.getContainerModelName(), trashRenderer.getTitle(locale)} %>" key="the-original-x-does-not-exist-anymore" />
</div>

<aui:form method="post" name="selectFolderFm">
	<liferay-ui:header
		showBackURL="<%= containerModel != null %>"
		title='<%= LanguageUtil.format(pageContext, "select-x", trashHandler.getContainerModelName(), true) %>'
	/>

	<liferay-ui:breadcrumb showGuestGroup="<%= false %>" showLayout="<%= false %>" showParentGroups="<%= false %>" />

	<aui:button-row>

		<%
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("classname", className);
		data.put("classpk", classPK);
		data.put("containermodelid", containerModelId);
		%>

		<aui:button cssClass="selector-button" data="<%= data %>" value='<%= LanguageUtil.format(pageContext, "choose-this-x", trashHandler.getContainerModelName(), true) %>' />
	</aui:button-row>

	<br />

	<%
	containerURL.setParameter("containerModelId", String.valueOf(containerModelId));
	%>

	<liferay-ui:search-container
		searchContainer="<%= new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, containerURL, null, null) %>"
		total="<%= trashHandler.getContainerModelsCount(classPK, containerModelId) %>"
	>
		<liferay-ui:search-container-results
			results="<%= trashHandler.getContainerModels(classPK, containerModelId, searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.ContainerModel"
			keyProperty="containerModelId"
			modelVar="curContainerModel"
		>

			<%
			containerURL.setParameter("containerModelId", String.valueOf(curContainerModel.getContainerModelId()));
			%>

			<liferay-ui:search-container-column-text
				name="<%= LanguageUtil.get(pageContext, trashHandler.getContainerModelName()) %>"
			>
				<c:choose>
					<c:when test="<%= curContainerModel.getContainerModelId() > 0 %>">

						<%
						TrashHandler containerTrashHandler = TrashHandlerRegistryUtil.getTrashHandler(((BaseModel)curContainerModel).getModelClassName());

						TrashRenderer containerTrashRenderer = containerTrashHandler.getTrashRenderer(curContainerModel.getContainerModelId());
						%>

						<liferay-ui:icon
							label="<%= true %>"
							message="<%= curContainerModel.getContainerModelName() %>"
							method="get" src="<%= containerTrashRenderer.getIconPath(renderRequest) %>"
							url="<%= containerURL.toString() %>"
						/>
					</c:when>
					<c:otherwise>
						<%= curContainerModel.getContainerModelName() %>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name='<%= LanguageUtil.format(pageContext, "num-of-x", trashHandler.getContainerModelName(), true) %>'
				value="<%= String.valueOf(trashHandler.getContainerModelsCount(classPK, curContainerModel.getContainerModelId())) %>"
			/>

			<liferay-ui:search-container-column-text>

				<%
				Map<String, Object> data = new HashMap<String, Object>();

				data.put("classname", className);
				data.put("classpk", classPK);
				data.put("containermodelid", curContainerModel.getContainerModelId());
				%>

				<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base">
	var Util = Liferay.Util;

	A.one('#<portlet:namespace />selectFolderFm').delegate(
		'click',
		function(event) {
			var result = Util.getAttributes(event.currentTarget, 'data-');

			Util.getOpener().Liferay.fire('<%= HtmlUtil.escapeJS(eventName) %>', result);

			Util.getWindow().hide();
		},
		'.selector-button'
	);
</aui:script>