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

package com.liferay.portal.kernel.search;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class HitsProcessorRegistryUtil {

	public static HitsProcessor getDefaultHitsProcessor() {
		return _defaultHitsProcessor;
	}

	public static HitsProcessor getHitsProcessor(String className) {
		HitsProcessor hitsProcessor = _hitsProcessors.get(className);

		if (hitsProcessor != null) {
			return hitsProcessor;
		}

		return _defaultHitsProcessor;
	}

	public void setDefaultHitsProcessor(HitsProcessor hitsProcessor) {
		_defaultHitsProcessor = hitsProcessor;
	}

	public void setHitsProcessors(Map<String, HitsProcessor> hitsProcessors) {
		_hitsProcessors.putAll(hitsProcessors);
	}

	private static HitsProcessor _defaultHitsProcessor;
	private static Map<String, HitsProcessor> _hitsProcessors =
		new HashMap<String, HitsProcessor>();

}