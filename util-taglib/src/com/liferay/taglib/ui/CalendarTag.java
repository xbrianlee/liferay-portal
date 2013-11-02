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

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import java.text.Format;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class CalendarTag extends IncludeTag {

	public void setData(Set<Integer> data) {
		_data = data;
	}

	public void setDay(int day) {
		_day = day;
	}

	public void setHeaderFormat(Format headerFormat) {
		_headerFormat = headerFormat;
	}

	public void setHeaderPattern(String headerPattern) {
		_headerPattern = headerPattern;
	}

	public void setMonth(int month) {
		_month = month;
	}

	public void setShowAllPotentialWeeks(boolean showAllPotentialWeeks) {
		_showAllPotentialWeeks = showAllPotentialWeeks;
	}

	public void setYear(int year) {
		_year = year;
	}

	@Override
	protected void cleanUp() {
		_data = null;
		_day = 0;
		_headerFormat = null;
		_headerPattern = null;
		_month = 0;
		_showAllPotentialWeeks = false;
		_year = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:calendar:data", _data);
		request.setAttribute("liferay-ui:calendar:day", String.valueOf(_day));
		request.setAttribute(
			"liferay-ui:calendar:headerPattern", _headerPattern);
		request.setAttribute("liferay-ui:calendar:headerFormat", _headerFormat);
		request.setAttribute(
			"liferay-ui:calendar:month", String.valueOf(_month));
		request.setAttribute(
			"liferay-ui:calendar:showAllPotentialWeeks",
			String.valueOf(_showAllPotentialWeeks));
		request.setAttribute("liferay-ui:calendar:year", String.valueOf(_year));
	}

	private static final String _PAGE = "/html/taglib/ui/calendar/page.jsp";

	private Set<Integer> _data;
	private int _day;
	private Format _headerFormat;
	private String _headerPattern;
	private int _month;
	private boolean _showAllPotentialWeeks;
	private int _year;

}