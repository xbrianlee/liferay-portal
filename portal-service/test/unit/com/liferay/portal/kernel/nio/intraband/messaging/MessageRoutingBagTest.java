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

package com.liferay.portal.kernel.nio.intraband.messaging;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.test.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.ReflectionUtil;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Field;

import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class MessageRoutingBagTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testAutomaticSerialization() throws Exception {
		Message message = new Message();

		String destinationName = "destinationName";

		message.setDestinationName(destinationName);

		boolean synchronizedBridge = true;

		MessageRoutingBag messageRoutingBag = new MessageRoutingBag(
			message, synchronizedBridge);

		String routingId1 = "routingId1";

		messageRoutingBag.appendRoutingId(routingId1);

		String routingId2 = "routingId2";

		messageRoutingBag.appendRoutingId(routingId2);

		boolean routingDowncast = true;

		messageRoutingBag.setRoutingDowncast(routingDowncast);

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
			unsyncByteArrayOutputStream);

		objectOutputStream.writeObject(messageRoutingBag);

		objectOutputStream.close();

		byte[] bytes = unsyncByteArrayOutputStream.toByteArray();

		ObjectInputStream objectInputStream = new ObjectInputStream(
			new UnsyncByteArrayInputStream(bytes));

		MessageRoutingBag newMessageRoutingBag =
			(MessageRoutingBag)objectInputStream.readObject();

		Assert.assertEquals(
			destinationName, newMessageRoutingBag.getDestinationName());

		Field messageField = ReflectionUtil.getDeclaredField(
			MessageRoutingBag.class, "_message");

		Assert.assertNull(messageField.get(newMessageRoutingBag));
		Assert.assertNotNull(newMessageRoutingBag.getMessageData());
		Assert.assertEquals(
			routingDowncast, newMessageRoutingBag.isRoutingDowncast());

		Field routingTraceField = ReflectionUtil.getDeclaredField(
			MessageRoutingBag.class, "_routingTrace");

		List<String> routingTrace = (List<String>)routingTraceField.get(
			newMessageRoutingBag);

		Assert.assertEquals(2, routingTrace.size());
		Assert.assertEquals(routingId1, routingTrace.get(0));
		Assert.assertEquals(routingId2, routingTrace.get(1));
		Assert.assertTrue(newMessageRoutingBag.isVisited(routingId1));
		Assert.assertTrue(newMessageRoutingBag.isVisited(routingId2));
		Assert.assertFalse(newMessageRoutingBag.isVisited("routingId3"));

		Assert.assertEquals(
			synchronizedBridge, newMessageRoutingBag.isSynchronizedBridge());

		Message newMessage = newMessageRoutingBag.getMessage();

		Assert.assertNotNull(newMessage);

		Field messageDataField = ReflectionUtil.getDeclaredField(
			MessageRoutingBag.class, "_messageData");

		Assert.assertNull(messageDataField.get(newMessageRoutingBag));
		Assert.assertSame(newMessage, newMessageRoutingBag.getMessage());
	}

	@Test
	public void testManualSerialization() throws Exception {
		Message message = new Message();

		String destinationName = "destinationName";

		message.setDestinationName(destinationName);

		boolean synchronizedBridge = false;

		MessageRoutingBag messageRoutingBag = new MessageRoutingBag(
			message, synchronizedBridge);

		String routingId1 = "routingId1";

		messageRoutingBag.appendRoutingId(routingId1);

		String routingId2 = "routingId2";

		messageRoutingBag.appendRoutingId(routingId2);

		boolean routingDowncast = true;

		messageRoutingBag.setRoutingDowncast(routingDowncast);

		byte[] bytes = messageRoutingBag.toByteArray();

		MessageRoutingBag newMessageRoutingBag =
			MessageRoutingBag.fromByteArray(bytes);

		Assert.assertEquals(
			destinationName, newMessageRoutingBag.getDestinationName());

		Field messageField = ReflectionUtil.getDeclaredField(
			MessageRoutingBag.class, "_message");

		Assert.assertNull(messageField.get(newMessageRoutingBag));
		Assert.assertNotNull(newMessageRoutingBag.getMessageData());
		Assert.assertEquals(
			routingDowncast, newMessageRoutingBag.isRoutingDowncast());

		Field routingTraceField = ReflectionUtil.getDeclaredField(
			MessageRoutingBag.class, "_routingTrace");

		List<String> routingTrace = (List<String>)routingTraceField.get(
			newMessageRoutingBag);

		Assert.assertEquals(2, routingTrace.size());
		Assert.assertEquals(routingId1, routingTrace.get(0));
		Assert.assertEquals(routingId2, routingTrace.get(1));
		Assert.assertTrue(newMessageRoutingBag.isVisited(routingId1));
		Assert.assertTrue(newMessageRoutingBag.isVisited(routingId2));
		Assert.assertFalse(newMessageRoutingBag.isVisited("routingId3"));

		Assert.assertEquals(
			synchronizedBridge, newMessageRoutingBag.isSynchronizedBridge());

		Message newMessage = newMessageRoutingBag.getMessage();

		Assert.assertNotNull(newMessage);

		Field messageDataField = ReflectionUtil.getDeclaredField(
			MessageRoutingBag.class, "_messageData");

		Assert.assertNull(messageDataField.get(newMessageRoutingBag));
		Assert.assertSame(newMessage, newMessageRoutingBag.getMessage());
	}

	@Test
	public void testMessageAssociation() throws Exception {
		Message message = new Message();

		MessageRoutingBag messageRoutingBag = new MessageRoutingBag(
			message, true);

		Message newMessage = new Message();

		messageRoutingBag.setMessage(newMessage);

		Field messageField = ReflectionUtil.getDeclaredField(
			MessageRoutingBag.class, "_message");

		Assert.assertSame(newMessage, messageField.get(messageRoutingBag));
		Assert.assertSame(
			messageRoutingBag,
			newMessage.get(MessageRoutingBag.MESSAGE_ROUTING_BAG));
	}

	@Test
	public void testUnserializableMessage() {
		Message message = new Message();

		message.setPayload(new Object());

		MessageRoutingBag messageRoutingBag = new MessageRoutingBag(
			message, true);

		try {
			messageRoutingBag.getMessageData();

			Assert.fail();
		}
		catch (RuntimeException re) {
			message.remove(MessageRoutingBag.MESSAGE_ROUTING_BAG);

			Assert.assertEquals(
				"Unable to write ordinary serializable object " + message,
				re.getMessage());
		}
	}

}