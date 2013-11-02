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

package com.liferay.portal.kernel.nio.intraband.cache;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheManager;
import com.liferay.portal.kernel.io.Serializer;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.SystemDataType;

import java.io.Serializable;

import java.net.URL;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shuyang Zhou
 */
public class IntrabandPortalCacheManager
		<K extends Serializable, V extends Serializable>
	implements PortalCacheManager<K, V> {

	public static <K extends Serializable, V extends Serializable>
		PortalCacheManager<K, V> getPortalCacheManager() {

		return (PortalCacheManager<K, V>)_portalCacheManager;
	}

	public static void setPortalCacheManager(
		PortalCacheManager<? extends Serializable, ? extends Serializable>
		portalCacheManager) {

		_portalCacheManager = portalCacheManager;
	}

	public IntrabandPortalCacheManager(
		RegistrationReference registrationReference) {

		_intraband = registrationReference.getIntraband();
		_registrationReference = registrationReference;
	}

	@Override
	public void clearAll() {
		_portalCaches.clear();
	}

	@Override
	public PortalCache<K, V> getCache(String name) {
		return getCache(name, false);
	}

	@Override
	public PortalCache<K, V> getCache(String name, boolean blocking) {
		PortalCache<K, V> portalCache = _portalCaches.get(name);

		if (portalCache == null) {
			portalCache = new IntrabandPortalCache<K, V>(
				name, _registrationReference);

			_portalCaches.put(name, portalCache);
		}

		return portalCache;
	}

	@Override
	public void reconfigureCaches(URL configurationURL) {
		Serializer serializer = new Serializer();

		serializer.writeInt(PortalCacheActionType.RECONFIGURE.ordinal());
		serializer.writeString(configurationURL.toExternalForm());

		SystemDataType systemDataType = SystemDataType.PORTAL_CACHE;

		_intraband.sendDatagram(
			_registrationReference,
			Datagram.createRequestDatagram(
				systemDataType.getValue(), serializer.toByteBuffer()));
	}

	@Override
	public void removeCache(String name) {
		_portalCaches.remove(name);
	}

	private static PortalCacheManager
		<? extends Serializable, ? extends Serializable> _portalCacheManager;

	private final Intraband _intraband;
	private final Map<String, PortalCache<K, V>> _portalCaches =
		new ConcurrentHashMap<String, PortalCache<K, V>>();
	private final RegistrationReference _registrationReference;

}