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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Company;

import java.util.Date;
import java.util.List;

import org.apache.abdera.factory.Factory;
import org.apache.abdera.i18n.iri.IRI;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;
import org.apache.abdera.model.Text;
import org.apache.abdera.protocol.server.RequestContext;
import org.apache.abdera.protocol.server.TargetType;
import org.apache.abdera.protocol.server.context.ResponseContextException;
import org.apache.abdera.protocol.server.impl.AbstractEntityCollectionAdapter;

/**
 * @author Igor Spasic
 */
public abstract class BaseEntityCollectionAdapter<T>
	extends AbstractEntityCollectionAdapter<T> {

	@Override
	public String getAuthor(RequestContext requestContext) {
		String author = null;

		try {
			Company company = AtomUtil.getCompany();

			author = company.getName();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return author;
	}

	@Override
	public String getHref(RequestContext requestContext) {
		return requestContext.urlFor(
			TargetType.TYPE_COLLECTION, collectionName);
	}

	@Override
	public String getId(RequestContext requestContext) {
		String id = AtomUtil.createIdTagPrefix(collectionName);

		id = id.concat("feed");

		return id;
	}

	@Override
	public String getId(T entry) {
		String id = AtomUtil.createIdTagPrefix(collectionName);

		id = id.concat("entry:");
		id = id.concat(getName(entry));

		return id;
	}

	@Override
	public String getName(T entry) {
		return getEntryId(entry);
	}

	protected BaseEntityCollectionAdapter(String collectionName) {
		this.collectionName = collectionName;
	}

	@Override
	protected String addEntryDetails(
			RequestContext requestContext, Entry entry, IRI feedIri, T entryObj)
		throws ResponseContextException {

		String link = getLink(entryObj, feedIri, requestContext);

		entry.addLink(link);

		List<Person> authors = getAuthors(entryObj, requestContext);

		if (authors != null) {
			for (Person author : authors) {
				entry.addAuthor(author);
			}
		}

		entry.setId(getId(entryObj));

		Text text = getSummary(entryObj, requestContext);

		if (text != null) {
			entry.setSummaryElement(text);
		}

		entry.setTitle(getTitle(entryObj));
		entry.setUpdated(getUpdated(entryObj));

		return link;
	}

	@Override
	protected void addFeedDetails(Feed feed, RequestContext requestContext)
		throws ResponseContextException {

		super.addFeedDetails(feed, requestContext);

		AtomPager atomPager = AtomUtil.getPager(requestContext);

		if (atomPager != null) {
			String url = String.valueOf(requestContext.getResolvedUri());

			atomPager.setFeedPagingLinks(feed, url);
		}
	}

	@Override
	protected Feed createFeedBase(RequestContext requestContext) {
		Factory factory = requestContext.getAbdera().getFactory();

		Feed feed = factory.newFeed();

		feed.addAuthor(getAuthor(requestContext));

		String url = String.valueOf(requestContext.getResolvedUri());

		url = AtomUtil.resolveCollectionUrl(url, collectionName);

		feed.addLink(url);
		feed.addLink(url, "self");

		feed.setId(getId(requestContext));
		feed.setTitle(getTitle(requestContext));
		feed.setUpdated(new Date());

		return feed;
	}

	protected abstract String getEntryId(T entry);

	protected String collectionName;

	private static Log _log = LogFactoryUtil.getLog(
		BaseEntityCollectionAdapter.class);

}