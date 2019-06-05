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
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Bryan Engler
 */
public class Ranking {

	public Ranking(Ranking ranking) {
		_blockIds = new LinkedHashSet<>(ranking._blockIds);
		_displayDate = ranking._displayDate;
		_id = ranking._id;
		_inactive = ranking._inactive;
		_index = ranking._index;
		_modifiedDate = ranking._modifiedDate;
		_name = ranking._name;
		_pinIds = new HashSet<>(ranking._pinIds);
		_pins = new ArrayList<>(ranking._pins);
		_queryStrings = new ArrayList<>(ranking._queryStrings);
	}

	public List<String> getBlockIds() {
		return new ArrayList<>(_blockIds);
	}

	@Deprecated
	public Date getDisplayDate() {
		return _displayDate;
	}

	public String getId() {
		return _id;
	}

	public String getIndex() {
		return _index;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public List<Pin> getPins() {
		return Collections.unmodifiableList(_pins);
	}

	public List<String> getQueryStrings() {
		return Collections.unmodifiableList(_queryStrings);
	}

	public int getStatus() {
		return _status;
	}

	public boolean isInactive() {
		return _inactive;
	}

	public boolean isPinned(String id) {
		return _pinIds.contains(id);
	}

	public static class Pin {

		public Pin(int position, String id) {
			_position = position;
			_id = id;
		}

		public String getId() {
			return _id;
		}

		public int getPosition() {
			return _position;
		}

		private final String _id;
		private final int _position;

	}

	public static class RankingBuilder {

		public RankingBuilder() {
			_ranking = new Ranking();
		}

		public RankingBuilder(Ranking ranking) {
			_ranking = ranking;
		}

		public RankingBuilder blocks(List<String> hiddenIds) {
			_ranking._blockIds = new LinkedHashSet<>(toList(hiddenIds));

			return this;
		}

		public Ranking build() {
			return new Ranking(_ranking);
		}

		public RankingBuilder id(String id) {
			_ranking._id = id;

			return this;
		}

		public RankingBuilder inactive(boolean inactive) {
			_ranking._inactive = inactive;

			return this;
		}

		public RankingBuilder index(String index) {
			_ranking._index = index;

			return this;
		}

		public RankingBuilder name(String name) {
			_ranking._name = name;

			return this;
		}

		public RankingBuilder pins(List<Pin> pins) {
			if (pins != null) {
				_ranking._pinIds = new LinkedHashSet<>(
					pins.stream(
					).map(
						Pin::getId
					).collect(
						Collectors.toSet()
					));

				_ranking._pins = pins;
			}
			else {
				_ranking._pinIds.clear();

				_ranking._pins.clear();
			}

			return this;
		}

		public RankingBuilder queryStrings(List<String> queryStrings) {
			_ranking._queryStrings = queryStrings;

			return this;
		}

		@Deprecated
		public RankingBuilder setDisplayDate(Date displayDate) {
			_ranking._displayDate = displayDate;

			return this;
		}

		@Deprecated
		public RankingBuilder setModifiedDate(Date modifiedDate) {
			_ranking._modifiedDate = modifiedDate;

			return this;
		}

		@Deprecated
		public RankingBuilder status(int status) {
			_ranking._status = status;

			return this;
		}

		protected static <T, V extends T> List<T> toList(List<V> list) {
			if (list != null) {
				return new ArrayList<>(list);
			}

			return new ArrayList<>();
		}

		private final Ranking _ranking;

	}

	private Ranking() {
	}

	private Set<String> _blockIds = new LinkedHashSet<>();

	@Deprecated
	private Date _displayDate;

	private String _id;
	private boolean _inactive;
	private String _index;

	@Deprecated
	private Date _modifiedDate;

	private String _name;
	private Set<String> _pinIds = new LinkedHashSet<>();
	private List<Pin> _pins = new ArrayList<>();
	private List<String> _queryStrings = new ArrayList<>();

	@Deprecated
	private int _status;

}