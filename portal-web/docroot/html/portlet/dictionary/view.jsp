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

<%@ include file="/html/portlet/dictionary/init.jsp" %>

<form name="<portlet:namespace />fm" onSubmit="window.open(document.<portlet:namespace />fm.<portlet:namespace />type[document.<portlet:namespace />fm.<portlet:namespace />type.selectedIndex].value + encodeURIComponent(document.<portlet:namespace />fm.<portlet:namespace />word.value)); return false;">

<input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" name="<portlet:namespace />word" size="30" type="text" />

<select name="<portlet:namespace />type">
	<option value="http://dictionary.reference.com/search?q="><liferay-ui:message key="dictionary" /></option>
	<option value="http://thesaurus.reference.com/search?q="><liferay-ui:message key="thesaurus" /></option>
</select>

<input type="submit" value="<liferay-ui:message key="find" />" />

</form>