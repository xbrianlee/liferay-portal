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

package com.liferay.portal.kernel.atom;

import java.io.InputStream;

import java.util.Date;

/**
 * @author Igor Spasic
 */
public abstract class BaseAtomCollectionAdapter<E>
	implements AtomCollectionAdapter<E> {

	@Override
	public void deleteEntry(
			String resourceName, AtomRequestContext atomRequestContext)
		throws AtomException {

		try {
			doDeleteEntry(resourceName, atomRequestContext);
		}
		catch (Exception e) {
			Class<?> clazz = e.getClass();

			String className = clazz.getSimpleName();

			if (className.startsWith("NoSuch")) {
				throw new AtomException(SC_NOT_FOUND);
			}

			throw new AtomException(SC_INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public E getEntry(
			String resourceName, AtomRequestContext atomRequestContext)
		throws AtomException {

		try {
			return doGetEntry(resourceName, atomRequestContext);
		}
		catch (Exception e) {
			Class<?> clazz = e.getClass();

			String className = clazz.getSimpleName();

			if (className.startsWith("NoSuch")) {
				throw new AtomException(SC_NOT_FOUND);
			}

			throw new AtomException(SC_INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public Iterable<E> getFeedEntries(AtomRequestContext atomRequestContext)
		throws AtomException {

		try {
			return doGetFeedEntries(atomRequestContext);
		}
		catch (Exception e) {
			Class<?> clazz = e.getClass();

			String className = clazz.getSimpleName();

			if (className.startsWith("NoSuch")) {
				throw new AtomException(SC_NOT_FOUND);
			}

			throw new AtomException(SC_INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public String getMediaContentType(E entry) {
		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public String getMediaName(E entry) throws AtomException {
		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unused")
	public InputStream getMediaStream(E entry) throws AtomException {
		throw new UnsupportedOperationException();
	}

	@Override
	public E postEntry(
			String title, String summary, String content, Date date,
			AtomRequestContext atomRequestContext)
		throws AtomException {

		try {
			return doPostEntry(
				title, summary, content, date, atomRequestContext);
		}
		catch (Exception e) {
			Class<?> clazz = e.getClass();

			String className = clazz.getSimpleName();

			if (className.startsWith("NoSuch")) {
				throw new AtomException(SC_NOT_FOUND);
			}

			throw new AtomException(SC_INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public E postMedia(
			String mimeType, String slug, InputStream inputStream,
			AtomRequestContext atomRequestContext)
		throws AtomException {

		try {
			return doPostMedia(mimeType, slug, inputStream, atomRequestContext);
		}
		catch (Exception e) {
			Class<?> clazz = e.getClass();

			String className = clazz.getSimpleName();

			if (className.startsWith("NoSuch")) {
				throw new AtomException(SC_NOT_FOUND);
			}

			throw new AtomException(SC_INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public void putEntry(
			E entry, String title, String summary, String content, Date date,
			AtomRequestContext atomRequestContext)
		throws AtomException {

		try {
			doPutEntry(
				entry, title, summary, content, date, atomRequestContext);
		}
		catch (Exception e) {
			Class<?> clazz = e.getClass();

			String className = clazz.getSimpleName();

			if (className.startsWith("NoSuch")) {
				throw new AtomException(SC_NOT_FOUND);
			}

			throw new AtomException(SC_INTERNAL_SERVER_ERROR, e);
		}
	}

	@Override
	public void putMedia(
			E entry, String mimeType, String slug, InputStream inputStream,
			AtomRequestContext atomRequestContext)
		throws AtomException {

		try {
			doPutMedia(entry, mimeType, slug, inputStream, atomRequestContext);
		}
		catch (Exception e) {
			Class<?> clazz = e.getClass();

			String className = clazz.getSimpleName();

			if (className.startsWith("NoSuch")) {
				throw new AtomException(SC_NOT_FOUND);
			}

			throw new AtomException(SC_INTERNAL_SERVER_ERROR, e);
		}
	}

	protected void doDeleteEntry(
			String resourceName, AtomRequestContext atomRequestContext)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	protected abstract E doGetEntry(
			String resourceName, AtomRequestContext atomRequestContext)
		throws Exception;

	protected abstract Iterable<E> doGetFeedEntries(
			AtomRequestContext atomRequestContext)
		throws Exception;

	protected E doPostEntry(
			String title, String summary, String content, Date date,
			AtomRequestContext atomRequestContext)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	protected E doPostMedia(
			String mimeType, String slug, InputStream inputStream,
			AtomRequestContext atomRequestContext)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	protected void doPutEntry(
			E entry, String title, String summary, String content, Date date,
			AtomRequestContext atomRequestContext)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	protected void doPutMedia(
			E entry, String mimeType, String slug, InputStream inputStream,
			AtomRequestContext atomRequestContext)
		throws Exception {

		throw new UnsupportedOperationException();
	}

}