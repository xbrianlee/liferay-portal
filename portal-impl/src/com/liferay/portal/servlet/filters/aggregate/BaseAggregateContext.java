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

package com.liferay.portal.servlet.filters.aggregate;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedList;

/**
 * @author Raymond Augé
 * @author Eduardo Lundgren
 */
public abstract class BaseAggregateContext implements AggregateContext {

	@Override
	public String getFullPath(String path) {
		String listPath = _generatePathFromList();

		return listPath.concat(path);
	}

	@Override
	public String getResourcePath(String path) {
		return getFullPath(path);
	}

	@Override
	public String popPath() {
		if (_list.isEmpty()) {
			return null;
		}

		return _list.pop();
	}

	@Override
	public void pushPath(String path) {
		if (Validator.isNotNull(path)) {
			_list.push(path);
		}
	}

	@Override
	public String shiftPath() {
		if (_list.isEmpty()) {
			return null;
		}

		return _list.removeLast();
	}

	@Override
	public void unshiftPath(String path) {
		if (Validator.isNotNull(path)) {
			_list.addLast(path);
		}
	}

	private String _generatePathFromList() {
		StringBundler sb = new StringBundler(_list.size());

		for (int i = _list.size() - 1; i >= 0; i--) {
			String path = _list.get(i);

			sb.append(path);

			if (!path.endsWith(StringPool.SLASH)) {
				sb.append(StringPool.SLASH);
			}
		}

		return StringUtil.replace(
			sb.toString(), StringPool.DOUBLE_SLASH, StringPool.SLASH);
	}

	private LinkedList<String> _list = new LinkedList<String>();

}