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

package com.liferay.portal.json.transformer;

import com.liferay.portal.kernel.json.JSONIncludesManagerUtil;
import com.liferay.portal.kernel.json.JSONTransformer;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import flexjson.JSONContext;
import flexjson.Path;
import flexjson.PathExpression;

import flexjson.transformer.ObjectTransformer;

import java.util.List;

import jodd.bean.BeanUtil;

/**
 * @author Igor Spasic
 */
public class FlexjsonObjectJSONTransformer
	extends ObjectTransformer implements JSONTransformer {

	@Override
	public void transform(Object object) {
		Class<?> type = resolveClass(object);

		List<PathExpression> pathExpressions =
			(List<PathExpression>)BeanUtil.getDeclaredProperty(
				getContext(), "pathExpressions");

		String path = _getPath();

		String[] excludes = JSONIncludesManagerUtil.lookupExcludes(type);

		_exclude(pathExpressions, path, excludes);

		String[] includes = JSONIncludesManagerUtil.lookupIncludes(type);

		_include(pathExpressions, path, includes);

		super.transform(object);
	}

	private void _exclude(
		List<PathExpression> pathExpressions, String path, String... names) {

		for (String name : names) {
			PathExpression pathExpression = new PathExpression(
				path.concat(name), false);

			for (int i = 0; i < pathExpressions.size(); i++) {
				PathExpression curPathExpression = pathExpressions.get(i);

				if (pathExpression.equals(curPathExpression) &&
					curPathExpression.isIncluded()) {

					// Same path expression found, but it was included,
					// therefore, replace it with the excluded path expression

					pathExpressions.set(i, pathExpression);

					return;
				}
			}

			pathExpressions.add(pathExpression);
		}
	}

	private String _getPath() {
		JSONContext jsonContext = getContext();

		Path path = jsonContext.getPath();

		List<String> paths = path.getPath();

		if (paths.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(paths.size() * 2);

		for (String curPath : paths) {
			sb.append(curPath);
			sb.append(CharPool.PERIOD);
		}

		return sb.toString();
	}

	private void _include(
		List<PathExpression> pathExpressions, String path, String... names) {

		for (String name : names) {
			PathExpression pathExpression = new PathExpression(
				path.concat(name), true);

			if (!pathExpressions.contains(pathExpression)) {
				pathExpressions.add(0, pathExpression);
			}
		}
	}

}