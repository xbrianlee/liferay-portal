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

import java.util.Properties;

import net.sf.ehcache.bootstrap.BootstrapCacheLoader;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-11061.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class JGroupsBootstrapCacheLoaderFactory
	extends
		net.sf.ehcache.distribution.jgroups.JGroupsBootstrapCacheLoaderFactory {

	@Override
	public BootstrapCacheLoader createBootstrapCacheLoader(
		Properties properties) {

		boolean asynchronous = extractAndValidateBootstrapAsynchronously(
			properties);
		int maximumChunkSize = extractMaximumChunkSizeBytes(properties);

		return new JGroupsBootstrapCacheLoader(asynchronous, maximumChunkSize);
	}

}