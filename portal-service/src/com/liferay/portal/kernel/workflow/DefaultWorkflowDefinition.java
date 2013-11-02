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

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Map;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public class DefaultWorkflowDefinition
	implements Serializable, WorkflowDefinition {

	@Override
	public String getContent() {
		return _content;
	}

	@Override
	public InputStream getInputStream() {
		return _inputStream;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public Map<String, Object> getOptionalAttributes() {
		return _optionalAttributes;
	}

	@Override
	public String getTitle() {
		if (_title == null) {
			return StringPool.BLANK;
		}
		else {
			return _title;
		}
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	@Override
	public int getVersion() {
		return _version;
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setInputStream(InputStream inputStream) {
		_inputStream = inputStream;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOptionalAttributes(Map<String, Object> optionalAttributes) {
		_optionalAttributes = optionalAttributes;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setVersion(int version) {
		_version = version;
	}

	private boolean _active;
	private String _content;
	private InputStream _inputStream;
	private String _name;
	private Map<String, Object> _optionalAttributes;
	private String _title;
	private int _version;

}