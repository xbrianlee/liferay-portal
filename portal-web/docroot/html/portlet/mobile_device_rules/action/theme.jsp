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

<%@ include file="/html/portlet/mobile_device_rules/action/init.jsp" %>

<%
String selThemeId = GetterUtil.getString(typeSettingsProperties.get("themeId"));
String selColorSchemeId = GetterUtil.getString(typeSettingsProperties.get("colorSchemeId"));

if (Validator.isNull(selThemeId)) {
	String className = BeanParamUtil.getString(action, request, "className");
	long classPK = BeanParamUtil.getLong(action, request, "classPK");

	if (className.equals(Layout.class.getName())) {
		Layout selLayout = LayoutLocalServiceUtil.getLayout(classPK);

		groupId = selLayout.getGroupId();

		selThemeId = selLayout.getThemeId();
		selColorSchemeId = selLayout.getColorSchemeId();
	}
	else if (className.equals(LayoutSet.class.getName())) {
		LayoutSet selLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(classPK);

		groupId = selLayoutSet.getGroupId();

		selThemeId = selLayoutSet.getThemeId();
		selColorSchemeId = selLayoutSet.getColorSchemeId();
	}
}

Theme selTheme = ThemeLocalServiceUtil.getTheme(company.getCompanyId(), selThemeId, false);
ColorScheme selColorScheme = ThemeLocalServiceUtil.getColorScheme(company.getCompanyId(), selThemeId, selColorSchemeId, false);
%>

<div class="lfr-theme-list">
	<div class="float-container lfr-current-theme">
		<h3><liferay-ui:message key="current-theme" /></h3>

		<div class="lfr-current-theme-body">
			<img alt="<%= selTheme.getName() %>" class="img-polaroid theme-screenshot" onclick="document.getElementById('<portlet:namespace />SelTheme').checked = true;" src="<%= selTheme.getStaticResourcePath() %><%= selTheme.getImagesPath() %>/thumbnail.png" title="<%= selTheme.getName() %>" />

			<div class="theme-details">
				<aui:input checked="<%= true %>" cssClass="selected-theme theme-title" id="SelTheme" label="<%= selTheme.getName() %>" name="themeId" type="radio" value="<%= selTheme.getThemeId() %>" />

				<dl class="theme-fields">

					<%
					PluginPackage selPluginPackage = selTheme.getPluginPackage();
					%>

					<c:if test="<%= (selPluginPackage != null) && Validator.isNotNull(selPluginPackage.getShortDescription()) %>">
						<dt>
							<liferay-ui:message key="description" />
						</dt>
						<dd>
							<%= selPluginPackage.getShortDescription() %>
						</dd>
					</c:if>

					<c:if test="<%= (selPluginPackage != null) && Validator.isNotNull(selPluginPackage.getAuthor()) %>">
						<dt>
							<liferay-ui:message key="author" />
						</dt>
						<dd>
							<a href="<%= selPluginPackage.getPageURL() %>"><%= selPluginPackage.getAuthor() %></a>
						</dd>
					</c:if>
				</dl>
			</div>
		</div>

		<%
		List<ColorScheme> colorSchemes = selTheme.getColorSchemes();
		%>

		<c:if test="<%= !colorSchemes.isEmpty() %>">
			<liferay-ui:panel-container extended="<%= true %>" id="mobileDeviceRulesColorSchemesPanelContainer" persistState="<%= true %>">
				<c:if test="<%= !colorSchemes.isEmpty() %>">
					<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="mobileDeviceRulesColorSchemesPanel" persistState="<%= true %>" title='<%= LanguageUtil.format(pageContext, "color-schemes-x", colorSchemes.size()) %>'>
						<aui:fieldset cssClass="color-schemes">
							<div class="lfr-theme-list unstyled">

								<%
								for (int i = 0; i < colorSchemes.size(); i++) {
									ColorScheme curColorScheme = colorSchemes.get(i);

									String cssClass = StringPool.BLANK;

									if (selColorScheme.getColorSchemeId().equals(curColorScheme.getColorSchemeId())) {
										cssClass = "selected-color-scheme";
									}
								%>

								<div class="<%= cssClass %> theme-entry">
									<img alt="" class="modify-link theme-thumbnail" onclick="document.getElementById('<portlet:namespace />ColorSchemeId<%= i %>').checked = true;" src="<%= selTheme.getStaticResourcePath() %><%= curColorScheme.getColorSchemeThumbnailPath() %>/thumbnail.png" title="<%= curColorScheme.getName() %>" />

									<aui:input checked="<%= selColorScheme.getColorSchemeId().equals(curColorScheme.getColorSchemeId()) %>" cssClass="theme-title" id='<%= "ColorSchemeId" + i %>' label="<%= curColorScheme.getName() %>" name="colorSchemeId" type="radio" value="<%= curColorScheme.getColorSchemeId() %>" />
								</div>

								<%
								}
								%>

							</div>
						</aui:fieldset>
					</liferay-ui:panel>
				</c:if>
			</liferay-ui:panel-container>
		</c:if>
	</div>

	<div class="float-container lfr-available-themes">
		<h3>
			<span class="header-title">

				<%
				List<Theme> themes = ThemeLocalServiceUtil.getThemes(company.getCompanyId(), groupId, user.getUserId(), false);
				%>

				<%= LanguageUtil.format(pageContext, "available-themes-x", (themes.size() - 1)) %>
			</span>
		</h3>

		<c:if test="<%= themes.size() > 1 %>">
			<ul class="lfr-theme-list unstyled">

				<%
				for (int i = 0; i < themes.size(); i++) {
					Theme curTheme = themes.get(i);

					if (!selTheme.getThemeId().equals(curTheme.getThemeId())) {
				%>

						<li>
							<div class="theme-entry">
								<img alt="" class="modify-link theme-thumbnail" onclick="document.getElementById('<portlet:namespace />ThemeId<%= i %>').checked = true;" src="<%= curTheme.getStaticResourcePath() %><%= curTheme.getImagesPath() %>/thumbnail.png" title="<%= curTheme.getName() %>" />

								<aui:input cssClass="theme-title" id='<%= "ThemeId" + i %>' label="<%= curTheme.getName() %>" name="themeId" type="radio" value="<%= curTheme.getThemeId() %>" />
							</div>
						</li>

				<%
					}
				}
				%>

			</ul>
		</c:if>
	</div>
</div>