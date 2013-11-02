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

package com.liferay.portal.oauth;

import com.liferay.portal.kernel.oauth.Verifier;

/**
 * @author Brian Wing Shun Chan
 */
public class VerifierImpl implements Verifier {

	public VerifierImpl(org.scribe.model.Verifier verifier) {
		_verifier = verifier;
	}

	@Override
	public Object getWrappedVerifier() {
		return _verifier;
	}

	private org.scribe.model.Verifier _verifier;

}