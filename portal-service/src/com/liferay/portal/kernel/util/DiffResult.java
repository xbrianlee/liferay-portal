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

package com.liferay.portal.kernel.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Represents a change between one or several lines. <code>changeType</code>
 * tells if the change happened in source or target. <code>lineNumber</code>
 * holds the line number of the first modified line. This line number refers to
 * a line in source or target, depending on the <code>changeType</code> value.
 * <code>changedLines</code> is a list of strings, each string is a line that is
 * already highlighted, indicating where the changes are.
 * </p>
 *
 * @author Bruno Farache
 */
public class DiffResult {

	public static final String SOURCE = "SOURCE";

	public static final String TARGET = "TARGET";

	public DiffResult(int linePos, List<String> changedLines) {
		_lineNumber = linePos + 1;
		_changedLines = changedLines;
	}

	public DiffResult(int linePos, String changedLine) {
		_lineNumber = linePos + 1;
		_changedLines = new ArrayList<String>();
		_changedLines.add(changedLine);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DiffResult)) {
			return false;
		}

		DiffResult diffResult = (DiffResult)obj;

		if ((diffResult.getLineNumber() == _lineNumber) &&
			diffResult.getChangedLines().equals(_changedLines)) {

			return true;
		}

		return false;
	}

	public List<String> getChangedLines() {
		return _changedLines;
	}

	public int getLineNumber() {
		return _lineNumber;
	}

	@Override
	public int hashCode() {
		HashCode hashCode = HashCodeFactoryUtil.getHashCode();

		hashCode.append(_lineNumber);
		hashCode.append(_changedLines);

		return hashCode.toHashCode();
	}

	public void setChangedLines(List<String> changedLines) {
		_changedLines = changedLines;
	}

	public void setLineNumber(int lineNumber) {
		_lineNumber = lineNumber;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(2 * _changedLines.size() + 3);

		sb.append("Line: ");
		sb.append(_lineNumber);
		sb.append("\n");

		for (String changedLine : _changedLines) {
			sb.append(changedLine);
			sb.append("\n");
		}

		if (!_changedLines.isEmpty()) {
			sb.setIndex(sb.index() - 1);
		}

		return sb.toString();
	}

	private List<String> _changedLines;
	private int _lineNumber;

}