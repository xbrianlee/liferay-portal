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

package com.liferay.portal.cache.ehcache;

import net.sf.ehcache.Ehcache;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-11061.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class JGroupsBootstrapCacheLoader
	extends net.sf.ehcache.distribution.jgroups.JGroupsBootstrapCacheLoader {

	public JGroupsBootstrapCacheLoader(
		boolean asynchronous, int maximumChunkSize) {

		super(asynchronous, maximumChunkSize);
	}

	@Override
	public Object clone() {
		return new JGroupsBootstrapCacheLoader(
			asynchronous, maximumChunkSizeBytes);
	}

	@Override
	public void load(Ehcache cache) {
		return;
	}

}