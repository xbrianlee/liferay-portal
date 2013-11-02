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

package com.liferay.portal.lar;

import com.liferay.portal.kernel.lar.StagedModelDataHandler;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerRegistry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the staged model data handler registry framework.
 *
 * @author Mate Thurzo
 * @see    com.liferay.portal.kernel.lar.StagedModelDataHandlerRegistryUtil
 * @since  6.2
 */
@DoPrivileged
public class StagedModelDataHandlerRegistryImpl
	implements StagedModelDataHandlerRegistry {

	@Override
	public StagedModelDataHandler<?> getStagedModelDataHandler(
		String className) {

		return _stagedModelDataHandlers.get(className);
	}

	@Override
	public List<StagedModelDataHandler<?>> getStagedModelDataHandlers() {
		return ListUtil.fromMapValues(_stagedModelDataHandlers);
	}

	@Override
	public void register(StagedModelDataHandler<?> stagedModelDataHandler) {
		for (String className : stagedModelDataHandler.getClassNames()) {
			if (_stagedModelDataHandlers.containsKey(className)) {
				if (_log.isWarnEnabled()) {
					_log.warn("Duplicate class " + className);
				}

				continue;
			}

			_stagedModelDataHandlers.put(className, stagedModelDataHandler);
		}
	}

	@Override
	public void unregister(StagedModelDataHandler<?> stagedModelDataHandler) {
		for (String className : stagedModelDataHandler.getClassNames()) {
			_stagedModelDataHandlers.remove(className);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		StagedModelDataHandlerRegistryImpl.class);

	private Map<String, StagedModelDataHandler<?>> _stagedModelDataHandlers =
		new HashMap<String, StagedModelDataHandler<?>>();

}