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

<%@ include file="/html/taglib/ui/search_toggle/init.jsp" %>

<div class="taglib-search-toggle">
	<div class="form-search">
		<div class="input-append" id="<%= id %>simple">
			<input class="search-query span9" id="<%= id + displayTerms.KEYWORDS %>" name="<portlet:namespace /><%= displayTerms.KEYWORDS %>" placeholder="<liferay-ui:message key="keywords" />" type="text" value="<%= displayTerms.getKeywords() %>" />

			<button class="btn" type="submit">
				<%= LanguageUtil.get(pageContext, buttonLabel, "search") %>
			</button>
		</div>
		<button class="btn-link" id="<%= id %>toggleAdvanced" type="button">
			<i class="icon-cog"></i>
		</button>
	</div>
	<div class="popover taglib-search-toggle-advanced" id="<%= id %>advanced">
		<input id="<%= id + displayTerms.ADVANCED_SEARCH %>" name="<portlet:namespace /><%= displayTerms.ADVANCED_SEARCH %>" type="hidden" value="false" />

		<div id="<%= id %>advancedContent">
			<div id="<%= id %>advancedBodyNode">
				<liferay-util:buffer var="andOperator">
					<aui:select cssClass="inline-control" inlineField="<%= true %>" label="" name="<%= displayTerms.AND_OPERATOR %>">
						<aui:option label="all" selected="<%= displayTerms.isAndOperator() %>" value="1" />
						<aui:option label="any" selected="<%= !displayTerms.isAndOperator() %>" value="0" />
					</aui:select>
				</liferay-util:buffer>

				<liferay-ui:message arguments="<%= andOperator %>" key="match-x-of-the-following-fields" />