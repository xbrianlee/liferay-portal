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
boolean supportedAudio = false;
boolean supportedVideo = false;

String mp3PreviewURL = ParamUtil.getString(request, "mp3PreviewURL");
String mp4PreviewURL = ParamUtil.getString(request, "mp4PreviewURL");
String oggPreviewURL = ParamUtil.getString(request, "oggPreviewURL");
String ogvPreviewURL = ParamUtil.getString(request, "ogvPreviewURL");
String videoThumbnailURL = ParamUtil.getString(request, "thumbnailURL");

List<String> previewFileURLs = new ArrayList<String>(4);

if (Validator.isNotNull(mp3PreviewURL)) {
	previewFileURLs.add(mp3PreviewURL);

	supportedAudio = true;
}

if (Validator.isNotNull(mp4PreviewURL)) {
	previewFileURLs.add(mp4PreviewURL);

	supportedVideo = true;
}

if (Validator.isNotNull(oggPreviewURL)) {
	previewFileURLs.add(oggPreviewURL);

	supportedAudio = true;
}

if (Validator.isNotNull(ogvPreviewURL)) {
	previewFileURLs.add(ogvPreviewURL);

	supportedVideo = true;
}

request.setAttribute("view_file_entry.jsp-supportedAudio", String.valueOf(supportedAudio));
request.setAttribute("view_file_entry.jsp-supportedVideo", String.valueOf(supportedVideo));

request.setAttribute("view_file_entry.jsp-previewFileURLs", previewFileURLs.toArray(new String[0]));
request.setAttribute("view_file_entry.jsp-videoThumbnailURL", videoThumbnailURL);
%>

<c:choose>
	<c:when test="<%= supportedAudio %>">
		<div class="lfr-preview-audio" id="<portlet:namespace />previewFile">
			<div class="lfr-preview-audio-content" id="<portlet:namespace />previewFileContent"></div>
		</div>
	</c:when>
	<c:when test="<%= supportedVideo %>">
		<div class="lfr-preview-file lfr-preview-video" id="<portlet:namespace />previewFile">
			<div class="lfr-preview-file-content lfr-preview-video-content" id="<portlet:namespace />previewFileContent"></div>
		</div>
	</c:when>
</c:choose>

<liferay-util:include page="/html/portlet/document_library/player.jsp" />