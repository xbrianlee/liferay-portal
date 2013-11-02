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

package com.liferay.portal.layoutconfiguration.util.velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 */
public class InitColumnProcessor {

	public InitColumnProcessor() {
		_columns = new ArrayList<String>();
	}

	public List<String> getColumns() {
		return _columns;
	}

	public void processColumn(String columnId) {
		_columns.add(columnId);
	}

	public void processColumn(String columnId, String classNames) {
		_columns.add(columnId);
	}

	private List<String> _columns;

}