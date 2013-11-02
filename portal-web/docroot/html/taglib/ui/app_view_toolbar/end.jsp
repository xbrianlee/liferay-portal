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
boolean includeDisplayStyle = GetterUtil.getBoolean(request.getAttribute("liferay-ui:app_view_toolbar:includeDisplayStyle"));
%>

		</div>

		<c:if test="<%= includeDisplayStyle %>">
			<div class="display-style">
				<span class="toolbar" id="<portlet:namespace />displayStyleToolbar"></span>
			</div>
		</c:if>
	</div>
</div>