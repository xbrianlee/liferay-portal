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

package com.liferay.portal.kernel.lar;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

/**
 * @author Raymond Augé
 */
public class PortletDataHandlerControl {

	public static String getNamespacedControlName(
		String namespace, String controlName) {

		StringBundler sb = new StringBundler(4);

		sb.append(StringPool.UNDERLINE);
		sb.append(namespace);
		sb.append(StringPool.UNDERLINE);
		sb.append(controlName);

		return sb.toString();
	}

	public PortletDataHandlerControl(String namespace, String controlName) {
		this(namespace, controlName, false);
	}

	public PortletDataHandlerControl(
		String namespace, String controlName, boolean disabled) {

		this(namespace, controlName, disabled, null);
	}

	public PortletDataHandlerControl(
		String namespace, String controlName, boolean disabled,
		String className) {

		this(namespace, controlName, disabled, className, null);
	}

	public PortletDataHandlerControl(
		String namespace, String controlName, boolean disabled,
		String className, String referrerClassName) {

		this(
			namespace, controlName, controlName, disabled, className,
			referrerClassName);
	}

	public PortletDataHandlerControl(
		String namespace, String controlName, String controlLabel,
		boolean disabled, String className, String referrerClassName) {

		_namespace = namespace;
		_controlLabel = controlLabel;
		_controlName = controlName;
		_disabled = disabled;
		_className = className;
		_referrerClassName = referrerClassName;
	}

	public String getClassName() {
		return _className;
	}

	public String getControlLabel() {
		return _controlLabel;
	}

	public String getControlName() {
		return _controlName;
	}

	public String getHelpMessage(Locale locale, String action) {
		String helpMessage = LanguageUtil.get(
			locale, action + "-" + _controlLabel + "-help", StringPool.BLANK);

		if (Validator.isNull(helpMessage)) {
			helpMessage = LanguageUtil.get(
				locale, "export-import-publish-" + _controlLabel + "-help",
				StringPool.BLANK);
		}

		return helpMessage;
	}

	public String getNamespace() {
		return _namespace;
	}

	public String getNamespacedControlName() {
		return getNamespacedControlName(_namespace, getControlName());
	}

	public String getReferrerClassName() {
		return _referrerClassName;
	}

	public boolean isDisabled() {
		return _disabled;
	}

	public void setNamespace(String namespace) {
		_namespace = namespace;
	}

	private String _className;
	private String _controlLabel;
	private String _controlName;
	private boolean _disabled;
	private String _namespace;
	private String _referrerClassName;

}