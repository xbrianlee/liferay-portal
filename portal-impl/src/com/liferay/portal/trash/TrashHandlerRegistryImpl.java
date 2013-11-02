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

package com.liferay.portal.trash;

import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.trash.TrashHandlerRegistry;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Alexander Chow
 */
@DoPrivileged
public class TrashHandlerRegistryImpl implements TrashHandlerRegistry {

	@Override
	public TrashHandler getTrashHandler(String className) {
		return _trashHandlers.get(className);
	}

	@Override
	public List<TrashHandler> getTrashHandlers() {
		return ListUtil.fromMapValues(_trashHandlers);
	}

	@Override
	public void register(TrashHandler trashHandler) {
		_trashHandlers.put(trashHandler.getClassName(), trashHandler);
	}

	@Override
	public void unregister(TrashHandler trashHandler) {
		_trashHandlers.remove(trashHandler.getClassName());
	}

	private Map<String, TrashHandler> _trashHandlers =
		new TreeMap<String, TrashHandler>();

}