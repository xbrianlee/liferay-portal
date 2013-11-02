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

package com.liferay.support.tomcat.jasper.runtime;

import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletConfig;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.jasper.Constants;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-19130.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class TagHandlerPool extends org.apache.jasper.runtime.TagHandlerPool {

	@Override
	@SuppressWarnings("rawtypes")
	public Tag get(Class tagClass) throws JspException {
		Tag tag = _tags.poll();

		if (tag == null) {
			try {
				tag = (Tag)tagClass.newInstance();
			}
			catch (Exception e) {
				throw new JspException(e);
			}
		}

		return tag;
	}

	@Override
	public void release() {
		Tag tag = null;

		while ((tag = _tags.poll()) != null) {
			tag.release();
		}
	}

	@Override
	public void reuse(Tag tag) {
		if (_counter.get() < _maxSize) {
			_counter.getAndIncrement();

			_tags.offer(tag);
		}
		else {
			tag.release();
		}
	}

	@Override
	protected void init(ServletConfig config) {
		_maxSize = GetterUtil.getInteger(
			getOption(config, OPTION_MAXSIZE, null), Constants.MAX_POOL_SIZE);
	}

	private AtomicInteger _counter = new AtomicInteger();
	private int _maxSize;
	private Queue<Tag> _tags = new ConcurrentLinkedQueue<Tag>();

}