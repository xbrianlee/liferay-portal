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

package com.liferay.portlet.wiki.engines.antlrwiki.translator.internal;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.parsers.creole.ast.BoldTextNode;
import com.liferay.portal.parsers.creole.ast.FormattedTextNode;
import com.liferay.portal.parsers.creole.ast.ItalicTextNode;
import com.liferay.portal.parsers.creole.ast.NoWikiSectionNode;
import com.liferay.portal.parsers.creole.ast.UnformattedTextNode;
import com.liferay.portal.parsers.creole.ast.link.LinkNode;
import com.liferay.portal.parsers.creole.visitor.impl.BaseASTVisitor;

/**
 * @author Miguel Pastor
 */
public abstract class UnformattedTextVisitor extends BaseASTVisitor {

	public String getText() {
		return _sb.toString();
	}

	@Override
	public void visit(BoldTextNode boldTextNode) {
		if (boldTextNode.getContent() != null) {
			write(boldTextNode.getContent());
		}
		else {
			super.visit(boldTextNode);
		}
	}

	@Override
	public void visit(FormattedTextNode formattedTextNode) {
		if (formattedTextNode.getContent() != null) {
			write(formattedTextNode.getContent());
		}
		else {
			super.visit(formattedTextNode);
		}
	}

	@Override
	public void visit(ItalicTextNode italicTextNode) {
		if (italicTextNode.getContent() != null) {
			write(italicTextNode.getContent());
		}
		else {
			super.visit(italicTextNode);
		}
	}

	@Override
	public void visit(LinkNode linkNode) {
		String link = linkNode.getLink();

		if (link != null) {
			write(link);
		}

		super.visit(linkNode);
	}

	@Override
	public void visit(NoWikiSectionNode noWikiSectionNode) {
		write(noWikiSectionNode.getContent());
	}

	@Override
	public void visit(UnformattedTextNode unformattedTextNode) {
		if (unformattedTextNode.hasContent()) {
			write(unformattedTextNode.getContent());
		}
		else {
			super.visit(unformattedTextNode);
		}
	}

	protected void write(String text) {
		_sb.append(text);
	}

	private StringBundler _sb = new StringBundler();

}