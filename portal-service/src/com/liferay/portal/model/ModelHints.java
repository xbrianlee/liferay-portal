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

package com.liferay.portal.model;

import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.xml.Element;

import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public interface ModelHints {

	public String buildCustomValidatorName(String validatorName);

	public Map<String, String> getDefaultHints(String model);

	public Element getFieldsEl(String model, String field);

	public Map<String, String> getHints(String model, String field);

	public int getMaxLength(String model, String field);

	public List<String> getModels();

	public Tuple getSanitizeTuple(String model, String field);

	public List<Tuple> getSanitizeTuples(String model);

	public String getType(String model, String field);

	public List<Tuple> getValidators(String model, String field);

	public String getValue(
		String model, String field, String name, String defaultValue);

	public boolean hasField(String model, String field);

	public boolean isCustomValidator(String validatorName);

	public boolean isLocalized(String model, String field);

	public void read(ClassLoader classLoader, String source) throws Exception;

	public String trimString(String model, String field, String value);

}