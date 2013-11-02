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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.json.JSON;

import java.util.List;

/**
 * @author Igor Spasic
 */
public class FooDataPage {

	public FooDataPage(FooData data, List<FooData> list, int page) {
		_data = data;
		_list = list;
		_page = page;
	}

	public FooData getData() {
		return _data;
	}

	public List<FooData> getList() {
		return _list;
	}

	public int getPage() {
		return _page;
	}

	public void setData(FooData data) {
		_data = data;
	}

	public void setList(List<FooData> list) {
		_list = list;
	}

	public void setPage(int page) {
		_page = page;
	}

	private FooData _data;

	@JSON
	private List<FooData> _list;

	private int _page;

}