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

package com.liferay.portal.convert;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.MaintenanceUtil;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Alexander Chow
 */
public abstract class ConvertProcess {

	public void convert() throws ConvertException {
		try {
			if (getPath() != null) {
				return;
			}

			StopWatch stopWatch = null;

			if (_log.isInfoEnabled()) {
				stopWatch = new StopWatch();

				stopWatch.start();

				_log.info("Starting conversion for " + getClass().getName());
			}

			doConvert();

			if (_log.isInfoEnabled()) {
				_log.info(
					"Finished conversion for " + getClass().getName() + " in " +
						stopWatch.getTime() + " ms");
			}
		}
		catch (Exception e) {
			throw new ConvertException(e);
		}
		finally {
			setParameterValues(null);

			MaintenanceUtil.cancel();
		}
	}

	public abstract String getDescription();

	public String getParameterDescription() {
		return null;
	}

	public String[] getParameterNames() {
		return null;
	}

	public String[] getParameterValues() {
		return _paramValues;
	}

	public String getPath() {
		return null;
	}

	public abstract boolean isEnabled();

	public void setParameterValues(String[] values) {
		_paramValues = values;
	}

	protected abstract void doConvert() throws Exception;

	private static Log _log = LogFactoryUtil.getLog(ConvertProcess.class);

	private String[] _paramValues = null;

}