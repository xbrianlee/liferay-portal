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

/**
 * @author Igor Spasic
 */
public abstract class BaseMediaAtomCollectionAdapter<E>
	extends BaseAtomCollectionAdapter<E> {

	@Override
	public abstract String getMediaContentType(E entry);

	@Override
	public abstract String getMediaName(E entry) throws AtomException;

	@Override
	public abstract InputStream getMediaStream(E entry) throws AtomException;

	@Override
	protected abstract E doPostMedia(
			String mimeType, String slug, InputStream inputStream,
			AtomRequestContext atomRequestContext)
		throws Exception;

	@Override
	protected abstract void doPutMedia(
			E entry, String mimeType, String slug, InputStream inputStream,
			AtomRequestContext atomRequestContext)
		throws Exception;

}