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

package com.liferay.portlet.dynamicdatamapping.storage.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class JunctionImpl implements Junction {

	public JunctionImpl(LogicalOperator logicalOperator) {
		_logicalOperator = logicalOperator;
	}

	@Override
	public Junction add(Condition condition) {
		_conditions.add(condition);

		return this;
	}

	@Override
	public LogicalOperator getLogicalOperator() {
		return _logicalOperator;
	}

	@Override
	public boolean isJunction() {
		return _JUNCTION;
	}

	@Override
	public Iterator<Condition> iterator() {
		return _conditions.iterator();
	}

	private static final boolean _JUNCTION = true;

	private List<Condition> _conditions = new ArrayList<Condition>();
	private LogicalOperator _logicalOperator;

}