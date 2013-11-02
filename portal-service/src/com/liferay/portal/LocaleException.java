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

package com.liferay.portal;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 */
public class LocaleException extends PortalException {

	public static final int TYPE_CONTENT = 3;

	public static final int TYPE_DEFAULT = 4;

	public static final int TYPE_DISPLAY_SETTINGS = 1;

	public static final int TYPE_EXPORT_IMPORT = 2;

	public LocaleException() {
		super();
	}

	public LocaleException(int type) {
		_type = type;
	}

	public LocaleException(int type, String msg) {
		super(msg);

		_type = type;
	}

	public LocaleException(int type, String msg, Throwable cause) {
		super(msg, cause);

		_type = type;
	}

	public LocaleException(int type, Throwable cause) {
		super(cause);

		_type = type;
	}

	public Locale[] getSourceAvailableLocales() {
		return _sourceAvailableLocales;
	}

	public Locale[] getTargetAvailableLocales() {
		return _targetAvailableLocales;
	}

	public int getType() {
		return _type;
	}

	public void setSourceAvailableLocales(Locale[] sourceAvailableLocales) {
		_sourceAvailableLocales = sourceAvailableLocales;
	}

	public void setTargetAvailableLocales(Locale[] targetAvailableLocales) {
		_targetAvailableLocales = targetAvailableLocales;
	}

	private Locale[] _sourceAvailableLocales;
	private Locale[] _targetAvailableLocales;
	private int _type;

}