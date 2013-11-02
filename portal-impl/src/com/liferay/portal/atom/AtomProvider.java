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

package com.liferay.portal.atom;

import com.liferay.portal.kernel.atom.AtomCollectionAdapter;

import java.util.Collection;

import org.apache.abdera.protocol.server.CollectionAdapter;
import org.apache.abdera.protocol.server.CollectionInfo;
import org.apache.abdera.protocol.server.RequestContext;
import org.apache.abdera.protocol.server.TargetType;
import org.apache.abdera.protocol.server.WorkspaceInfo;
import org.apache.abdera.protocol.server.impl.AbstractWorkspaceProvider;
import org.apache.abdera.protocol.server.impl.RegexTargetResolver;
import org.apache.abdera.protocol.server.impl.SimpleWorkspaceInfo;

/**
 * @author Igor Spasic
 */
public class AtomProvider extends AbstractWorkspaceProvider {

	public AtomProvider() {
		_initWorkspace();
		_initTargetResolver();
		_initTargetBuilder();
	}

	public <E> void addCollection(
		AtomCollectionAdapter<E> atomCollectionAdapter) {

		_workspace.addCollection(
			new AtomCollectionAdapterWrapper<E>(atomCollectionAdapter));
	}

	@Override
	public CollectionAdapter getCollectionAdapter(RequestContext request) {
		String path = request.getTargetPath();

		int index = path.indexOf('?');

		if (index != -1) {
			path = path.substring(0, index);
		}

		String baseUri = request.getBaseUri().toString();

		for (WorkspaceInfo workspaceInfo : workspaces) {
			Collection<CollectionInfo> collections =
				workspaceInfo.getCollections(request);

			for (CollectionInfo collectionInfo : collections) {
				String href = collectionInfo.getHref(request);

				if (href == null) {
					continue;
				}

				if (href.startsWith(baseUri)) {
					href = href.substring(baseUri.length() - 1);
				}

				index = href.indexOf('?');

				if (index != -1) {
					href = href.substring(0, index);
				}

				if (path.startsWith(href)) {
					return (CollectionAdapter)collectionInfo;
				}
			}
		}

		return null;
	}

	private void _initTargetBuilder() {
		setTargetBuilder(new AtomTargetBuilder());
	}

	private void _initTargetResolver() {
		RegexTargetResolver targetResolver = new RegexTargetResolver();

		targetResolver.setPattern(
			_COLLECTION_ENTRY_REGEXP, TargetType.TYPE_ENTRY, "collection",
			"entry");
		targetResolver.setPattern(
			_COLLECTION_MEDIA_REGEXP, TargetType.TYPE_MEDIA, "collection",
			"media");
		targetResolver.setPattern(
			_COLLECTION_REGEXP, TargetType.TYPE_COLLECTION, "collection");
		targetResolver.setPattern(_SERVICE_REGEXP, TargetType.TYPE_SERVICE);

		setTargetResolver(targetResolver);
	}

	private void _initWorkspace() {
		_workspace = new SimpleWorkspaceInfo();

		_workspace.setTitle("Liferay Workspace");

		addWorkspace(_workspace);
	}

	private static final String _COLLECTION_ENTRY_REGEXP =
		"/api/atom/([^/#?]+)/([^/#?:]+)(\\?[^#]*)?";

	private static final String _COLLECTION_MEDIA_REGEXP =
		"/api/atom/([^/#?]+)/([^/#?]+):media(\\?[^#]*)?";

	private static final String _COLLECTION_REGEXP =
		"/api/atom/([^/#?;]+)(\\?[^#]*)?";

	private static final String _SERVICE_REGEXP = "/api/atom?(\\?[^#]*)?";

	private SimpleWorkspaceInfo _workspace;

}