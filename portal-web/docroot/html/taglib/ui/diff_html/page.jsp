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

<%@ include file="/html/taglib/init.jsp" %>

<%
String diffHtmlResults = (String)request.getAttribute("liferay-ui:diff-html:diffHtmlResults");
%>

<div class="taglib-diff-html">
	<%= diffHtmlResults %>
</div>

<aui:script>
	function updateOverlays() {
		var images = document.getElementsByTagName("img");

		for (var i = 0; i < images.length; i++) {
			var image = images[i];

			var imageChangeType = image.getAttribute('changeType');

			if ((imageChangeType == 'diff-removed-image') ||
				(imageChangeType == 'diff-added-image')) {

				var filter = null;
				var existingDivs = image.parentNode.getElementsByTagName('div');

				if ((existingDivs.length > 0) &&
					(existingDivs[0].className == imageChangeType)) {

					filter = existingDivs[0];
				}
				else {
					filter = document.createElement("div");

					filter.className= image.getAttribute("changeType");
				}

				filter.style.height = image.offsetHeight - 4 + "px";
				filter.style.width = image.offsetWidth - 4 + "px";

				if (image.y && image.x) {

					// Workaround for IE

					filter.style.top = image.y + "px";
					filter.style.left = image.x - 1 + "px";
				}

				if (existingDivs.length == 0) {
					image.parentNode.insertBefore(filter, image);
				}
			}
		}
	}
</aui:script>