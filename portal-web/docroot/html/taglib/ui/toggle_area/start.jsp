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

<%@ include file="/html/taglib/ui/toggle_area/init.jsp" %>

<div style="float: <%= align %>">
	<liferay-ui:toggle
		defaultShowContent="<%= defaultShowContent %>"
		hideImage="<%= hideImage %>"
		hideMessage="<%= hideMessage %>"
		id="<%= id %>"
		showImage="<%= showImage %>"
		showMessage="<%= showMessage %>"
		stateVar="<%= stateVar %>"
	/>
</div>

<%
String clickValue = SessionClicks.get(request, id, null);

if (clickValue == null) {
	if (defaultShowContent) {
		clickValue = "block";
	}
	else {
		clickValue = "none";
	}
}
else if (clickValue.equals(StringPool.BLANK)) {
	clickValue = "block";
}
%>

<div id="<%= id %>" style="display: <%= clickValue %>;">