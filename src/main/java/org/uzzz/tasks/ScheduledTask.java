package org.uzzz.tasks;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.uzzz.crawlers.CsdnCrawler;

@Component
public class ScheduledTask {

	private static Log log = LogFactory.getLog(ScheduledTask.class);

	@Autowired
	private CsdnCrawler crawler;

	@Autowired
	private RestTemplate rest;

	@Scheduled(initialDelay = 2000, fixedDelay = 1000 * 60 * 10)
	public void crawl_blockchain() throws IOException {
		log.warn("crawl blockchain @Scheduled");
		crawler.blockchain();
	}

	@Scheduled(initialDelay = 2000, fixedDelay = 1000 * 60 * 10)
	public void crawl_careerlife() throws IOException {
		log.warn("crawl careerlife @Scheduled");
		crawler.careerlife();
	}

	@Scheduled(initialDelay = 2000, fixedDelay = 1000 * 60 * 60 * 12)
	public void rewritesitemapxml() {
		log.warn("rewritesitemapxml start");
		String url = "https://blog.uzzz.org/api/rewritesitemapxml";
		String ok = rest.getForObject(url, String.class);
		log.warn("rewritesitemapxml end : " + ok);
	}
}