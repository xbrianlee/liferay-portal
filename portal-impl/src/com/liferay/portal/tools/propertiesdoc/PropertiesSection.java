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

package com.liferay.portal.tools.propertiesdoc;

import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.List;

/**
 * @author Jesse Rao
 * @author James Hinkey
 */
public class PropertiesSection {

	public PropertiesSection(String text) {
		_text = text;
	}

	public List<String> getComments() {
		return _comments;
	}

	public String getDefaultProperties() {
		return _defaultProperties;
	}

	public String getExampleProperties() {
		return _exampleProperties;
	}

	public List<PropertyComment> getPropertyComments() {
		return _propertyComments;
	}

	public String getText() {
		return _text;
	}

	public String getTitle() {
		return _title;
	}

	public boolean hasComments() {
		return !_comments.isEmpty();
	}

	public boolean hasDefaultProperties() {
		return Validator.isNotNull(_defaultProperties);
	}

	public boolean hasExampleProperties() {
		return Validator.isNotNull(_exampleProperties);
	}

	public boolean hasPropertyComments() {
		return !_propertyComments.isEmpty();
	}

	public boolean hasTitle() {
		return Validator.isNotNull(_title);
	}

	public void setComments(List<String> comments) {
		_comments = comments;
	}

	public void setDefaultProperties(String defaultProperties) {
		_defaultProperties = defaultProperties;
	}

	public void setExampleProperties(String exampleProperties) {
		_exampleProperties = exampleProperties;
	}

	public void setPropertyComments(List<PropertyComment> propertyComments) {
		_propertyComments = propertyComments;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private List<String> _comments = Collections.emptyList();
	private String _defaultProperties;
	private String _exampleProperties;
	private List<PropertyComment> _propertyComments = Collections.emptyList();
	private final String _text;
	private String _title;

}