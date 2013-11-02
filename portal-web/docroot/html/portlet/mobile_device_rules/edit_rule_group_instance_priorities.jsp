<%--
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

<%@ include file="/html/portlet/mobile_device_rules/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String saveCallback = ParamUtil.getString(request, "saveCallback");

if (Validator.isNotNull(saveCallback)) {
	saveCallback = "Liferay.Util.getOpener()." + saveCallback + "(Liferay.Util.getWindow());";

	redirect = null;
}

String className = ParamUtil.getString(request, "className");
long classPK = ParamUtil.getLong(request, "classPK");

List<MDRRuleGroupInstance> ruleGroupInstances = MDRRuleGroupInstanceServiceUtil.getRuleGroupInstances(className, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new RuleGroupInstancePriorityComparator());
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title="manage-rule-priorities"
/>

<div class="alert alert-info">
	<liferay-ui:message key="to-manage-priorities,-drag-the-rule-to-the-desired-position" />
</div>

<div class="separator"><!-- --></div>

<portlet:actionURL var="editRuleGroupInstancesURL">
	<portlet:param name="struts_action" value="/mobile_device_rules/edit_rule_group_instance" />
</portlet:actionURL>

<aui:form action="<%= editRuleGroupInstancesURL %>" method="post" name="fm" onSubmit='<%= renderResponse.getNamespace() + "saveRuleGroupInstancesPriorities()" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="ruleGroupsInstancesJSON" type="hidden" />

	<div class="rule-group-instance-container" id="<portlet:namespace />ruleGroupInstancesPriorities">

		<%
		for (int i = 0; i < ruleGroupInstances.size(); i++) {
			MDRRuleGroupInstance ruleGroupInstance = ruleGroupInstances.get(i);

			MDRRuleGroup ruleGroup = ruleGroupInstance.getRuleGroup();
		%>

			<div class="rule-group-instance <%= (i == 0) ? "rule-group-instance-first" : StringPool.BLANK %>" data-rule-group-instance-id="<%= ruleGroupInstance.getRuleGroupInstanceId() %>">
				<span class="rule-group-instance-handle icon icon-grip-dotted-vertical"></span>

				<span class="rule-group-instance-label"><%= ruleGroup.getName(locale) %></span>

				<span class="rule-group-instance-priority">
					<liferay-ui:message key="priority" />:

					<span class="rule-group-instance-priority-value"><%= ruleGroupInstance.getPriority() %></span>
				</span>
			</div>

		<%
		}
		%>

	</div>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="<%= saveCallback %>" value="close" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />saveRuleGroupInstancesPriorities',
		function() {
			var A = AUI();

			var nodes = A.all('#<portlet:namespace />ruleGroupInstancesPriorities .rule-group-instance');

			var ruleGroupInstances = [];

			nodes.each(
				function(item, index, collection) {
					ruleGroupInstances.push(
						{
							priority: index,
							ruleGroupInstanceId: item.getAttribute('data-rule-group-instance-id')
						}
					);
				}
			);

			var ruleGroupsInstancesJSON = A.one('#<portlet:namespace />ruleGroupsInstancesJSON');

			if (ruleGroupsInstancesJSON) {
				ruleGroupsInstancesJSON.val(A.JSON.stringify(ruleGroupInstances));
			}

			submitForm(document.<portlet:namespace />fm);
		},
		['json']
	);
</aui:script>

<aui:script use="aui-base,dd-constrain,sortable">
	var container = A.one('#<portlet:namespace />ruleGroupInstancesPriorities');

	if (container) {
		var sortable = new A.Sortable(
			{
				container: container,
				handles: ['.rule-group-instance'],
				nodes: '.rule-group-instance',
				opacity: '.4',
				on: {
					moved: function(event) {
						var instance = this;

						var delegate = instance.delegate;

						var nodes = container.all('.rule-group-instance');

						var dragNode = event.drag.get('dragNode');

						var priorityNode = dragNode.one('.rule-group-instance-priority-value');

						if (priorityNode) {
							var currentNode = delegate.get('currentNode');

							priorityNode.html(nodes.indexOf(currentNode));
						}
					}
				}
			}
		);

		var sortableDD = sortable.delegate.dd;

		sortableDD.after(
			{
				'drag:end': function(event) {
					var drag = event.target;
					var dragNode = drag.get('dragNode');

					var nodes = container.all('.rule-group-instance');

					nodes.each(
						function(item, index, collection) {
							var priorityNode = item.one('.rule-group-instance-priority-value');

							priorityNode.html(index);
						}
					);

					dragNode.removeClass('rule-group-instance-dragging');
				},
				'drag:start': function(event) {
					var drag = event.target;
					var dragNode = drag.get('dragNode');

					dragNode.addClass('rule-group-instance-dragging');
				}
			}

		);

		sortableDD.plug(
			A.Plugin.DDConstrained,
			{
				constrain: container,
				stickY: true
			}
		);
	}
</aui:script>