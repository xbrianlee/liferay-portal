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

package com.liferay.portlet.wiki.translators;

/**
 * @author Jorge Ferrer
 */
public class ClassicToCreoleTranslator extends BaseTranslator {

	public ClassicToCreoleTranslator() {
		initRegexps();
	}

	protected void initRegexps() {

		// Bold and italics

		regexps.put(
			"'''''((?s:.)*?)('''''|(\n\n|\r\r|\r\n\r\n))", "**//$1//**$3");

		// Bold

		regexps.put("'''((?s:.)*?)('''|(\n\n|\r\r|\r\n\r\n))", "**$1**$3");

		// Italics

		regexps.put("''((?s:.)*?)(''|(\n\n|\r\r|\r\n\r\n))", "//$1//$3");

		// Link

		regexps.put("\\[([^ ]*)\\]", "[[$1]]");

		// Link with label

		regexps.put("\\[([^ ]+) (.*)\\]", "[[$1|$2]]");

		// Monospace

		regexps.put("(^ (.+))(\\n (.+))*", "{{{\n$0\n}}}");

		// List item

		regexps.put("^\\t[\\*] (.*)", "* $1");

		// List subitem

		regexps.put("^\\t\\t[\\*] (.*)", "** $1");

		// List subsubitem

		regexps.put("^\\t\\t\\t[\\*] (.*)", "*** $1");

		// List subsubsubitem

		regexps.put("^\\t\\t\\t\\t[\\*] (.*)", "**** $1");

		// Ordered list item

		regexps.put("^\\t1 (.*)", "# $1");

		// Ordered list subitem

		regexps.put("^\\t\\t1 (.*)", "## $1");

		// Ordered list subsubitem

		regexps.put("^\\t\\t\\t1 (.*)", "### $1");

		// Ordered list subsubsubitem

		regexps.put("^\\t\\t\\t\\t1 (.*)", "#### $1");

		// Term and definition

		regexps.put("^\\t([\\w]+):\\t(.*)", "**$1**:\n$2");

		// Indented paragraph

		regexps.put("^\\t:\\t(.*)", "$1");

		// CamelCase

		regexps.put(
			"(^|\\p{Punct}|\\p{Space})((\\p{Lu}\\p{Ll}+){2,})" +
				"(\\z|\\n|\\p{Punct}|\\p{Space})", " [[$2]] ");
	}

}