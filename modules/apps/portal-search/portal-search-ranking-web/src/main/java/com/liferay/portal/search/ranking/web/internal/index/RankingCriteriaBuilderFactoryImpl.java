/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.ranking.web.internal.index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 */
@Component(service = RankingCriteriaBuilderFactory.class)
public class RankingCriteriaBuilderFactoryImpl
	implements RankingCriteriaBuilderFactory {

	@Override
	public RankingCriteriaBuilder builder() {
		return new RankingCriteriaBuilderImpl();
	}

	public static class RankingCriteriaBuilderImpl
		implements RankingCriteriaBuilder {

		@Deprecated
		@Override
		public RankingCriteriaBuilder aliases(String... aliases) {
			_rankingCriteriaImpl._aliases.clear();

			Collections.addAll(_rankingCriteriaImpl._aliases, aliases);

			return this;
		}

		@Override
		public RankingCriteria build() {
			return new RankingCriteriaImpl(_rankingCriteriaImpl);
		}

		@Override
		public RankingCriteriaBuilder id(String id) {
			_rankingCriteriaImpl._id = id;

			return this;
		}

		@Override
		public RankingCriteriaBuilder index(String index) {
			_rankingCriteriaImpl._index = index;

			return this;
		}

		@Deprecated
		@Override
		public RankingCriteriaBuilder queryString(String queryString) {
			_rankingCriteriaImpl._queryString = queryString;

			return this;
		}

		private final RankingCriteriaImpl _rankingCriteriaImpl =
			new RankingCriteriaImpl();

	}

	public static class RankingCriteriaImpl implements RankingCriteria {

		public RankingCriteriaImpl() {
		}

		public RankingCriteriaImpl(RankingCriteriaImpl rankingCriteriaImpl) {
			_index = rankingCriteriaImpl._index;
			_id = rankingCriteriaImpl._id;
			_queryString = rankingCriteriaImpl._queryString;
			_aliases = new ArrayList<>(rankingCriteriaImpl._aliases);
		}

		@Deprecated
		@Override
		public List<String> getAliases() {
			return Collections.unmodifiableList(_aliases);
		}

		@Override
		public String getId() {
			return _id;
		}

		@Override
		public String getIndex() {
			return _index;
		}

		@Deprecated
		@Override
		public String getQueryString() {
			return _queryString;
		}

		private List _aliases = new ArrayList<>();
		private String _id;
		private String _index;
		private String _queryString;

	}

}