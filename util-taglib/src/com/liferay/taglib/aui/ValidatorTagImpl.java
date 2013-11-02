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

package com.liferay.taglib.aui;

import com.liferay.portal.kernel.servlet.taglib.aui.ValidatorTag;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelHintsUtil;
import com.liferay.taglib.aui.base.BaseValidatorTagImpl;

import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * @author Julio Camarero
 * @author Brian Wing Shun Chan
 */
public class ValidatorTagImpl
	extends BaseValidatorTagImpl implements BodyTag, ValidatorTag {

	public ValidatorTagImpl() {
	}

	public ValidatorTagImpl(
		String name, String errorMessage, String body, boolean custom) {

		setName(name);
		setErrorMessage(errorMessage);

		_body = body;
		_custom = custom;
	}

	@Override
	public void cleanUp() {
		super.cleanUp();

		_body = null;
		_custom = false;
	}

	@Override
	public int doAfterBody() {
		BodyContent bodyContent = getBodyContent();

		if (bodyContent != null) {
			_body = bodyContent.getString();
		}

		return SKIP_BODY;
	}

	@Override
	public int doEndTag() {
		InputTag inputTag = (InputTag)findAncestorWithClass(
			this, InputTag.class);

		String name = getName();

		_custom = ModelHintsUtil.isCustomValidator(name);

		if (_custom) {
			name = ModelHintsUtil.buildCustomValidatorName(name);
		}

		ValidatorTag validatorTag = new ValidatorTagImpl(
			name, getErrorMessage(), _body, _custom);

		inputTag.addValidatorTag(name, validatorTag);

		return EVAL_BODY_BUFFERED;
	}

	@Override
	public String getBody() {
		if (Validator.isNull(_body)) {
			return StringPool.DOUBLE_APOSTROPHE;
		}

		return _body.trim();
	}

	@Override
	public String getErrorMessage() {
		String errorMessage = super.getErrorMessage();

		if (errorMessage == null) {
			return StringPool.BLANK;
		}

		return errorMessage;
	}

	@Override
	public boolean isCustom() {
		return _custom;
	}

	public void setBody(String body) {
		_body = body;
	}

	protected String processCustom(String name) {
		if (name.equals("custom")) {
			_custom = true;

			return name.concat(StringPool.UNDERLINE).concat(
				StringUtil.randomId());
		}

		return name;
	}

	private String _body;
	private boolean _custom;

}