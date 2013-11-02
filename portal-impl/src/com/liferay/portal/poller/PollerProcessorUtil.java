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

package com.liferay.portal.poller;

import com.liferay.portal.kernel.poller.PollerProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
public class PollerProcessorUtil {

	public static void addPollerProcessor(
		String portletId, PollerProcessor pollerProcessor) {

		_instance._addPollerProcessor(portletId, pollerProcessor);
	}

	public static void deletePollerProcessor(String portletId) {
		_instance._deletePollerProcessor(portletId);
	}

	public static PollerProcessor getPollerProcessor(String portletId) {
		return _instance._getPollerProcessor(portletId);
	}

	private PollerProcessorUtil() {
	}

	private void _addPollerProcessor(
		String portletId, PollerProcessor pollerProcessor) {

		_pollerPorcessors.put(portletId, pollerProcessor);
	}

	private void _deletePollerProcessor(String portletId) {
		_pollerPorcessors.remove(portletId);
	}

	private PollerProcessor _getPollerProcessor(String portletId) {
		return _pollerPorcessors.get(portletId);
	}

	private static PollerProcessorUtil _instance = new PollerProcessorUtil();

	private Map<String, PollerProcessor> _pollerPorcessors =
		new ConcurrentHashMap<String, PollerProcessor>();

}