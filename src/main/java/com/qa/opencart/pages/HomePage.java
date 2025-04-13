package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	private Page page;
	// 1. String Locators - Object repo

	private String search = "//input[@placeholder='Search']";
	private String searchIcon = "//button[@class='btn btn-default btn-lg']";
	private String searchPageHeader = "div#content h1";

	// 2. Page contructor
	public HomePage(Page page) {
		this.page = page;
	}

	// 3. page actions/methods
	public String getHomePageTitle() {
		String title = page.title();
		System.out.println("Page title is: " + title);
		return title;
	}

	public String getHomePageURL() {
		String url = page.url();
		System.out.println("page url is: " + url);
		return url;
	}

	public String doSearch(String productName) {
		page.fill(search, productName);
		page.click(searchIcon);
		String header = page.textContent(searchPageHeader);
		System.out.println("search hearder: " + header);
		return header;
	}

}
