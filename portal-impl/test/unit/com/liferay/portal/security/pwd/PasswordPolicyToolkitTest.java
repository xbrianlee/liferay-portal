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

package com.liferay.portal.security.pwd;

import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.impl.PasswordPolicyImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mika Koivisto
 */
public class PasswordPolicyToolkitTest {

	@Before
	public void setUp() throws Exception {
		_passwordPolicyToolkit = new PasswordPolicyToolkit();

		_passwordPolicy = new PasswordPolicyImpl();

		_passwordPolicy.setAllowDictionaryWords(true);
		_passwordPolicy.setChangeable(true);
		_passwordPolicy.setCheckSyntax(true);
		_passwordPolicy.setMinAlphanumeric(5);
		_passwordPolicy.setMinLength(8);
		_passwordPolicy.setMinLowerCase(2);
		_passwordPolicy.setMinUpperCase(2);
		_passwordPolicy.setMinNumbers(1);
		_passwordPolicy.setMinSymbols(1);
		_passwordPolicy.setRegex(".{5,}");
	}

	@Test
	public void testGeneratePassword() {
		String password = _passwordPolicyToolkit.generate(_passwordPolicy);

		try {
			_passwordPolicyToolkit.validate(
				password, password, _passwordPolicy);
		}
		catch (Exception e) {
			Assert.fail("Generated password does not validate against policy");
		}
	}

	@Test
	public void testValidateLength() {
		Assert.assertEquals(false, validate("xH9fxM@"));
	}

	@Test
	public void testValidateMinAlphanumeric() {
		Assert.assertEquals(false, validate("xH9f.,@-"));
	}

	@Test
	public void testValidateMinLowerChars() {
		Assert.assertEquals(false, validate("xHFXM@W"));
	}

	@Test
	public void testValidateMinNumbers() {
		Assert.assertEquals(false, validate("xHafxMkw"));
	}

	@Test
	public void testValidateMinSpecial() {
		Assert.assertEquals(false, validate("xH9fxMkw"));
	}

	@Test
	public void testValidateMinUpperChars() {
		Assert.assertEquals(false, validate("xh9fxM@w"));
	}

	@Test
	public void testValidateRegex() {
		Assert.assertEquals(false, validate("xH9fxM@"));
	}

	@Test
	public void testValidateValid() {
		Assert.assertEquals(true, validate("xH9fxM@w"));
	}

	protected boolean validate(String password) {
		try {
			_passwordPolicyToolkit.validate(
				password, password, _passwordPolicy);
		}
		catch (Exception e) {
			return false;
		}

		return true;
	}

	private PasswordPolicy _passwordPolicy;
	private PasswordPolicyToolkit _passwordPolicyToolkit;

}