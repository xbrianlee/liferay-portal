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

package com.liferay.portal.security.ntlm.msrpc;

import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrObject;

/**
 * @author Marcellus Tavares
 */
public class GroupMembership extends NdrObject {

	public GroupMembership() {
	}

	public GroupMembership(int relativeId, int attributes) {
		_relativeId = relativeId;
		_attributes = attributes;
	}

	@Override
	public void decode(NdrBuffer ndrBuffer) {
		ndrBuffer.align(4);

		_relativeId = ndrBuffer.dec_ndr_long();
		_attributes = ndrBuffer.dec_ndr_long();
	}

	@Override
	public void encode(NdrBuffer ndrBuffer) {
		ndrBuffer.align(4);

		ndrBuffer.enc_ndr_long(_relativeId);
		ndrBuffer.enc_ndr_long(_attributes);
	}

	public int getAttributes() {
		return _attributes;
	}

	public int getRelativeId() {
		return _relativeId;
	}

	private int _attributes;
	private int _relativeId;

}