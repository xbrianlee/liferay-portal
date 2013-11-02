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

<%@ include file="/html/portlet/shopping/init.jsp" %>

<%
String[] fieldsQuantities = StringUtil.split(ParamUtil.getString(request, "fieldsQuantities"));

List<String> names = new ArrayList<String>();
List<String[]> values = new ArrayList<String[]>();

for (int i = 0; i < 9; i++) {
	String n = request.getParameter("n" + i);
	String v = request.getParameter("v" + i);

	if ((n == null) || (v == null)) {
		break;
	}

	names.add(n);
	values.add(StringUtil.split(v));
}

int rowsCount = 1;

for (String[] vArray : values) {
	rowsCount = rowsCount * vArray.length;
}
%>

<aui:form method="post" name="fm">
	<aui:fieldset>
		<table border="1" cellpadding="4" cellspacing="0">
		<tr>

			<%
			for (String name : names) {
			%>

				<td>
					<strong><%= HtmlUtil.escape(name) %></strong>
				</td>

			<%
			}
			%>

			<td>
				<strong><liferay-ui:message key="quantity" /></strong>
			</td>
		</tr>

		<%
		for (int i = 0; i < rowsCount; i++) {
		%>

			<tr>

				<%
				for (int j = 0; j < names.size(); j++) {
					int numOfRepeats = 1;

					for (int k = j + 1; k < values.size(); k++) {
						String[] vArray = values.get(k);

						numOfRepeats = numOfRepeats * vArray.length;
					}

					String[] vArray = values.get(j);

					int arrayPos;

					for (arrayPos = i / numOfRepeats; arrayPos >= vArray.length; arrayPos = arrayPos - vArray.length) {
					}
				%>

					<td>
						<%= HtmlUtil.escape(vArray[arrayPos]) %>
					</td>

				<%
				}

				int fieldsQuantity = 0;

				if (i < fieldsQuantities.length) {
					fieldsQuantity = GetterUtil.getInteger(fieldsQuantities[i]);
				}
				%>

				<td>
					<aui:input label="" name='<%= "fieldsQuantity" + i %>' size="4" type="text" value="<%= fieldsQuantity %>" />
				</td>
			</tr>

		<%
		}
		%>

		</table>
	</aui:fieldset>

	<aui:button-row>
		<aui:button onClick='<%= renderResponse.getNamespace() + "updateItemQuantities();" %>' value="update" />

		<aui:button onClick="self.close();" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />updateItemQuantities() {
		var itemQuantities = "";

		<%
		for (int i = 0; i < rowsCount; i++) {
		%>

			itemQuantities = itemQuantities + document.<portlet:namespace />fm.<portlet:namespace />fieldsQuantity<%= i %>.value + ",";

		<%
		}
		%>

		opener.document.<portlet:namespace />fm.<portlet:namespace />fieldsQuantities.value = itemQuantities;

		self.close();
	}
</aui:script>