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

package com.liferay.portal.atom;

import com.liferay.portal.kernel.atom.AtomCollectionAdapter;
import com.liferay.portal.kernel.atom.AtomCollectionAdapterRegistry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Igor Spasic
 */
@DoPrivileged
public class AtomCollectionAdapterRegistryImpl
	implements AtomCollectionAdapterRegistry {

	@Override
	public AtomCollectionAdapter<?> getAtomCollectionAdapter(
		String collectionName) {

		return _atomCollectionAdapters.get(collectionName);
	}

	@Override
	public List<AtomCollectionAdapter<?>> getAtomCollectionAdapters() {
		return ListUtil.fromMapValues(_atomCollectionAdapters);
	}

	@Override
	public void register(AtomCollectionAdapter<?> atomCollectionAdapter) {
		if (_atomCollectionAdapters.containsKey(
				atomCollectionAdapter.getCollectionName())) {

			if (_log.isWarnEnabled()) {
				_log.warn(
					"Duplicate collection name " +
						atomCollectionAdapter.getCollectionName());
			}

			return;
		}

		_atomCollectionAdapters.put(
			atomCollectionAdapter.getCollectionName(), atomCollectionAdapter);
	}

	@Override
	public void unregister(AtomCollectionAdapter<?> atomCollectionAdapter) {
		_atomCollectionAdapters.remove(
			atomCollectionAdapter.getCollectionName());
	}

	private static Log _log = LogFactoryUtil.getLog(
		AtomCollectionAdapterRegistryImpl.class);

	private Map<String, AtomCollectionAdapter<?>> _atomCollectionAdapters =
		new ConcurrentHashMap<String, AtomCollectionAdapter<?>>();

}