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

package com.liferay.util;

import java.io.IOException;
import java.io.Reader;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

/**
 * @author Brian Wing Shun Chan
 */
public class HTMLParser {

	public HTMLParser(Reader reader) throws IOException {
		HTMLEditorKit.Parser parser = new DefaultParser().getParser();

		parser.parse(reader, new HTMLCallback(), true);
	}

	public List<String> getImages() {
		return _images;
	}

	public List<String> getLinks() {
		return _links;
	}

	private List<String> _images = new ArrayList<String>();
	private List<String> _links = new ArrayList<String>();

	private class DefaultParser extends HTMLEditorKit {

		@Override
		public HTMLEditorKit.Parser getParser() {
			return super.getParser();
		}

	}

	private class HTMLCallback extends HTMLEditorKit.ParserCallback {

		@Override
		public void handleText(char[] data, int pos) {
		}

		@Override
		public void handleStartTag(
			HTML.Tag tag, MutableAttributeSet attributes, int pos) {

			if (tag.equals(HTML.Tag.A)) {
				String href = (String)attributes.getAttribute(
					HTML.Attribute.HREF);

				if (href != null) {
					_links.add(href);
				}
			}
			else if (tag.equals(HTML.Tag.IMG)) {
				String src = (String)attributes.getAttribute(
					HTML.Attribute.SRC);

				if (src != null) {
					_images.add(src);
				}
			}
		}

		@Override
		public void handleEndTag(HTML.Tag tag, int pos) {
		}

		@Override
		public void handleSimpleTag(
			HTML.Tag tag, MutableAttributeSet attributes, int pos) {

			if (tag.equals(HTML.Tag.A)) {
				String href = (String)attributes.getAttribute(
					HTML.Attribute.HREF);

				if (href != null) {
					_links.add(href);
				}
			}
			else if (tag.equals(HTML.Tag.IMG)) {
				String src = (String)attributes.getAttribute(
					HTML.Attribute.SRC);

				if (src != null) {
					_images.add(src);
				}
			}
		}

		@Override
		public void handleComment(char[] data, int pos) {
		}

		@Override
		public void handleError(String errorMsg, int pos) {
		}

	}

}