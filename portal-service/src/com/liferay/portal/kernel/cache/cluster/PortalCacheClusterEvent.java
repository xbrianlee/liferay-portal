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

package com.liferay.portal.kernel.cache.cluster;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class PortalCacheClusterEvent implements Serializable {

	public PortalCacheClusterEvent(
		String cacheName, Serializable elementKey,
		PortalCacheClusterEventType portalCacheClusterEventType) {

		this(cacheName, elementKey, null, portalCacheClusterEventType);
	}

	public PortalCacheClusterEvent(
		String cacheName, Serializable elementKey, Serializable elementValue,
		PortalCacheClusterEventType portalCacheClusterEventType) {

		_cacheName = cacheName;
		_elementKey = elementKey;
		_elementValue = elementValue;
		_portalCacheClusterEventType = portalCacheClusterEventType;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PortalCacheClusterEvent)) {
			return false;
		}

		PortalCacheClusterEvent portalCacheClusterEvent =
			(PortalCacheClusterEvent)obj;

		if (Validator.equals(_cacheName, portalCacheClusterEvent._cacheName) &&
			Validator.equals(
				_elementKey, portalCacheClusterEvent._elementKey) &&
			Validator.equals(
				_elementValue, portalCacheClusterEvent._elementValue) &&
			Validator.equals(
				_portalCacheClusterEventType,
				portalCacheClusterEvent._portalCacheClusterEventType)) {

			return true;
		}

		return false;
	}

	public String getCacheName() {
		return _cacheName;
	}

	public Serializable getElementKey() {
		return _elementKey;
	}

	public Serializable getElementValue() {
		return _elementValue;
	}

	public PortalCacheClusterEventType getEventType() {
		return _portalCacheClusterEventType;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	public void setElementValue(Serializable elementValue) {
		_elementValue = elementValue;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append(_cacheName);
		sb.append(StringPool.COLON);
		sb.append(_elementKey);
		sb.append(StringPool.COLON);

		if (_elementValue != null) {
			sb.append(_elementValue.toString());
			sb.append(StringPool.COLON);
		}

		sb.append(_portalCacheClusterEventType.toString());

		return sb.toString();
	}

	private String _cacheName;
	private Serializable _elementKey;
	private Serializable _elementValue;
	private PortalCacheClusterEventType _portalCacheClusterEventType;

}