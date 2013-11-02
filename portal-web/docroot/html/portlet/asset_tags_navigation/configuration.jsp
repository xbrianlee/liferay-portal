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

<%@ include file="/html/portlet/asset_tags_navigation/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:fieldset>
		<ul class="lfr-tree unstyled">
			<li class="tree-item">
				<aui:input label="show-unused-tags" name="preferences--showZeroAssetCount--" type="checkbox" value="<%= showZeroAssetCount %>" />
			</li>

			<li class="tree-item">
				<aui:input name="preferences--showAssetCount--" type="checkbox" value="<%= showAssetCount %>" />

				<ul class="lfr-tree hide unstyled" id="<portlet:namespace />assetCountOptions">
					<li class="tree-item">
						<aui:select helpMessage="asset-type-asset-count-help" label="asset-type" name="preferences--classNameId--">
							<aui:option label="any" value="<%= classNameId == 0 %>" />

							<%
							List<AssetRendererFactory> assetRendererFactories = AssetRendererFactoryRegistryUtil.getAssetRendererFactories(company.getCompanyId());

							for (AssetRendererFactory assetRendererFactory : assetRendererFactories) {
							%>

								<aui:option label="<%= ResourceActionsUtil.getModelResource(locale, assetRendererFactory.getClassName()) %>" selected="<%= classNameId == assetRendererFactory.getClassNameId() %>" value="<%= assetRendererFactory.getClassNameId() %>" />

							<%
							}
							%>

						</aui:select>
					</li>
				</ul>
			</li>

			<li class="tree-item">
				<ul class="lfr-tree unstyled" id="<portlet:namespace />displayTemplateSettings">
					<div class="display-template">

						<%
						TemplateHandler templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(AssetTag.class.getName());

						List<String> displayStyles = new ArrayList<String>();

						displayStyles.add("number");
						displayStyles.add("cloud");
						%>

						<liferay-ui:ddm-template-selector
							classNameId="<%= PortalUtil.getClassNameId(templateHandler.getClassName()) %>"
							displayStyle="<%= displayStyle %>"
							displayStyleGroupId="<%= displayStyleGroupId %>"
							displayStyles="<%= displayStyles %>"
							refreshURL="<%= currentURL %>"
						/>
					</div>
				</ul>
			</li>

			<li class="tree-item">
				<aui:input label="max-num-of-tags" name="preferences--maxAssetTags--" type="text" value="<%= maxAssetTags %>" />
			</li>
		</ul>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base">
	var showAssetCount = A.one('#<portlet:namespace />showAssetCountCheckbox');

	function showHiddenFields() {
		var assetCountOptions = A.one('#<portlet:namespace />assetCountOptions');

		if (showAssetCount && assetCountOptions) {
			if (showAssetCount.get('checked')) {
				assetCountOptions.show();
			}
			else {
				assetCountOptions.hide();
			}
		}
	}

	showHiddenFields();

	showAssetCount.on('change', showHiddenFields);
</aui:script>