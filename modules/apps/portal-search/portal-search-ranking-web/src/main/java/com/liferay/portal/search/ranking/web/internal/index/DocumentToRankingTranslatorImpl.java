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

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.document.Document;

import java.text.DateFormat;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 */
@Component(service = DocumentToRankingTranslator.class)
public class DocumentToRankingTranslatorImpl
	implements DocumentToRankingTranslator {

	@Override
	public Ranking translate(Document document, String id) {
		return builder(
		).blocks(
			document.getStrings(SearchTuningFields.BLOCKS)
		).setDisplayDate(
			_getDate(document, Field.DISPLAY_DATE)
		).id(
			id
		).inactive(
			document.getBoolean(SearchTuningFields.INACTIVE)
		).index(
			document.getString("index")
		).setModifiedDate(
			_getDate(document, Field.MODIFIED_DATE)
		).name(
			_getName(document)
		).pins(
			_getPins(document)
		).queryStrings(
			_getQueryStrings(document)
		).build();
	}

	protected Ranking.RankingBuilder builder() {
		return new Ranking.RankingBuilder();
	}

	private static Date _getDate(Document document, String name) {
		try {
			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
				_INDEX_DATE_FORMAT_PATTERN);

			return dateFormat.parse(document.getDate(name));
		}
		catch (Exception e) {
			return null;
		}
	}

	private String _getName(Document document) {
		String string = document.getString(SearchTuningFields.NAME);

		if (Validator.isBlank(string)) {
			return document.getString(SearchTuningFields.QUERY_STRING);
		}

		return string;
	}

	private List<Ranking.Pin> _getPins(Document document) {
		List<?> values = document.getValues(SearchTuningFields.PINS);

		if (ListUtil.isEmpty(values)) {
			return Collections.emptyList();
		}

		List<Map<String, String>> maps = (List<Map<String, String>>)values;

		Stream<Map<String, String>> stream = maps.stream();

		Stream<Ranking.Pin> pinStream = stream.map(this::_toPin);

		return pinStream.collect(Collectors.toList());
	}

	private List<String> _getQueryStrings(Document document) {
		List<String> strings = document.getStrings(
			SearchTuningFields.QUERY_STRINGS);

		if (ListUtil.isEmpty(strings)) {
			return document.getStrings(SearchTuningFields.ALIASES);
		}

		return strings;
	}

	private Ranking.Pin _toPin(Map<String, String> map) {
		return new Ranking.Pin(
			GetterUtil.getInteger(map.get("position")), map.get("uid"));
	}

	private static final String _INDEX_DATE_FORMAT_PATTERN = "yyyyMMddHHmmss";

}