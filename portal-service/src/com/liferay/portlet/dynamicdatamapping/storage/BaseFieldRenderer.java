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

import java.util.Locale;

/**
 * @author Bruno Basto
 * @author Manuel de la Peña
 */
public abstract class BaseFieldRenderer implements FieldRenderer {

	@Override
	public String render(Field field, Locale locale) {
		try {
			return doRender(field, locale);
		}
		catch (Exception e) {
			_log.error("Unable to render field", e);
		}

		return null;
	}

	@Override
	public String render(Field field, Locale locale, int valueIndex) {
		try {
			return doRender(field, locale, valueIndex);
		}
		catch (Exception e) {
			_log.error("Unable to render field", e);
		}

		return null;
	}

	protected abstract String doRender(Field field, Locale locale)
		throws Exception;

	protected abstract String doRender(
			Field field, Locale locale, int valueIndex)
		throws Exception;

	private static Log _log = LogFactoryUtil.getLog(BaseFieldRenderer.class);

}