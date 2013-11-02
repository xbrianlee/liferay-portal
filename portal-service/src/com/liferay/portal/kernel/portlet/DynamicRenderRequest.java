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

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.filter.RenderRequestWrapper;

/**
 * @author Brian Wing Shun Chan
 * @see    DynamicActionRequest
 * @see    DynamicEventRequest
 * @see    DynamicResourceRequest
 */
public class DynamicRenderRequest extends RenderRequestWrapper {

	public DynamicRenderRequest(RenderRequest renderRequest) {
		this(renderRequest, null, true);
	}

	public DynamicRenderRequest(RenderRequest renderRequest, boolean inherit) {
		this(renderRequest, null, inherit);
	}

	public DynamicRenderRequest(
		RenderRequest renderRequest, Map<String, String[]> params) {

		this(renderRequest, params, true);
	}

	public DynamicRenderRequest(
		RenderRequest renderRequest, Map<String, String[]> params,
		boolean inherit) {

		super(renderRequest);

		_params = new HashMap<String, String[]>();
		_inherit = inherit;

		if (params != null) {
			_params.putAll(params);
		}

		if (_inherit && (renderRequest instanceof DynamicRenderRequest)) {
			DynamicRenderRequest dynamicRenderRequest =
				(DynamicRenderRequest)renderRequest;

			setRequest(dynamicRenderRequest.getRequest());

			params = dynamicRenderRequest.getDynamicParameterMap();

			for (Map.Entry<String, String[]> entry : params.entrySet()) {
				String name = entry.getKey();
				String[] oldValues = entry.getValue();

				String[] curValues = _params.get(name);

				if (curValues == null) {
					_params.put(name, oldValues);
				}
				else {
					String[] newValues = ArrayUtil.append(oldValues, curValues);

					_params.put(name, newValues);
				}
			}
		}
	}

	public Map<String, String[]> getDynamicParameterMap() {
		return _params;
	}

	@Override
	public String getParameter(String name) {
		String[] values = _params.get(name);

		if (_inherit && (values == null)) {
			return super.getParameter(name);
		}

		if (ArrayUtil.isNotEmpty(values)) {
			return values[0];
		}
		else {
			return null;
		}
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = new HashMap<String, String[]>();

		if (_inherit) {
			map.putAll(super.getParameterMap());
		}

		map.putAll(_params);

		return map;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		Set<String> names = new LinkedHashSet<String>();

		if (_inherit) {
			Enumeration<String> enu = super.getParameterNames();

			while (enu.hasMoreElements()) {
				names.add(enu.nextElement());
			}
		}

		names.addAll(_params.keySet());

		return Collections.enumeration(names);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = _params.get(name);

		if (_inherit && (values == null)) {
			return super.getParameterValues(name);
		}

		return values;
	}

	public void setParameter(String name, String value) {
		_params.put(name, new String[] {value});
	}

	public void setParameterValues(String name, String[] values) {
		_params.put(name, values);
	}

	private boolean _inherit;
	private Map<String, String[]> _params;

}