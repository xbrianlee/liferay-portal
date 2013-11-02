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

<%@ include file="/html/common/themes/init.jsp" %>

<%-- Raw Text --%>

<%
StringBundler bodyTopSB = OutputTag.getData(request, WebKeys.PAGE_BODY_TOP);
%>

<c:if test="<%= bodyTopSB != null %>">

	<%
	bodyTopSB.writeTo(out);
	%>

</c:if>

<%@ include file="/html/common/themes/top_messages.jsp" %>

<liferay-util:include page="/html/common/themes/body_top-ext.jsp" />