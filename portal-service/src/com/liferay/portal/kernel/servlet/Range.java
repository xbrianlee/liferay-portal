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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Juan González
 */
public class Range {

	public Range(long start, long end, long total) {
		_start = start;
		_end = end;
		_length = end - start + 1;
		_total = total;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Range)) {
			return false;
		}

		Range range = (Range)obj;

		if ((_end == range._end) && (_length == range._length) &&
			(_start == range._start) && (_total == range._total)) {

			return true;
		}

		return false;
	}

	public String getContentRange() {
		StringBundler sb = new StringBundler(6);

		sb.append("bytes ");
		sb.append(getStart());
		sb.append(StringPool.DASH);
		sb.append(getEnd());
		sb.append(StringPool.SLASH);
		sb.append(getTotal());

		return sb.toString();
	}

	public long getEnd() {
		return _end;
	}

	public long getLength() {
		return _length;
	}

	public long getStart() {
		return _start;
	}

	public long getTotal() {
		return _total;
	}

	@Override
	public int hashCode() {
		int result = 1;

		result = _PRIME * result + (int) (_end ^ (_end >>> 32));
		result = _PRIME * result + (int) (_length ^ (_length >>> 32));
		result = _PRIME * result + (int) (_start ^ (_start >>> 32));
		result = _PRIME * result + (int) (_total ^ (_total >>> 32));

		return result;
	}

	public void setEnd(long end) {
		_end = end;
	}

	public void setLength(long length) {
		_length = length;
	}

	public void setStart(long start) {
		_start = start;
	}

	public void setTotal(long total) {
		_total = total;
	}

	private static final int _PRIME = 31;

	private long _end;
	private long _length;
	private long _start;
	private long _total;

}