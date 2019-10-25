package uzsupport;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.uzzz.SupportApp;
import org.uzzz.bean.Referer;
import org.uzzz.dao.slave.RefererSlaveDao;
import org.uzzz.utils.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupportApp.class)
public class VlangSearchTest {

	private static Log log = LogFactory.getLog(VlangSearchTest.class);

	@Autowired
	private RefererSlaveDao refererSlaveDao;

	@Test
	public void search() {
		String key = "V语言";
		int start = 0;
		int end = 3;

		String urlTemp = "https://so.csdn.net/so/search/s.do?t=blog&q=%s&p=%d";
		for (int p = start; p <= end; p++) {
			try {
				String url = String.format(urlTemp, key, p);

				Connection conn = Jsoup.connect(url);
				conn.header("User-agent", "Mozilla/4.0");
				conn.header("Cookie",
						"TY_SESSION_ID=fa68bb15-50d6-46f8-8cdc-9522aa841cfb; JSESSIONID=AD7834807EE772F2B0F92AD92C404970; uuid_tt_dd=10_6645302180-1558423058052-360706; dc_session_id=10_1558423058052.526174; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_6645302180-1558423058052-360706!1788*1*PC_VC!5744*1*yu_fang1987; smidV2=20181205165707f6e655c3b9e52376f955c789252ae01600a57767669218030; _ga=GA1.2.109375524.1558586351; UN=yu_fang1987; BT=1560738699948; aliyun_UAToken=118#ZVWZzaTHH8X9Ee/rQe2VcZZTZegh6gIVZH2V/YT5zTWZiezCXfq4zexuZ8qTXgh6ZIAuZ8WTzHRGZHW6VGQEOjWG7gDTXHP+ZZZ/ZzqhzeRZzgeZXoqVzeA2ZZZh0HWWGcb/ZzqTqhZzZgZCUfq4zH2ZZZChXHWVZgZZusqhzeWZZgCuTOq4zH2ZZZYPgZW4Zg2+CeThebChVH8ukfTrED8Z1m2gsmGbdqad4PvenWsiTn3xggCvr4/Pb8S7ZZe3L4PXZZxqdgSSKpz3ZYiP+7stpDS8GwQWj78VzJisO2YB7Q9s0qcmVVS9NAUlSC0x3b8CyZ4maBvE1eQfM9N7UjvTLXnkYJ13Eq4uSe4GnVwGfyM59BsU5ZCvxuW/1sslCggtzurJ3CAZRjgvywJnR0BrHR2YBjQk6wMTUaTPCcwk8xzoJfPvVPB+uSRmLzXcEiwUa5u8wWSdEReRlufUy7I+rv+xKvMnz9weblYDe/K+eDMe0J+5pqyOf8lzURV04PS7MxfMrzRU7w+6yzLDZzY/wAMGRg2amI2VQK+w2pY2Ow0jhnR3Klmon/2zX4aT+WvCRYaAhjbMhFtptB4Th8LqIwfi2oZZPWNkUNUuQlNL92ImSYt3TN+1G2M2H1mhdQlfogzUo5pDOenffEtk7Sdi7TNoVXHTEX5xdpIVVKjvaSD19Di5metrfhzb72RCmXYI2xBWTklbiFxO4cAksZTZ2Da5fZ8p5zZV0WGohWgx4Pw+ZDngJhwGIv4jQ+A4iKlB6OtOPhYHrxaTYefoRcpDANCJjNf2ltGi1chdGqCezGlHpK3EIASbjuJjXaQpTJMiDPNPC83CdE0oFsGQpkE9u3rMCiYy+ahP7WdxNEkTj/IUP7lYBPHFCpV/Yd4hGwM66ReXhRcU2iKhAZ3rJZb=; aliyun_webUmidToken=TDF35BD2529D41CAA01D8AF9837DA746B33306BD49CACA124F33809BBDF; UM_distinctid=16b8c9b0506121-0c12d9f573a4ab-e353165-100200-16b8c9b05073db; _dg_id.40e39cb6d36d5282.c482=bf3846a733dbbbbf%7C%7C%7C1562208799%7C%7C%7C0%7C%7C%7C1562208799%7C%7C%7C1562208799%7C%7C%7C%7C%7C%7C032089ff14b77d9b%7C%7C%7Chttps%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DPiK_m1x1IsfynS8ZxbCjLQsyrQM3z-Oo7NcAGIR5BJLeHjqyQxY0f4aaDegaNclZKa7pF38BbVq8XPNeKd2IvGUANjVyfSVYSR96eTssPlO%26wd%3D%26eqid%3Dd9565ac400042505000000035d1d6a19%7C%7C%7Chttps%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DPiK_m1x1IsfynS8ZxbCjLQsyrQM3z-Oo7NcAGIR5BJLeHjqyQxY0f4aaDegaNclZKa7pF38BbVq8XPNeKd2IvGUANjVyfSVYSR96eTssPlO%26wd%3D%26eqid%3Dd9565ac400042505000000035d1d6a19%7C%7C%7C1%7C%7C%7Cundefined; gr_user_id=4a9b68a9-a0b0-4e71-8aee-4f0c7232800d; pt_s_7cd998c4=vt=1562208799643&cad=; pt_7cd998c4=uid=sxkABNx4a0eqnr38A3RsGQ&nid=1&vid=VAUlydKOZX3/38HpOIzfnQ&vn=1&pvn=1&sact=1562208819679&to_flag=1&pl=w8U2aGywnHkYGksFsQki8w*pt*1562208799643; firstDie=1; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1563432325,1563434994,1563435114,1563435358; acw_tc=65c86a0c15634415094997656ebd1ddab461280f8ea662d6730590152e32af; SESSION=39d1fb68-6303-4a08-9562-39aa0a31deae; __yadk_uid=Hwd4vP2ybvqAZUWrWLRowOqUlbvGATsR; c-login-auto=3; acw_sc__v3=5d31279e66726466bf84cad792083c9cca66f8a8; acw_sc__v2=5d31276ba9e31212441e8d8db520c2dc9f135ea1; dc_tos=puva9n; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1563502523");
				Document doc = conn.get();

				Elements elements = doc.select("dl.search-list");
				for (Element e : elements) {
					String value = e.attr("data-report-click");
					String article_url = Utils.substring(value, "\"dest\":\"", "\"");
					if (article_url != null && article_url.startsWith("https://blog.csdn.net")) {
						url(article_url, key);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
			}
		}

	}

	public void url(String url, String tags) throws IOException {
		try {
			Document _doc = Jsoup.connect(url).get();
			String time = _doc.select(".time").first().text().split("日")[0].replace("年", "-").replace("月", "-");
			String title = _doc.select(".title-article").text();
			Elements article = _doc.select("article");

			List<String> thumbnails = new ArrayList<>();

			article.select("img").stream().parallel().forEach(element -> {
				String src = imgUrl(element);
				thumbnails.add(src);
			});
			article.select("script, #btn-readmore, .article-copyright").remove();
			String c = article.html();

			String source = _doc.outerHtml();
			String username = Utils.substring(source, "var username = \"", "\";");
			String id = Utils.substring(source, "var articleId = \"", "\";");
			id = StringUtils.isEmpty(id) ? Utils.substring(source, "var fileName = \"", "\";") : id;
			String filename = username + "_" + id;

			String content = "---\r\n" //
					+ "layout: post\r\n" //
					+ "title: \"" + title.replace("\\", "\\\\").replace("\"", "\\\"") + "\"\r\n" //
					+ "date: \"" + time + "\"\r\n" //
					+ "categories: \"博文\"\r\n" //
					+ "tags: \"V语言\"\r\n" //
					+ "---\r\n\r\n" //
					+ c;

			try {
				String mulu = "C:\\uzblog\\vlang-articles\\";
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(mulu + filename + ".html", false), "UTF-8"));
				writer.write(content);
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
			log.error(ioe.getMessage(), ioe);
		}
	}

	private String imgUrl(Element element) {

		String src = element.absUrl("src");

		if (StringUtils.isNotBlank(src)) {
			List<Referer> referers = refererSlaveDao.findAll();
			boolean b = false;
			for (Referer r : referers) {
				String host = r.getHost();
				if (src.startsWith("http://" + host) //
						|| src.startsWith("https://" + host)) {
					b = true;
					break;
				}
			}
			if (b) {
				src = "https://uzshare.com/_p?" + src;
				element.attr("src", src);
			}
		}

		return src;
	}
}