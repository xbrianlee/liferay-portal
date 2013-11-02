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

<%@ include file="/html/portlet/image_gallery_display/init.jsp" %>

<%
Folder folder = (Folder)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FOLDER);

long folderId = (folder == null) ? 0 : folder.getFolderId();

long repositoryId = scopeGroupId;

if (folder != null) {
	repositoryId = folder.getRepositoryId();
}

List fileEntries = DLAppServiceUtil.getFileEntries(repositoryId, folderId);

int defaultSpeed = 3000;
%>

<aui:form>
	<aui:fieldset column="<%= true %>">
		<aui:col width="<%= 50 %>">
			<aui:button onClick='<%= renderResponse.getNamespace() + "showPrevious();" %>' value="previous" />
			<aui:button onClick='<%= renderResponse.getNamespace() + "play();" %>' value="play" />
			<aui:button onClick='<%= renderResponse.getNamespace() + "pause();" %>' value="pause" />
			<aui:button onClick='<%= renderResponse.getNamespace() + "showNext();" %>' value="next" />
		</aui:col>
		<aui:col width="<%= 50 %>">
			<aui:select inlineLabel="left" name="speed" onChange='<%= renderResponse.getNamespace() + "pause();" + renderResponse.getNamespace() + "speed = this[this.selectedIndex].value * 1000;" + renderResponse.getNamespace() + "play();" %>'>

				<%
				for (int i = 1; i <= 10; i++) {
				%>

					<aui:option label="<%= i %>" selected="<%= (defaultSpeed / 1000) == i %>" />

				<%
				}
				%>

			</aui:select>

		</aui:col>
	</aui:fieldset>
</aui:form>

<br />

<table class="lfr-table">
<tr>
	<td>

		<%
		if (!fileEntries.isEmpty()) {
			FileEntry fileEntry = (FileEntry)fileEntries.get(0);

			String largeSrc = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK);
		%>

			<img border="0" name="<portlet:namespace />slideShow" src="<%= largeSrc %>" />

		<%
		}
		%>

	</td>
</tr>
</table>

<aui:script>
	var <portlet:namespace />imgArray = new Array();

	<%
	for (int i = 0; i < fileEntries.size(); i++) {
		FileEntry fileEntry = (FileEntry)fileEntries.get(i);

		String largeSrc = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK);
	%>

		<portlet:namespace />imgArray[<%= i %>] = "<%= largeSrc %>";

	<%
	}
	%>

	var <portlet:namespace />imgArrayPos = 0
	var <portlet:namespace />speed = <%= defaultSpeed %>;
	var <portlet:namespace />timeout = 0;

	function <portlet:namespace />pause() {
		clearInterval(<portlet:namespace />timeout);
		<portlet:namespace />timeout = 0;
	}

	function <portlet:namespace />play() {
		if (<portlet:namespace />timeout == 0) {
			<portlet:namespace />timeout = setInterval("<portlet:namespace />showNext()", <portlet:namespace />speed);
		}
	}

	function <portlet:namespace />showNext() {
		<portlet:namespace />imgArrayPos++;

		if (<portlet:namespace />imgArrayPos == <portlet:namespace />imgArray.length) {
			<portlet:namespace />imgArrayPos = 0;
		}

		document.images.<portlet:namespace />slideShow.src = <portlet:namespace />imgArray[<portlet:namespace />imgArrayPos];
	}

	function <portlet:namespace />showPrevious() {
		<portlet:namespace />imgArrayPos--;

		if (<portlet:namespace />imgArrayPos < 0) {
			<portlet:namespace />imgArrayPos = <portlet:namespace />imgArray.length - 1;
		}

		document.images.<portlet:namespace />slideShow.src = <portlet:namespace />imgArray[<portlet:namespace />imgArrayPos];
	}

	<portlet:namespace />play();
</aui:script>