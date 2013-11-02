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

<%@ include file="/html/portlet/search/facets/init.jsp" %>

<%
if (termCollectors.isEmpty()) {
%>

	<aui:input name="<%= facet.getFieldName() %>" type="hidden" value="0" />

<%
	return;
}

int frequencyThreshold = dataJSONObject.getInt("frequencyThreshold");
int maxTerms = dataJSONObject.getInt("maxTerms");
boolean showAssetCount = dataJSONObject.getBoolean("showAssetCount", true);
%>

<div class="<%= cssClass %>" data-facetFieldName="<%= facet.getFieldId() %>" id="<%= randomNamespace %>facet">
	<aui:input name="<%= facet.getFieldId() %>" type="hidden" value="<%= fieldParam %>" />

	<ul class="nav nav-pills nav-stacked scopes">
		<li class="facet-value default <%= fieldParam.equals("0") ? "active" : StringPool.BLANK %>">
			<a data-value="0" href="javascript:;"><aui:icon image="sitemap" /> <liferay-ui:message key="any" /> <liferay-ui:message key="<%= facetConfiguration.getLabel() %>" /></a>
		</li>

		<%
		long groupId = GetterUtil.getInteger(fieldParam);

		for (int i = 0; i < termCollectors.size(); i++) {
			TermCollector termCollector = termCollectors.get(i);

			long curGroupId = GetterUtil.getInteger(termCollector.getTerm());

			Group group = GroupLocalServiceUtil.fetchGroup(curGroupId);

			if (group == null) {
				continue;
			}
		%>

			<c:if test="<%= groupId == curGroupId %>">
				<aui:script use="liferay-token-list">
					Liferay.Search.tokenList.add(
						{
							fieldValues: '<%= renderResponse.getNamespace() + facet.getFieldId() + "|0" %>',
							text: '<%= HtmlUtil.escapeJS(group.getDescriptiveName(locale)) %>'
						}
					);
				</aui:script>
			</c:if>

			<%
			if (((maxTerms > 0) && (i >= maxTerms)) || ((frequencyThreshold > 0) && (frequencyThreshold > termCollector.getFrequency()))) {
				break;
			}
			%>

			<li class="facet-value <%= groupId == curGroupId ? "active" : StringPool.BLANK %>">
				<a data-value="<%= curGroupId %>" href="javascript:;">
					<%= HtmlUtil.escape(group.getDescriptiveName(locale)) %>

					<c:if test="<%= showAssetCount %>">
						<span class="badge badge-info frequency"><%= termCollector.getFrequency() %></span>
					</c:if>
				</a>
			</li>

		<%
		}
		%>

	</ul>
</div>