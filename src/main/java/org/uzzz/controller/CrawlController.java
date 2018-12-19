package org.uzzz.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.uzzz.crawlers.CsdnCrawler;

@Controller
@RequestMapping("crawl")
public class CrawlController {

	@Autowired
	private CsdnCrawler crawler;

	@GetMapping("blockchain")
	@ResponseBody
	public String blockchain() throws IOException {
		return crawler.blockchain();
	}

	@GetMapping("url")
	public String url(String url) throws IOException {
		String redirect = crawler.url(url);
		return "redirect:" + redirect;
	}
}
