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

package com.liferay.portlet.wiki.engines.antlrwiki;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.parsers.creole.ast.ASTNode;
import com.liferay.portal.parsers.creole.ast.WikiPageNode;
import com.liferay.portal.parsers.creole.ast.link.LinkNode;
import com.liferay.portal.parsers.creole.parser.Creole10Lexer;
import com.liferay.portal.parsers.creole.parser.Creole10Parser;
import com.liferay.portal.parsers.creole.visitor.impl.LinkNodeCollectorVisitor;
import com.liferay.portlet.wiki.PageContentException;
import com.liferay.portlet.wiki.engines.WikiEngine;
import com.liferay.portlet.wiki.engines.antlrwiki.translator.XhtmlTranslator;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

/**
 * @author Miguel Pastor
 */
public class CreoleWikiEngine implements WikiEngine {

	@Override
	public String convert(
		WikiPage page, PortletURL viewPageURL, PortletURL editPageURL,
		String attachmentURLPrefix) {

		XhtmlTranslator xhtmlTranslator = new XhtmlTranslator();

		return xhtmlTranslator.translate(
			page, viewPageURL, editPageURL, attachmentURLPrefix,
			parse(page.getContent()));
	}

	@Override
	public Map<String, Boolean> getOutgoingLinks(WikiPage page)
		throws PageContentException {

		Map<String, Boolean> outgoingLinks = new HashMap<String, Boolean>();

		LinkNodeCollectorVisitor linkNodeCollectorVisitor =
			new LinkNodeCollectorVisitor();

		List<ASTNode> astNodes = linkNodeCollectorVisitor.collect(
			parse(page.getContent()));

		try {
			for (ASTNode astNode : astNodes) {
				LinkNode linkNode = (LinkNode)astNode;

				String title = linkNode.getLink();

				boolean existingLink = false;

				if (WikiPageLocalServiceUtil.getPagesCount(
						page.getNodeId(), title, true) > 0) {

					existingLink = true;
				}

				if (existingLink) {
					title = StringUtil.toLowerCase(title);
				}

				outgoingLinks.put(title, existingLink);
			}
		}
		catch (SystemException se) {
			throw new PageContentException(se);
		}

		return outgoingLinks;
	}

	@Override
	public void setInterWikiConfiguration(String interWikiConfiguration) {
	}

	@Override
	public void setMainConfiguration(String mainConfiguration) {
	}

	@Override
	public boolean validate(long nodeId, String content) {
		return true;
	}

	protected Creole10Parser build(String creoleCode) {
		ANTLRStringStream antlrStringStream = new ANTLRStringStream(creoleCode);

		Creole10Lexer creole10Lexer = new Creole10Lexer(antlrStringStream);

		CommonTokenStream commonTokenStream = new CommonTokenStream(
			creole10Lexer);

		return new Creole10Parser(commonTokenStream);
	}

	protected WikiPageNode parse(String creoleCode) {
		Creole10Parser creole10Parser = build(creoleCode);

		try {
			creole10Parser.wikipage();
		}
		catch (RecognitionException re) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to parse:\n" + creoleCode, re);

				for (String error : creole10Parser.getErrors()) {
					_log.debug(error);
				}
			}
		}

		return creole10Parser.getWikiPageNode();
	}

	private static Log _log = LogFactoryUtil.getLog(CreoleWikiEngine.class);

}