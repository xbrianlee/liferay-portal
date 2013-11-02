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

<%@ include file="/html/portlet/wiki/init.jsp" %>

<%
WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_PAGE);

double sourceVersion = ParamUtil.getDouble(request, "sourceVersion");
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectVersion");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/wiki/select_version");
portletURL.setParameter("redirect", currentURL);
portletURL.setParameter("nodeId", String.valueOf(wikiPage.getNodeId()));
portletURL.setParameter("title", HtmlUtil.unescape(wikiPage.getTitle()));
portletURL.setParameter("sourceVersion", String.valueOf(sourceVersion));
%>

<aui:form action="<%= portletURL.toString() %>" method="post" name="selectVersionFm">
	<liferay-ui:search-container
		id="wikiPageVersionSearchContainer"
		iteratorURL="<%= portletURL %>"
		total="<%= WikiPageLocalServiceUtil.getPagesCount(wikiPage.getNodeId(), wikiPage.getTitle()) %>"
	>
		<liferay-ui:search-container-results
			results="<%= WikiPageLocalServiceUtil.getPages(wikiPage.getNodeId(), wikiPage.getTitle(), searchContainer.getStart(), searchContainer.getEnd(), new PageVersionComparator()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portlet.wiki.model.WikiPage"
			modelVar="curWikiPage"
		>
			<liferay-ui:search-container-column-text
				name="version"
				value="<%= String.valueOf(curWikiPage.getVersion()) %>"
			/>

			<liferay-ui:search-container-column-date
				name="date"
				value="<%= curWikiPage.getModifiedDate() %>"
			/>

			<liferay-ui:search-container-column-text
				name=""
			>
				<c:if test="<%= sourceVersion != curWikiPage.getVersion() %>">

					<%
					double curSourceVersion = sourceVersion;
					double curTargetVersion = curWikiPage.getVersion();

					if (curTargetVersion < curSourceVersion) {
						double tempVersion = curTargetVersion;

						curTargetVersion = curSourceVersion;
						curSourceVersion = tempVersion;
					}

					Map<String, Object> data = new HashMap<String, Object>();

					data.put("sourceversion", curSourceVersion);
					data.put("targetversion", curTargetVersion);
					%>

					<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
				</c:if>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base">
	var Util = Liferay.Util;

	A.one('#<portlet:namespace />selectVersionFm').delegate(
		'click',
		function(event) {
			var result = Util.getAttributes(event.currentTarget, 'data-');

			Util.getOpener().Liferay.fire('<%= HtmlUtil.escapeJS(eventName) %>', result);

			Util.getWindow().hide();
		},
		'.selector-button'
	);
</aui:script>