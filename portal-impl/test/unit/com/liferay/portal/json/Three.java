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

package com.liferay.portal.json;

import com.liferay.portal.kernel.json.JSON;

/**
 * @author Igor Spasic
 */
@JSON
public class Three {

	@JSON(include = false)
	public String getIgnore() {
		return _ignore;
	}

	@JSON
	public boolean hasFeature() {
		return _feature;
	}

	public boolean isFlag() {
		return _flag;
	}

	public void setFlag(boolean flag) {
		this._flag = flag;
	}

	public void setIgnore(String ignore) {
		this._ignore = ignore;
	}

	private boolean _feature = true;
	private boolean _flag = true;
	private String _ignore;

}