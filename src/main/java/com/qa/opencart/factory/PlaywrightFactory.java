package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties prop;

	public Page initBrowser(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		System.out.println("browser name is : " + browserName);

		playwright = Playwright.create();

		switch (browserName.toLowerCase()) {
		case "chromium":
			playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "firefox":
			playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "safari":
			playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "chrome":
			playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			break;
		default:
			System.out.println("please pass the right browser name");
			break;
		}
		browserContext = browser.newContext();
		page = browserContext.newPage();
		page.navigate(prop.getProperty("url").trim());
		return page;
	}
	// this method is used to initialize the properties from config file

	public Properties init_prop() {
		try {
			FileInputStream ip = new FileInputStream("./src/test/resourcess/config/config.properties");
			prop = new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}
}
