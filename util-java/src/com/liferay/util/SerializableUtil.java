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

package com.liferay.util;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.StreamUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Alexander Chow
 * @author Igor Spasic
 */
public class SerializableUtil {

	public static Object clone(Object object) {
		return deserialize(serialize(object));
	}

	public static Object deserialize(byte[] bytes) {
		ObjectInputStream objectInputStream = null;

		try {
			objectInputStream = new ObjectInputStream(
				new UnsyncByteArrayInputStream(bytes));

			return objectInputStream.readObject();
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		finally {
			StreamUtil.cleanUp(objectInputStream);
		}
	}

	public static Object deserialize(byte[] bytes, ClassLoader classLoader) {
		ObjectInputStream objectInputStream = null;

		try {
			objectInputStream = new ClassLoaderObjectInputStream(
				new UnsyncByteArrayInputStream(bytes), classLoader);

			return objectInputStream.readObject();
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		finally {
			StreamUtil.cleanUp(objectInputStream);
		}
	}

	public static byte[] serialize(Object object) {
		ObjectOutputStream objectOutputStream = null;

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		try {
			objectOutputStream = new ObjectOutputStream(
				unsyncByteArrayOutputStream);

			objectOutputStream.writeObject(object);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		finally {
			StreamUtil.cleanUp(objectOutputStream);
		}

		return unsyncByteArrayOutputStream.toByteArray();
	}

}