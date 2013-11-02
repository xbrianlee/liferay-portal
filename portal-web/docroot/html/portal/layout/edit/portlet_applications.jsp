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

<%@ include file="/html/portal/layout/edit/init.jsp" %>

<%
String copyLayoutIdPrefix = ParamUtil.getString(request, "copyLayoutIdPrefix");
%>

<aui:select id='<%= copyLayoutIdPrefix + "copyLayoutId" %>' label="copy-from-page" name="copyLayoutId" showEmptyOption="<%= true %>">

	<%
	List layoutList = (List)request.getAttribute(WebKeys.LAYOUT_LISTER_LIST);

	for (int i = 0; i < layoutList.size(); i++) {

		// id | parentId | ls | obj id | name | img | depth

		String layoutDesc = (String)layoutList.get(i);

		String[] nodeValues = StringUtil.split(layoutDesc, '|');

		long objId = GetterUtil.getLong(nodeValues[3]);
		String name = nodeValues[4];

		int depth = 0;

		if (i != 0) {
			depth = GetterUtil.getInteger(nodeValues[6]);
		}

		name = HtmlUtil.escape(name);

		for (int j = 0; j < depth; j++) {
			name = "-&nbsp;" + name;
		}

		Layout copiableLayout = null;

		try {
			copiableLayout = LayoutLocalServiceUtil.getLayout(objId);
		}
		catch (Exception e) {
		}

		if (copiableLayout != null) {
	%>

			<aui:option disabled="<%= Validator.isNotNull(selLayout) && selLayout.getPlid() == copiableLayout.getPlid() %>" label="<%= name %>" value="<%= copiableLayout.getLayoutId() %>" />

	<%
		}
	}
	%>

</aui:select>