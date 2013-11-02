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

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.portal.kernel.nio.intraband.CompletionHandler.CompletionType;

import java.io.IOException;

import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

import java.util.EnumSet;

/**
 * @author Shuyang Zhou
 */
public class DatagramHelper {

	public static Datagram createACKResponseDatagram(long sequenceId) {
		return Datagram.createACKResponseDatagram(sequenceId);
	}

	public static Datagram createReceiveDatagram() {
		return Datagram.createReceiveDatagram();
	}

	public static Object getAttachment(Datagram datagram) {
		return datagram.attachment;
	}

	public static CompletionHandler<Object> getCompletionHandler(
		Datagram datagram) {

		return datagram.completionHandler;
	}

	public static EnumSet<CompletionType> getCompletionTypes(
		Datagram datagram) {

		return datagram.completionTypes;
	}

	public static long getSequenceId(Datagram datagram) {
		return datagram.getSequenceId();
	}

	public static boolean isAckResponse(Datagram datagram) {
		return datagram.isAckResponse();
	}

	public static boolean readFrom(
			Datagram datagram, ScatteringByteChannel scatteringByteChannel)
		throws IOException {

		return datagram.readFrom(scatteringByteChannel);
	}

	public static void setAckRequest(Datagram datagram) {
		datagram.setAckRequest(true);
	}

	public static void setAttachment(Datagram datagram, Object attachment) {
		datagram.attachment = attachment;
	}

	public static void setCompletionHandler(
		Datagram datagram, CompletionHandler<Object> completionHandler) {

		datagram.completionHandler = completionHandler;
	}

	public static void setCompletionTypes(
		Datagram datagram, EnumSet<CompletionType> completionTypes) {

		datagram.completionTypes = completionTypes;
	}

	public static void setSequenceId(Datagram datagram, long sequenceId) {
		datagram.setSequenceId(sequenceId);
	}

	public static void setTimeout(Datagram datagram, long timeout) {
		datagram.timeout = timeout;
	}

	public static void writeTo(
			Datagram datagram, GatheringByteChannel gatheringByteChannel)
		throws IOException {

		while (!datagram.writeTo(gatheringByteChannel));
	}

}