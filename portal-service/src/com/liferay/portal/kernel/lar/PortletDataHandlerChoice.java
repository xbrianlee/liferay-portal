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

/**
 * @author Raymond Augé
 */
public class PortletDataHandlerChoice extends PortletDataHandlerControl {

	public PortletDataHandlerChoice(String namespace, String controlName) {
		this(namespace, controlName, 0, _DEFAULT_CHOICES);
	}

	public PortletDataHandlerChoice(
		String namespace, String controlName, int defaultChoice) {

		this(namespace, controlName, defaultChoice, _DEFAULT_CHOICES);
	}

	public PortletDataHandlerChoice(
		String namespace, String controlName, int defaultChoice,
		String[] choices) {

		super(namespace, controlName);

		_choices = choices;
		_defaultChoice = defaultChoice;
	}

	public String[] getChoices() {
		if ((_choices == null) || (_choices.length < 1)) {
			return _DEFAULT_CHOICES;
		}
		else {
			return _choices;
		}
	}

	public String getDefaultChoice() {
		return getChoices()[getDefaultChoiceIndex()];
	}

	public int getDefaultChoiceIndex() {
		if ((_defaultChoice < 0) || (_defaultChoice >= _choices.length)) {
			return 0;
		}
		else {
			return _defaultChoice;
		}
	}

	private static final String[] _DEFAULT_CHOICES = new String[] {
		"false", "true"
	};

	private String[] _choices;
	private int _defaultChoice;

}