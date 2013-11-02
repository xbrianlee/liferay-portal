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

package com.liferay.portal.search;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.DummyIndexer;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.spring.aop.ServiceBeanAopCacheManagerUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Raymond Augé
 */
public class IndexerRegistryImpl implements IndexerRegistry {

	@Override
	public Indexer getIndexer(String className) {
		return _indexers.get(className);
	}

	@Override
	public List<Indexer> getIndexers() {
		return ListUtil.fromMapValues(_indexers);
	}

	@Override
	public Indexer nullSafeGetIndexer(String className) {
		Indexer indexer = _indexers.get(className);

		if (indexer != null) {
			return indexer;
		}

		if (_log.isWarnEnabled()) {
			_log.warn("No indexer found for " + className);
		}

		return _dummyIndexer;
	}

	@Override
	public void register(String className, Indexer indexerInstance) {
		_indexers.put(className, indexerInstance);

		ServiceBeanAopCacheManagerUtil.reset();
	}

	@Override
	public void unregister(String className) {
		_indexers.remove(className);
	}

	private static Log _log = LogFactoryUtil.getLog(IndexerRegistryImpl.class);

	private Indexer _dummyIndexer = new DummyIndexer();
	private Map<String, Indexer> _indexers =
		new ConcurrentHashMap<String, Indexer>();

}