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
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_input_move_boxes_page") + StringPool.UNDERLINE;

String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-move-boxes:cssClass"));

String leftTitle = LanguageUtil.get(pageContext, (String)request.getAttribute("liferay-ui:input-move-boxes:leftTitle"));
String rightTitle = LanguageUtil.get(pageContext, (String)request.getAttribute("liferay-ui:input-move-boxes:rightTitle"));

String leftBoxName = (String)request.getAttribute("liferay-ui:input-move-boxes:leftBoxName");
String rightBoxName = (String)request.getAttribute("liferay-ui:input-move-boxes:rightBoxName");

String leftOnChange = (String)request.getAttribute("liferay-ui:input-move-boxes:leftOnChange");
String rightOnChange = (String)request.getAttribute("liferay-ui:input-move-boxes:rightOnChange");

boolean leftReorder = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-move-boxes:leftReorder"));
boolean rightReorder = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-move-boxes:rightReorder"));

List leftList = (List)request.getAttribute("liferay-ui:input-move-boxes:leftList");
List rightList = (List)request.getAttribute("liferay-ui:input-move-boxes:rightList");
%>

<div class="taglib-move-boxes <%= cssClass %> <%= leftReorder ? "left-reorder" : StringPool.BLANK %> <%= rightReorder ? "right-reorder" : StringPool.BLANK %>" id="<%= randomNamespace + "input-move-boxes" %>">
	<aui:row>
		<aui:col cssClass="left-selector-column" width="<%= 30 %>">
			<aui:select cssClass="choice-selector left-selector" label="<%= leftTitle %>" multiple="<%= true %>" name="<%= leftBoxName %>" onChange="<%= Validator.isNotNull(leftOnChange) ? leftOnChange : StringPool.BLANK %>" size="10">

				<%
				for (int i = 0; i < leftList.size(); i++) {
					KeyValuePair kvp = (KeyValuePair)leftList.get(i);
				%>

					<aui:option label="<%= kvp.getValue() %>" value="<%= kvp.getKey() %>" />

				<%
				}
				%>

			</aui:select>
		</aui:col>

		<aui:col cssClass="move-arrow-buttons" span="<%= 1 %>"></aui:col>

		<aui:col cssClass="right-selector-column" width="<%= 30 %>">
			<aui:select cssClass="choice-selector right-selector" label="<%= rightTitle %>" multiple="<%= true %>" name="<%= rightBoxName %>" onChange="<%= Validator.isNotNull(rightOnChange) ? rightOnChange : StringPool.BLANK %>" size="10">

				<%
				for (int i = 0; i < rightList.size(); i++) {
					KeyValuePair kvp = (KeyValuePair)rightList.get(i);
				%>

					<option value="<%= kvp.getKey() %>"><%= kvp.getValue() %></option>

				<%
				}
				%>

				</aui:select>
		</aui:col>
	</aui:row>
</div>

<aui:script use="liferay-input-move-boxes">
	new Liferay.InputMoveBoxes(
		{
			contentBox: '#<%= randomNamespace + "input-move-boxes" %>',
			strings: {
				LEFT_MOVE_DOWN: '<%= UnicodeLanguageUtil.format(pageContext, "move-selected-item-in-x-one-position-down", new Object[] {leftTitle}) %>',
				LEFT_MOVE_UP: '<%= UnicodeLanguageUtil.format(pageContext, "move-selected-item-in-x-one-position-up", new Object[] {leftTitle}) %>',
				MOVE_LEFT: '<%= UnicodeLanguageUtil.format(pageContext, "move-selected-items-from-x-to-x", new Object[] {leftTitle, rightTitle}) %>',
				MOVE_RIGHT: '<%= UnicodeLanguageUtil.format(pageContext, "move-selected-items-from-x-to-x", new Object[] {rightTitle, leftTitle}) %>',
				RIGHT_MOVE_DOWN: '<%= UnicodeLanguageUtil.format(pageContext, "move-selected-item-in-x-one-position-down", new Object[] {rightTitle}) %>',
				RIGHT_MOVE_UP: '<%= UnicodeLanguageUtil.format(pageContext, "move-selected-item-in-x-one-position-up", new Object[] {rightTitle}) %>'
			}
		}
	).render();
</aui:script>