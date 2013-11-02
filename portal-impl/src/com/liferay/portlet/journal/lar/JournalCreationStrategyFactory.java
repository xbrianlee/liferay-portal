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

package com.liferay.portlet.journal.lar;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.ClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Joel Kozikowski
 */
public class JournalCreationStrategyFactory {

	public static JournalCreationStrategy getInstance() {
		if (_journalCreationStrategy == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Instantiate " + PropsValues.JOURNAL_LAR_CREATION_STRATEGY);
			}

			ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

			try {
				_journalCreationStrategy =
					(JournalCreationStrategy)classLoader.loadClass(
						PropsValues.JOURNAL_LAR_CREATION_STRATEGY).
							newInstance();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Return " + _journalCreationStrategy.getClass().getName());
		}

		return _journalCreationStrategy;
	}

	public static void setInstance(
		JournalCreationStrategy journalCreationStrategy) {

		if (_log.isDebugEnabled()) {
			_log.debug("Set " + journalCreationStrategy.getClass().getName());
		}

		_journalCreationStrategy = journalCreationStrategy;
	}

	private static Log _log = LogFactoryUtil.getLog(
		JournalCreationStrategyFactory.class);

	private static JournalCreationStrategy _journalCreationStrategy;

}