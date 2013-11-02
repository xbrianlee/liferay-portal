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

package com.liferay.portal.kernel.template;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Jorge Ferrer
 */
public class TemplateVariableDefinition {

	public TemplateVariableDefinition(
		String label, Class<?> clazz, String name, String accessor) {

		this(
			label, clazz, StringPool.BLANK, name, accessor,
			label.concat("-help"), false, null);
	}

	public TemplateVariableDefinition(
		String label, Class<?> clazz, String dataType, String name,
		String accessor, String help, boolean repeatable,
		TemplateVariableCodeHandler templateVariableCodeHandler) {

		_label = label;
		_clazz = clazz;
		_dataType = dataType;
		_name = name;
		_accessor = accessor;
		_help = help;
		_repeatable = repeatable;
		_templateVariableCodeHandler = templateVariableCodeHandler;
	}

	public TemplateVariableDefinition(
		String label, Class<?> clazz, String name,
		TemplateVariableDefinition itemTemplateVariableDefinition) {

		this(label, clazz, name, StringPool.BLANK);

		_itemTemplateVariableDefinition = itemTemplateVariableDefinition;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TemplateVariableDefinition)) {
			return false;
		}

		TemplateVariableDefinition templateVariableDefinition =
			(TemplateVariableDefinition)obj;

		if (Validator.equals(_name, templateVariableDefinition._name) &&
			Validator.equals(_accessor, templateVariableDefinition._accessor)) {

			return true;
		}

		return false;
	}

	public String[] generateCode(String language) throws Exception {
		if (_templateVariableCodeHandler == null) {
			return null;
		}

		return _templateVariableCodeHandler.generate(this, language);
	}

	public String getAccessor() {
		return _accessor;
	}

	public Class<?> getClazz() {
		return _clazz;
	}

	public String getDataType() {
		return _dataType;
	}

	public String getHelp() {
		return _help;
	}

	public TemplateVariableDefinition getItemTemplateVariableDefinition() {
		return _itemTemplateVariableDefinition;
	}

	public String getLabel() {
		return _label;
	}

	public String getName() {
		return _name;
	}

	public TemplateVariableCodeHandler getTemplateVariableCodeHandler() {
		return _templateVariableCodeHandler;
	}

	public boolean isCollection() {
		if (_itemTemplateVariableDefinition != null) {
			return true;
		}

		return false;
	}

	public boolean isRepeatable() {
		return _repeatable;
	}

	private String _accessor;
	private Class<?> _clazz;
	private String _dataType;
	private String _help;
	private TemplateVariableDefinition _itemTemplateVariableDefinition;
	private String _label;
	private String _name;
	private boolean _repeatable;
	private TemplateVariableCodeHandler _templateVariableCodeHandler;

}