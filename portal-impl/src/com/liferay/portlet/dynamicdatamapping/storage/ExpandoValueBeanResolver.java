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

package com.liferay.portlet.dynamicdatamapping.storage;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;

/**
 * @author Marcellus Tavares
 */
public class ExpandoValueBeanResolver implements BeanResolver {

	public ExpandoValueBeanResolver(List<ExpandoValue> expandoValues) {
		_expandoValues = new HashMap<String, ExpandoValue>();

		try {
			for (ExpandoValue expandoValue : expandoValues) {
				ExpandoColumn expandoColumn = expandoValue.getColumn();

				_expandoValues.put(expandoColumn.getName(), expandoValue);
			}
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
	}

	@Override
	public Object resolve(EvaluationContext context, String beanName) {
		return _expandoValues.get(beanName);
	}

	private static Log _log = LogFactoryUtil.getLog(
		ExpandoValueBeanResolver.class);

	private Map<String, ExpandoValue> _expandoValues;

}