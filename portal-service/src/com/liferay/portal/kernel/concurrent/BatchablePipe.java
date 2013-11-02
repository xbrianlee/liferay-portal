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

package com.liferay.portal.kernel.concurrent;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Shuyang Zhou
 */
public class BatchablePipe<K, V> {

	public BatchablePipe() {
		_headEntry = new Entry<K, V>(null);
		_lastEntryReference = new AtomicReference<Entry<K, V>>(_headEntry);
	}

	public boolean put(IncreasableEntry<K, V> increasableEntry) {
		Entry<K, V> newEntry = new Entry<K, V>(increasableEntry);

		while (true) {
			if (_doIncrease(increasableEntry)) {
				return false;
			}

			Entry<K, V> lastEntryLink = _lastEntryReference.get();
			Entry<K, V> nextEntryLink = lastEntryLink._nextEntry.getReference();

			if (nextEntryLink == null) {
				if (lastEntryLink._nextEntry.compareAndSet(
						null, newEntry, false, false)) {

					_lastEntryReference.set(newEntry);

					return true;
				}
			}
			else {
				_lastEntryReference.compareAndSet(lastEntryLink, nextEntryLink);
			}
		}
	}

	public IncreasableEntry<K, V> take() {
		boolean[] marked = {false};

		take:
		while (true) {
			Entry<K, V> predecessorEntry = _headEntry;
			Entry<K, V> currentEntry =
				predecessorEntry._nextEntry.getReference();

			while (currentEntry != null) {
				Entry<K, V> successorEntry = currentEntry._nextEntry.get(
					marked);

				if (marked[0]) {
					if (!predecessorEntry._nextEntry.compareAndSet(
							currentEntry, successorEntry, false, false)) {

						continue take;
					}

					currentEntry = predecessorEntry._nextEntry.getReference();

					continue;
				}

				if (currentEntry._nextEntry.compareAndSet(
						successorEntry, successorEntry, false, true)) {

					return currentEntry._increasableEntry;
				}

				continue take;
			}

			return null;
		}
	}

	private boolean _doIncrease(IncreasableEntry<K, V> increasableEntry) {
		boolean[] marked = {false};

		retry:
		while (true) {
			Entry<K, V> predecessorEntry = _headEntry;
			Entry<K, V> currentEntry =
				predecessorEntry._nextEntry.getReference();

			while (currentEntry != null) {
				Entry<K, V> successorEntry = currentEntry._nextEntry.get(
					marked);

				if (marked[0]) {
					if (!predecessorEntry._nextEntry.compareAndSet(
							currentEntry, successorEntry, false, false)) {

						continue retry;
					}

					currentEntry = predecessorEntry._nextEntry.getReference();

					continue;
				}

				if (currentEntry._increasableEntry.getKey().equals(
						increasableEntry.getKey())) {

					return currentEntry._increasableEntry.increase(
						increasableEntry.getValue());
				}

				predecessorEntry = currentEntry;
				currentEntry = successorEntry;
			}

			_lastEntryReference.set(predecessorEntry);

			return false;
		}
	}

	private final Entry<K, V> _headEntry;
	private final AtomicReference<Entry<K, V>> _lastEntryReference;

	private static class Entry<K, V> {

		private Entry(IncreasableEntry<K, V> increasableEntry) {
			_increasableEntry = increasableEntry;
			_nextEntry = new AtomicMarkableReference<Entry<K, V>>(null, false);
		}

		private IncreasableEntry<K, V> _increasableEntry;
		private AtomicMarkableReference<Entry<K, V>> _nextEntry;

	}

}