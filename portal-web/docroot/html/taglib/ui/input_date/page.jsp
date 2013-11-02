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
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_input_date_page") + StringPool.UNDERLINE;

if (GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-date:disableNamespace"))) {
	namespace = StringPool.BLANK;
}

boolean autoFocus = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-date:autoFocus"));
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-date:cssClass"));
boolean disabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-date:disabled"));
String dayParam = namespace + request.getAttribute("liferay-ui:input-date:dayParam");
String dayParamId = namespace + request.getAttribute("liferay-ui:input-date:dayParamId");
int dayValue = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:input-date:dayValue"));
int firstDayOfWeek = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:input-date:firstDayOfWeek"));
String monthAndYearParam = namespace + request.getAttribute("liferay-ui:input-date:monthAndYearParam");
String monthParam = namespace + request.getAttribute("liferay-ui:input-date:monthParam");
String monthParamId = namespace + request.getAttribute("liferay-ui:input-date:monthParamId");
int monthValue = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:input-date:monthValue"));
String name = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-date:name"));
String yearParam = namespace + request.getAttribute("liferay-ui:input-date:yearParam");
String yearParamId = namespace + request.getAttribute("liferay-ui:input-date:yearParamId");
int yearValue = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:input-date:yearValue"));

Calendar calendar = CalendarFactoryUtil.getCalendar(yearValue, monthValue, dayValue);

String mask = _MASK_MDY;
String simpleDateFormatPattern = _SIMPLE_DATE_FORMAT_PATTERN_MDY;

if (BrowserSnifferUtil.isMobile(request)) {
	simpleDateFormatPattern = _SIMPLE_DATE_FORMAT_PATTERN_HTML5;
}
else {
	DateFormat shortDateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);

	SimpleDateFormat shortDateFormatSimpleDateFormat = (SimpleDateFormat)shortDateFormat;

	String shortDateFormatSimpleDateFormatPattern = shortDateFormatSimpleDateFormat.toPattern();

	if (shortDateFormatSimpleDateFormatPattern.indexOf("y") == 0) {
		mask = _MASK_YMD;
		simpleDateFormatPattern = _SIMPLE_DATE_FORMAT_PATTERN_YMD;
	}
	else if (shortDateFormatSimpleDateFormatPattern.indexOf("d") == 0) {
		mask = _MASK_DMY;
		simpleDateFormatPattern = _SIMPLE_DATE_FORMAT_PATTERN_DMY;
	}
}

Format format = FastDateFormatFactoryUtil.getSimpleDateFormat(simpleDateFormatPattern, locale);
%>

<span class="lfr-input-date <%= cssClass %>" id="<%= randomNamespace %>displayDate">
	<c:choose>
		<c:when test="<%= BrowserSnifferUtil.isMobile(request) %>">
			<input class="input-medium" <%= disabled ? "disabled=\"disabled\"" : "" %> id="<%= namespace + name %>" name="<%= namespace + name %>" type="date" value="<%= format.format(calendar.getTime()) %>" />
		</c:when>
		<c:otherwise>
			<input class="input-medium" <%= disabled ? "disabled=\"disabled\"" : "" %> id="<%= namespace + name %>" name="<%= namespace + name %>" placeholder="<%= StringUtil.toLowerCase(simpleDateFormatPattern) %>" type="text" value="<%= format.format(calendar.getTime()) %>" />
		</c:otherwise>
	</c:choose>

	<input <%= disabled ? "disabled=\"disabled\"" : "" %> id="<%= dayParamId %>" name="<%= dayParam %>" type="hidden" value="<%= dayValue %>" />
	<input <%= disabled ? "disabled=\"disabled\"" : "" %> id="<%= monthParamId %>" name="<%= monthParam %>" type="hidden" value="<%= monthValue %>" />
	<input <%= disabled ? "disabled=\"disabled\"" : "" %> id="<%= yearParamId %>" name="<%= yearParam %>" type="hidden" value="<%= yearValue %>" />
</span>

<aui:script use='<%= "aui-datepicker" + (BrowserSnifferUtil.isMobile(request) ? "-native" : StringPool.BLANK) %>'>
	Liferay.component(
		'<%= namespace + name %>DatePicker',
		function() {
			var datePicker = new A.DatePicker<%= BrowserSnifferUtil.isMobile(request) ? "Native" : StringPool.BLANK %>(
				{
					container: '#<%= randomNamespace %>displayDate',
					mask: '<%= mask %>',
					on: {
						disabledChange: function(event) {
							var instance = this;

							var container = instance.get('container');

							var newVal = event.newVal;

							container.one('#<%= dayParamId %>').attr('disabled', newVal);
							container.one('#<%= monthParamId %>').attr('disabled', newVal);
							container.one('#<%= namespace + name %>').attr('disabled', newVal);
							container.one('#<%= yearParamId %>').attr('disabled', newVal);
						},
						selectionChange: function(event) {
							var instance = this;

							var container = instance.get('container');

							var date = event.newSelection[0];

							if (date) {
								container.one('#<%= dayParamId %>').val(date.getDate());
								container.one('#<%= monthParamId %>').val(date.getMonth());
								container.one('#<%= yearParamId %>').val(date.getFullYear());
							}
						}
					},
					popover: {
						zIndex: Liferay.zIndex.TOOLTIP
					},
					trigger: '#<%= namespace + name %>'
				}
			);

			datePicker.getDate = function() {
				var instance = this;

				var container = instance.get('container');

				return new Date(container.one('#<%= yearParamId %>').val(), container.one('#<%= monthParamId %>').val(), container.one('#<%= dayParamId %>').val());
			};

			return datePicker;
		}
	);

	Liferay.component('<%= namespace + name %>DatePicker');
</aui:script>

<%!
private static final String _SIMPLE_DATE_FORMAT_PATTERN_DMY = "dd/MM/yyyy";

private static final String _SIMPLE_DATE_FORMAT_PATTERN_HTML5 = "yyyy-MM-dd";

private static final String _SIMPLE_DATE_FORMAT_PATTERN_MDY = "MM/dd/yyyy";

private static final String _SIMPLE_DATE_FORMAT_PATTERN_YMD = "yyyy/MM/dd";

private static final String _MASK_DMY = "%d/%m/%Y";

private static final String _MASK_MDY = "%m/%d/%Y";

private static final String _MASK_YMD = "%Y/%m/%d";
%>