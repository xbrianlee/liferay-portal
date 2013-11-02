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

<%@ include file="/html/portlet/site_map/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

LayoutLister layoutLister = new LayoutLister();

String rootNodeName = StringPool.BLANK;
LayoutView layoutView = layoutLister.getLayoutView(layout.getGroupId(), layout.isPrivateLayout(), rootNodeName, locale);

List layoutList = layoutView.getList();
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:fieldset>
		<aui:select label="root-layout" name="preferences--rootLayoutUuid--">
			<aui:option value="" />

			<%
			for (int i = 0; i < layoutList.size(); i++) {

				// id | parentId | ls | obj id | name | img | depth

				String layoutDesc = (String)layoutList.get(i);

				String[] nodeValues = StringUtil.split(layoutDesc, '|');

				long objId = GetterUtil.getLong(nodeValues[3]);
				String name = nodeValues[4];

				int depth = 0;

				if (i != 0) {
					depth = GetterUtil.getInteger(nodeValues[6]);
				}

				for (int j = 0; j < depth; j++) {
					name = "-&nbsp;" + name;
				}

				Layout curRootLayout = null;

				try {
					curRootLayout = LayoutLocalServiceUtil.getLayout(objId);
				}
				catch (Exception e) {
				}

				if (curRootLayout != null) {
			%>

				<aui:option label="<%= name %>" selected="<%= curRootLayout.getUuid().equals(rootLayoutUuid) %>" value="<%= curRootLayout.getUuid() %>" />

			<%
				}
			}
			%>

		</aui:select>

		<aui:select name="preferences--displayDepth--">
			<aui:option label="unlimited" value="0" />

			<%
			for (int i = 1; i <= 20; i++) {
			%>

				<aui:option label="<%= i %>" selected="<%= displayDepth == i %>" />

			<%
			}
			%>

		</aui:select>

		<aui:input name="preferences--includeRootInTree--" type="checkbox" value="<%= includeRootInTree %>" />

		<aui:input name="preferences--showCurrentPage--" type="checkbox" value="<%= showCurrentPage %>" />

		<aui:input name="preferences--useHtmlTitle--" type="checkbox" value="<%= useHtmlTitle %>" />

		<aui:input name="preferences--showHiddenPages--" type="checkbox" value="<%= showHiddenPages %>" />

		<div class="display-template">

			<%
			TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(LayoutSet.class.getName());
			%>

			<liferay-ui:ddm-template-selector
				classNameId="<%= PortalUtil.getClassNameId(templateHandler.getClassName()) %>"
				displayStyle="<%= displayStyle %>"
				displayStyleGroupId="<%= displayStyleGroupId %>"
				refreshURL="<%= currentURL %>"
				showEmptyOption="<%= true %>"
			/>
		</div>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>