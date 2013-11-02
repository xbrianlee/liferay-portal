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

package com.liferay.portlet.portletconfiguration.util;

import com.liferay.portal.model.PublicRenderParameter;
import com.liferay.portlet.PortletQNameUtil;

/**
 * @author Alberto Montero
 */
public class PublicRenderParameterConfiguration {

	public static final String IGNORE_PREFIX = "lfr-prp-ignore-";

	public static final String MAPPING_PREFIX = "lfr-prp-mapping-";

	public static String getIgnoreKey(
		PublicRenderParameter publicRenderParameter) {

		String publicRenderParameterName =
			PortletQNameUtil.getPublicRenderParameterName(
				publicRenderParameter.getQName());

		return IGNORE_PREFIX.concat(publicRenderParameterName);
	}

	public static String getMappingKey(
		PublicRenderParameter publicRenderParameter) {

		String publicRenderParameterName =
			PortletQNameUtil.getPublicRenderParameterName(
				publicRenderParameter.getQName());

		return MAPPING_PREFIX.concat(publicRenderParameterName);
	}

	public PublicRenderParameterConfiguration(
		PublicRenderParameter publicRenderParameter, String mappingValue,
		boolean ignoreValue) {

		_publicRenderParameter = publicRenderParameter;
		_publicRenderParameterName =
			PortletQNameUtil.getPublicRenderParameterName(
				publicRenderParameter.getQName());
		_mappingValue = mappingValue;
		_ignoreValue = ignoreValue;
	}

	public String getIgnoreKey() {
		return IGNORE_PREFIX.concat(_publicRenderParameterName);
	}

	public boolean getIgnoreValue() {
		return _ignoreValue;
	}

	public String getMappingKey() {
		return MAPPING_PREFIX.concat(_publicRenderParameterName);
	}

	public String getMappingValue() {
		return _mappingValue;
	}

	public PublicRenderParameter getPublicRenderParameter() {
		return _publicRenderParameter;
	}

	public String getPublicRenderParameterName() {
		return _publicRenderParameterName;
	}

	private boolean _ignoreValue;
	private String _mappingValue;
	private PublicRenderParameter _publicRenderParameter;
	private String _publicRenderParameterName;

}