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

package com.liferay.portalweb.portal.util.liferayselenium;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Brian Wing Shun Chan
 */
public class WebDriverWrapper implements WebDriver {

	public WebDriverWrapper(WebDriver webDriver) {
		_webDriver = webDriver;
	}

	@Override
	public void close() {
		_webDriver.close();
	}

	@Override
	public WebElement findElement(By by) {
		return _webDriver.findElement(by);
	}

	@Override
	public List<WebElement> findElements(By by) {
		return _webDriver.findElements(by);
	}

	@Override
	public void get(String url) {
		_webDriver.get(url);
	}

	@Override
	public String getCurrentUrl() {
		return _webDriver.getCurrentUrl();
	}

	@Override
	public String getPageSource() {
		return _webDriver.getPageSource();
	}

	@Override
	public String getTitle() {
		return _webDriver.getTitle();
	}

	@Override
	public String getWindowHandle() {
		return _webDriver.getWindowHandle();
	}

	@Override
	public Set<String> getWindowHandles() {
		return _webDriver.getWindowHandles();
	}

	public WebDriver getWrappedWebDriver() {
		return _webDriver;
	}

	@Override
	public Options manage() {
		return _webDriver.manage();
	}

	@Override
	public Navigation navigate() {
		return _webDriver.navigate();
	}

	@Override
	public void quit() {
		_webDriver.quit();
	}

	@Override
	public TargetLocator switchTo() {
		return _webDriver.switchTo();
	}

	private WebDriver _webDriver;

}