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

package com.liferay.portlet.asset.model;

import com.liferay.portal.kernel.json.JSON;

import java.io.Serializable;

import java.util.List;

/**
 * @author Igor Spasic
 */
public class AssetTagDisplay implements Serializable {

	public AssetTagDisplay() {
	}

	public AssetTagDisplay(List<AssetTag> tags, int total, int start, int end) {
		_tags = tags;
		_total = total;
		_start = start;
		_end = end;
	}

	public int getEnd() {
		return _end;
	}

	public int getPage() {
		if ((_end > 0) && (_start >= 0)) {
			return _end / (_end - _start);
		}

		return 0;
	}

	public int getStart() {
		return _start;
	}

	public List<AssetTag> getTags() {
		return _tags;
	}

	public int getTotal() {
		return _total;
	}

	public void setEnd(int end) {
		_end = end;
	}

	public void setStart(int start) {
		_start = start;
	}

	public void setTags(List<AssetTag> tags) {
		_tags = tags;
	}

	public void setTotal(int total) {
		_total = total;
	}

	private int _end;
	private int _start;

	@JSON
	private List<AssetTag> _tags;

	private int _total;

}