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

<%@ include file="/html/taglib/ui/social_bookmark/init.jsp" %>

<liferay-util:html-bottom outputKey='<%= "taglib_ui_social_bookmark_link_" + type %>'>
	<style type="text/css">
		.taglib-social-bookmarks .taglib-social-bookmark-<%= type %> a.social-bookmark-link {
			background-image: url(/html/taglib/ui/social_bookmark/icons/<%= type %>.png);
		}
	</style>
</liferay-util:html-bottom>

<aui:a cssClass="social-bookmark-link" href="<%= postUrl %>" target="<%= target %>"><liferay-ui:message key="<%= messageKey %>" /></aui:a>