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

package com.liferay.portal.dao.orm.jpa;

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.ScrollableResults;

import java.util.List;

/**
 * @author Prashant Dighe
 * @author Brian Wing Shun Chan
 */
public class ScrollableResultsImpl implements ScrollableResults {

	public ScrollableResultsImpl(List<?> results) {
		_results = results;
		_last = _results.size();
	}

	@Override
	public boolean first() throws ORMException {
		if (_results.isEmpty()) {
			return false;
		}

		_current = 1;

		return true;
	}

	@Override
	public Object[] get() throws ORMException {
		Object[] result = null;

		Object object = _results.get(_current - 1);

		if (object instanceof Object[]) {
			result = (Object[])object;
		}
		else {
			result = new Object[] {object};
		}

		return result;
	}

	@Override
	public Object get(int i) throws ORMException {
		Object result = null;

		Object object = _results.get(_current - 1);

		if (object instanceof Object[]) {
			result = ((Object[])object)[i];
		}
		else {
			result = object;
		}

		return result;
	}

	@Override
	public boolean last() throws ORMException {
		if (_results.isEmpty()) {
			return false;
		}

		_current = _last;

		return true;
	}

	@Override
	public boolean next() throws ORMException {
		if (_current == _last) {
			return false;
		}

		_current++;

		return true;
	}

	@Override
	public boolean previous() throws ORMException {
		if (_current == 1) {
			return false;
		}

		_current--;

		return true;
	}

	@Override
	public boolean scroll(int i) throws ORMException {
		if (((_current + i) < 1) || ((_current + i) > _last)) {
			return false;
		}

		_current += i;

		return true;
	}

	private int _current = 0;
	private int _last = 0;
	private List<?> _results;

}