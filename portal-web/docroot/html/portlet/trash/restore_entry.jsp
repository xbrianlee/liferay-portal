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
String redirect = ParamUtil.getString(request, "redirect");

String restoreEntryAction = ParamUtil.getString(request, "restoreEntryAction", "/trash/edit_entry");

long trashEntryId = ParamUtil.getLong(request, "trashEntryId");

String className = ParamUtil.getString(request, "className");
long classPK = ParamUtil.getLong(request, "classPK");

TrashEntry entry = null;

if (trashEntryId > 0) {
	entry = TrashEntryLocalServiceUtil.getEntry(trashEntryId);
}
else if (Validator.isNotNull(className) && (classPK > 0)) {
	entry = TrashEntryLocalServiceUtil.fetchEntry(className, classPK);
}

if (entry != null) {
	className = entry.getClassName();
	classPK = entry.getClassPK();
}

String duplicateEntryId = ParamUtil.getString(request, "duplicateEntryId");
String oldName = ParamUtil.getString(request, "oldName");

String overrideMessage = ParamUtil.getString(request, "overrideMessage");
String renameMessage = ParamUtil.getString(request, "renameMessage");
%>

<div class="alert alert-block" id="<portlet:namespace />messageContainer">
	<liferay-ui:message arguments="<%= new String[] {oldName} %>" key="an-entry-with-name-x-already-exists" />
</div>

<portlet:actionURL var="restoreActionURL">
	<portlet:param name="struts_action" value="<%= restoreEntryAction %>" />
</portlet:actionURL>

<aui:form action="<%= restoreActionURL %>" enctype="multipart/form-data" method="post" name="restoreTrashEntryFm" onSubmit="event.preventDefault();">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="trashEntryId" type="hidden" value="<%= trashEntryId %>" />
	<aui:input name="duplicateEntryId" type="hidden" value="<%= duplicateEntryId %>" />
	<aui:input name="oldName" type="hidden" value="<%= oldName %>" />

	<aui:fieldset>
		<aui:input checked="<%= true %>" id="override" label="<%= overrideMessage %>" name="<%= Constants.CMD %>" type="radio" value="<%= Constants.OVERRIDE %>" />

		<aui:input id="rename" label="<%= renameMessage %>" name="<%= Constants.CMD %>" type="radio" value="<%= Constants.RENAME %>" />

		<aui:input cssClass="new-file-name" label="" name="newName" title="<%= renameMessage %>" value="<%= TrashUtil.getNewName(themeDisplay, className, classPK, oldName) %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button cssClass="btn-cancel" type="cancel" />

		<aui:button type="submit" />
	</aui:button-row>
</aui:form>