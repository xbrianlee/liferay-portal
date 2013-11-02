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

package com.liferay.portal.security.ldap;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.util.ClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class AttributesTransformerFactory {

	public static AttributesTransformer getInstance() {
		return _attributesTransformer;
	}

	public static void setInstance(
		AttributesTransformer attributesTransformer) {

		if (_log.isDebugEnabled()) {
			_log.debug("Set " + ClassUtil.getClassName(attributesTransformer));
		}

		if (attributesTransformer == null) {
			_attributesTransformer = _originalAttributesTransformer;
		}
		else {
			_attributesTransformer = attributesTransformer;
		}
	}

	public void afterPropertiesSet() throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug(
				"Instantiate " + PropsValues.LDAP_ATTRS_TRANSFORMER_IMPL);
		}

		ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

		_originalAttributesTransformer =
			(AttributesTransformer)InstanceFactory.newInstance(
				classLoader, PropsValues.LDAP_ATTRS_TRANSFORMER_IMPL);

		_attributesTransformer = _originalAttributesTransformer;
	}

	private static Log _log = LogFactoryUtil.getLog(
		AttributesTransformerFactory.class);

	private static volatile AttributesTransformer _attributesTransformer;
	private static AttributesTransformer _originalAttributesTransformer;

}