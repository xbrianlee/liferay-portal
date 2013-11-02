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

package com.liferay.portal.kernel.cluster;

import com.liferay.portal.kernel.io.Deserializer;
import com.liferay.portal.kernel.io.Serializer;
import com.liferay.portal.kernel.util.Base64;

import java.nio.ByteBuffer;

/**
 * @author Shuyang Zhou
 */
public class AddressSerializerUtil {

	public static Address deserialize(String serializedAddress) {
		byte[] bytes = Base64.decode(serializedAddress);

		Deserializer deserializer = new Deserializer(ByteBuffer.wrap(bytes));

		try {
			return deserializer.readObject();
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(
				"Unable to deserialize address " + serializedAddress, cnfe);
		}
	}

	public static String serialize(Address address) {
		Serializer serializer = new Serializer();

		serializer.writeObject(address);

		ByteBuffer byteBuffer = serializer.toByteBuffer();

		return Base64.encode(byteBuffer.array());
	}

}