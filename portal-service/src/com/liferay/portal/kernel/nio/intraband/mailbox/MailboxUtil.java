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

package com.liferay.portal.kernel.nio.intraband.mailbox;

import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.SystemDataType;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.nio.ByteBuffer;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Shuyang Zhou
 */
public class MailboxUtil {

	public static ByteBuffer receiveMail(long receipt) {
		ByteBuffer byteBuffer = _mailMap.remove(receipt);

		_overdueMailQueue.remove(new ReceiptStub(receipt));

		if (!_INTRABAND_MAILBOX_REAPER_THREAD_ENABLED) {
			_pollingCleanup();
		}

		return byteBuffer;
	}

	public static long sendMail(
			RegistrationReference registrationReference, ByteBuffer byteBuffer)
		throws MailboxException {

		Intraband intraband = registrationReference.getIntraband();

		try {
			SystemDataType systemDataType = SystemDataType.MAILBOX;

			Datagram responseDatagram = intraband.sendSyncDatagram(
				registrationReference,
				Datagram.createRequestDatagram(
					systemDataType.getValue(), byteBuffer));

			byteBuffer = responseDatagram.getDataByteBuffer();

			return byteBuffer.getLong();
		}
		catch (Exception e) {
			throw new MailboxException(e);
		}
	}

	static long depositMail(ByteBuffer byteBuffer) {
		long receipt = _receiptGenerator.getAndIncrement();

		_mailMap.put(receipt, byteBuffer);

		_overdueMailQueue.offer(new ReceiptStub(receipt, System.nanoTime()));

		if (!_INTRABAND_MAILBOX_REAPER_THREAD_ENABLED) {
			_pollingCleanup();
		}

		return receipt;
	}

	private static void _pollingCleanup() {
		ReceiptStub receiptStub = null;

		while ((receiptStub = _overdueMailQueue.poll()) != null) {
			_mailMap.remove(receiptStub.getReceipt());
		}
	}

	private static final boolean _INTRABAND_MAILBOX_REAPER_THREAD_ENABLED =
		GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.INTRABAND_MAILBOX_REAPER_THREAD_ENABLED));

	private static final long _INTRABAND_MAILBOX_STORAGE_LIFE =
		GetterUtil.getLong(
			PropsUtil.get(PropsKeys.INTRABAND_MAILBOX_STORAGE_LIFE));

	private final static Map<Long, ByteBuffer> _mailMap =
		new ConcurrentHashMap<Long, ByteBuffer>();
	private final static BlockingQueue<ReceiptStub> _overdueMailQueue =
		new DelayQueue<ReceiptStub>();
	private final static AtomicLong _receiptGenerator = new AtomicLong();

	private static class OverdueMailReaperThread extends Thread {

		public OverdueMailReaperThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			while (true) {
				try {
					ReceiptStub receiptStub = _overdueMailQueue.take();

					_mailMap.remove(receiptStub.getReceipt());
				}
				catch (InterruptedException ie) {
				}
			}
		}
	}

	private static class ReceiptStub implements Delayed {

		public ReceiptStub(long receipt) {
			this(receipt, -1);
		}

		public ReceiptStub(long receipt, long currentNanoTime) {
			_expireTime = currentNanoTime + TimeUnit.MILLISECONDS.toNanos(
				_INTRABAND_MAILBOX_STORAGE_LIFE);
			_receipt = receipt;
		}

		@Override
		public int compareTo(Delayed delayed) {
			ReceiptStub receiptStub = (ReceiptStub)delayed;

			return (int)(_expireTime - receiptStub._expireTime);
		}

		@Override
		public boolean equals(Object obj) {
			ReceiptStub receiptStub = (ReceiptStub)obj;

			return _receipt == receiptStub._receipt;
		}

		public long getReceipt() {
			return _receipt;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return _expireTime - System.nanoTime();
		}

		private final long _expireTime;
		private final long _receipt;

	}

	static {
		if (_INTRABAND_MAILBOX_REAPER_THREAD_ENABLED) {
			Thread thread = new OverdueMailReaperThread(
				MailboxUtil.class.getName());

			thread.setContextClassLoader(MailboxUtil.class.getClassLoader());
			thread.setDaemon(true);

			thread.start();
		}
	}

}